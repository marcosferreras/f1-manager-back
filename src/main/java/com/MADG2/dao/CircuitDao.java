package com.MADG2.dao;

import com.MADG2.model.entities.Circuit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitDao extends JpaRepository<Circuit, Long> {
    // Recupera un listado de equipos que se puede paginar (Pageable)
    // Pageable ya incluye ordenamiento
    //@Query(value = "select e from Team e", countQuery = "select count(e) from Team e")
    Page<Circuit> findAll(Pageable pageable);
    Circuit save(Circuit circuit);
    void deleteById(Long id);
    Circuit findById(long id);
}
