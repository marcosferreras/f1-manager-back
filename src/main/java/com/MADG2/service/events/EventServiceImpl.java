package com.MADG2.service.events;

import com.MADG2.dao.EventDao;

import com.MADG2.model.dtos.events.NewEventDto;
import com.MADG2.model.entities.Circuit;
import com.MADG2.model.entities.Event;
import com.MADG2.service.circuits.CircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventDao eventDao;
    @Autowired
    private CircuitService circuitService;

    @Override
    public Page<Event> findAll(Pageable pageable) {

        return eventDao.findAll(pageable);
    }

    @Override
    public void delete(long id) {
        eventDao.deleteById(id);
    }

    @Override
    public void save(NewEventDto event) {
        Circuit circuit = circuitService.findById(event.getCircuitId());
        Event finalEvent = new Event();
        finalEvent.setColor(event.getColor());
        finalEvent.setEndAt(event.getEndAt());
        finalEvent.setStartAt(event.getStartAt());
        finalEvent.setSummary(event.getSummary());
        finalEvent.setCircuit(circuit);
        eventDao.save(finalEvent);
    }

    @Override
    public void update(Long id, NewEventDto event) {
        Circuit circuit = circuitService.findById(event.getCircuitId());
        Event finalEvent = new Event();
        finalEvent.setId(id);
        finalEvent.setColor(event.getColor());
        finalEvent.setEndAt(event.getEndAt());
        finalEvent.setStartAt(event.getStartAt());
        finalEvent.setSummary(event.getSummary());
        finalEvent.setCircuit(circuit);
        eventDao.save(finalEvent);
    }
}
