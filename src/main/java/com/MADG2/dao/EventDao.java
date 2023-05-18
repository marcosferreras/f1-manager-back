package com.MADG2.dao;

import com.MADG2.model.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    // Recupera un listado de eventos que se puede paginar (Pageable)
    // Pageable ya incluye ordenamiento
    Page<Event> findAll(Pageable pageable);
    Event save(Event event);
    void deleteById(Long id);
}
