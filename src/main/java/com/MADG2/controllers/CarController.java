package com.MADG2.controllers;

import com.MADG2.model.dtos.cars.CarDto;
import com.MADG2.security.entity.User;
import com.MADG2.security.enums.RolName;
import com.MADG2.security.service.UserService;
import com.MADG2.service.cars.CarService;
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

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    @PostMapping
    public void save(@RequestBody CarDto carDto) throws Exception {
        carService.save(carDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        carService.delete(id);
    }

    @PutMapping("/{carId}")
    public void update(@RequestBody CarDto carDto, @PathVariable Long carId) throws ChangeSetPersister.NotFoundException {
        carService.update(carId, carDto);
    }

    @GetMapping("/manager-cars")
    public ResponseEntity<Page<CarDto>> getManagerTeam(Pageable pageable){

        ResponseEntity<Page<CarDto>> responseEntity;
        User user;
        Page<CarDto> carList=Page.empty();


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            user = userService.getByUserName(((UserDetails)principal).getUsername()).orElse(null);
            if(user != null){
                if(user.getRole().getRolName() == RolName.ROL_MANAGER) {
                    if(user.getTeamId()!=null)
                        carList = carService.findAll(user.getTeam().getId(), pageable);
                }
                else carList = carService.findAll(pageable);
                responseEntity = new ResponseEntity<>(carList, HttpStatus.OK);
            }
            else {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return responseEntity;
        }
        catch (ClassCastException | ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
