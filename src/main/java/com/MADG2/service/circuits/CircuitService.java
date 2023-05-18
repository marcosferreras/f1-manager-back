package com.MADG2.service.circuits;

import com.MADG2.model.entities.Circuit;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CircuitService {
    Page<Circuit> findAll(Pageable pageable);
    void delete(long id);
    void save(Circuit circuit);
    Circuit findById(long id);
    void update(Long id, Circuit circuit) throws ChangeSetPersister.NotFoundException;
}
