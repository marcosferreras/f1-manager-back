package com.MADG2.controllers;


import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Car;
import com.MADG2.model.entities.Driver;
import com.MADG2.model.entities.Team;
import com.MADG2.security.entity.User;
import com.MADG2.security.service.UserService;
import com.MADG2.service.cars.CarService;
import com.MADG2.service.drivers.DriverService;
import com.MADG2.service.teams.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class TeamController {

    @Autowired
    TeamService teamService;
    @Autowired
    DriverService driverService;

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    /* ---------------------------------- SHOW TEAMS --------------------------------------*/
    @GetMapping
    public Page<Team> findAll(Pageable pageable) {
        return teamService.findAll(pageable);
    }


    /*----------------------------- SHOW ONE TEAM ------------------------------------------ */
    @GetMapping("/{id}")
    public Team findById(@PathVariable Long id) throws Exception {
        return teamService.findById(id);
    }

    /* ---------------------------------- CREATE TEAMS --------------------------------------*/
    @PostMapping
    public void save(@RequestBody Team team){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUserName(((UserDetails)principal).getUsername()).get();
        List<User> manager = new ArrayList<>();
        manager.add(user);
        team.setManagers(manager);
        Team team1 = teamService.save(team);
        user.setTeamId(team1.getId());
        userService.save(user);
    }

    /* ---------------------------------- DELETE TEAMS --------------------------------------*/
    @DeleteMapping()
    public void delete() throws ChangeSetPersister.NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUserName(((UserDetails)principal).getUsername()).get();
        Team team = user.getTeam();
        List<Driver> drivers = team.getDrivers();
        List<Car> cars = team.getCars();

        for ( Driver driver : drivers ) {
            driverService.delete(driver.getId());
        }
        for ( Car car : cars ) {
            carService.delete(car.getId());
        }

        user.setTeamId(null);

        teamService.delete(team.getId());
    }

    /* ---------------------------------- UPDATE TEAMS --------------------------------------*/
    @PutMapping()
    public void update(@RequestBody TeamDto team) throws ChangeSetPersister.NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUserName(((UserDetails)principal).getUsername()).get();
        Long idTeam = user.getTeam().getId();
        teamService.update(team, idTeam);
    }


    @GetMapping("/manager-team")
    public ResponseEntity<Team> getManagerTeam(){

        ResponseEntity<Team> responseEntity;
        Team team;
        User user;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            user = userService.getByUserName(((UserDetails)principal).getUsername()).orElse(null);
            if(user != null){
                team  = teamService.findByManager(user);
                responseEntity = new ResponseEntity<>(team, HttpStatus.OK);
            }
            else {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return responseEntity;
        }
        catch (ClassCastException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
