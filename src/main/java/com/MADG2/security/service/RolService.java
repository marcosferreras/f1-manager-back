package com.MADG2.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MADG2.security.dao.RolDao;
import com.MADG2.security.entity.Rol;
import com.MADG2.security.enums.RolName;

@Service
@Transactional
public class RolService {

    @Autowired
    RolDao rolDao;

    public Optional<Rol> getByRolName(RolName rolName){
        return rolDao.findByRolName(rolName);
    }

    public void save(Rol rol){
        rolDao.save(rol);
    }

    public List<Rol> findAll(){
        return rolDao.findAll();
    }
    
}
