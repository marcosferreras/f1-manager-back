package com.MADG2.security.service;


import java.util.Objects;
import java.util.Optional;

import com.MADG2.common.BadRequestException;
import com.MADG2.model.entities.Team;
import com.MADG2.security.dto.NewUser;
import com.MADG2.security.entity.Rol;
import com.MADG2.security.enums.RolName;
import com.MADG2.service.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MADG2.security.dao.UserDao;
import com.MADG2.security.entity.User;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RolService rolService;

    @Autowired
    TeamService teamService;

    public Optional<User> getByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public Page<User> getAllUserWithoutRol(Pageable pageable, String name){
        return userDao.findAllByRolesIsNullAndUserNameContainingIgnoreCase(pageable, name);
    }

    public Page<User> getAllManagerWithoutTeam(Pageable pageable, String name){
        Rol rolManager = rolService.getByRolName(RolName.ROL_MANAGER).get();
        return userDao.findAllByRolesIsAndUserNameContainingIgnoreCase(pageable, name, rolManager.getId());
    }

    public void setRolToUser(Long idUser, Rol rol) throws ChangeSetPersister.NotFoundException {
        User user = userDao.findById(idUser.intValue()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        user.setRole(rolService.getByRolName(rol.getRolName()).get());
        userDao.save(user);
    }

    public void setTeamToManager(Long idUser, Long idTeam) throws ChangeSetPersister.NotFoundException {
        Team team = teamService.findById(idTeam);
        User user = userDao.findById(idUser.intValue()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        try {
            if(team.getManagers().isEmpty())
                throw new BadRequestException("Team doesn't have manager");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        user.setTeam(team);
        userDao.save(user);

    }
    
    public boolean existsByUserName(String userName){
        return userDao.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return userDao.existsByEmail(email);
    }

    public void save(User user){
        userDao.save(user);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public void update(Long idUser, NewUser newUser) throws Exception {
        User user = userDao.findById(idUser.intValue()).orElseThrow(() -> new Exception("User id not found - " + idUser));
        if(newUser.getUserName()!=null)user.setUserName(newUser.getUserName());
        if(newUser.getName()!=null)user.setName(newUser.getName());
        if(newUser.getEmail()!=null)user.setEmail(newUser.getEmail());
        if(newUser.getPassword()!=null)user.setPassword(newUser.getPassword());
    }

    public void cancelUser(Long idUser){
        userDao.deleteById(idUser.intValue());
    }

    public User findByTeamId(Long id){
        return userDao.findByTeamId(id);
    }
}
