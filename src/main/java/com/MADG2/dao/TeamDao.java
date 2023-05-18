package com.MADG2.dao;

import com.MADG2.model.entities.Team;
import com.MADG2.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {
    // Recupera un listado de Equipos ordenado
    @Query(value = "select e from Team e")
    List<Team> findAll(Sort sort);

    // Recupera un listado de equipos que se puede paginar (Pageable)
    // Pageable ya incluye ordenamiento
    //@Query(value = "select e from Team e", countQuery = "select count(e) from Team e")
    Page<Team> findAll(Pageable pageable);

    // Recupera el equipo que busquemos
    @Query(value = "select e from Team e where e.id = :id")
    Team findById(long id);

    Team findByManagersContaining(User user);
}
