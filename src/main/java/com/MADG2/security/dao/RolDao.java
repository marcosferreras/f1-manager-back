package com.MADG2.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MADG2.security.entity.Rol;
import com.MADG2.security.enums.RolName;

public interface RolDao extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolName(RolName rolName);
    
}
