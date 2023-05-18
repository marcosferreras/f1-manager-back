package com.MADG2.dao;

import com.MADG2.model.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDao extends JpaRepository<Driver, Long> {
    Page<Driver> findAllByTeamId(Long teamId, Pageable pageable);
}
