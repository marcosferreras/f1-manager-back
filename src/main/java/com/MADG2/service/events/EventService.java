package com.MADG2.service.events;

import com.MADG2.model.dtos.events.NewEventDto;
import com.MADG2.model.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Page<Event> findAll(Pageable pageable);
    void delete(long id);
    void save(NewEventDto event);
    void update(Long id, NewEventDto event);
}
