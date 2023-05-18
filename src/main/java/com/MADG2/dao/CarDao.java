package com.MADG2.dao;

import com.MADG2.model.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {

    Page<Car> findAllByTeamId(Long teamId, Pageable pageable);
}
