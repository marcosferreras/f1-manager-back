package com.MADG2.controllers;


import com.MADG2.model.entities.Circuit;
import com.MADG2.service.circuits.CircuitService;
import com.MADG2.service.uploads.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/circuits")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class CircuitController {
    @Autowired
    CircuitService circuitService;

    @Autowired
    UploadFileService uploadFileService;

    /* ---------------------------------- SHOW CIRCUITS --------------------------------------*/

    @GetMapping
    public Page<Circuit> findALl(Pageable pageable) {
        return circuitService.findAll(pageable);
    }

    /* ---------------------------------- CREATE CIRCUITS --------------------------------------*/
    @PostMapping
    public void save(@RequestBody Circuit circuit){
        circuitService.save(circuit);
    }

    /* ---------------------------------- DELETE CIRCUITS --------------------------------------*/
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        circuitService.delete(id);
    }

    /* ---------------------------------- UPDATE CIRCUITS --------------------------------------*/
    @PutMapping("/{idCircuit}")
    public void update(@RequestBody Circuit circuit, @PathVariable Long idCircuit) throws ChangeSetPersister.NotFoundException {
        circuitService.update(idCircuit, circuit);
    }

}
