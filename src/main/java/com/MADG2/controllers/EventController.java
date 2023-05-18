package com.MADG2.controllers;

import com.MADG2.model.dtos.events.NewEventDto;
import com.MADG2.model.entities.Event;
import com.MADG2.service.events.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class EventController {
    @Autowired
    EventService eventService;


    /* ---------------------------------- SHOW EVENTS --------------------------------------*/

    @GetMapping
    public Page<Event> findALl(Pageable pageable) {
        return eventService.findAll(pageable);
    }

    /* ---------------------------------- CREATE EVENTS --------------------------------------*/
    @PostMapping
    public void save(@RequestBody NewEventDto event){
        eventService.save(event);
    }

    /* ---------------------------------- DELETE EVENTS --------------------------------------*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        eventService.delete(id);
    }


    /* ---------------------------------- UPDATE EVENTS --------------------------------------*/
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody NewEventDto event){
        eventService.update(id, event);
    }
}
