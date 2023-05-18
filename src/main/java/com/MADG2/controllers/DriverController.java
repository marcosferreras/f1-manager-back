package com.MADG2.controllers;

import com.MADG2.model.dtos.drivers.DriverDto;
import com.MADG2.security.entity.User;
import com.MADG2.security.enums.RolName;
import com.MADG2.security.service.UserService;
import com.MADG2.service.drivers.DriverService;
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
@RequestMapping("/drivers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class DriverController {

    @Autowired
    DriverService driverService;

    @Autowired
    UserService userService;

    @GetMapping("/{idDriver}")
    public DriverDto getDriverById(@PathVariable Long idDriver){
        return driverService.findByIdDto(idDriver);
    }

    @GetMapping
    public Page<DriverDto> findALl(Pageable pageable) {
        return driverService.findAll(pageable);
    }
    @PostMapping
    public void save(@RequestBody DriverDto driverDto) throws Exception{
        driverService.save(driverDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        driverService.delete(id);
    }

    @PutMapping("/{driverId}")
    public void update(@RequestBody DriverDto driverDto, @PathVariable Long driverId) throws ChangeSetPersister.NotFoundException {
        driverService.update(driverId, driverDto);
    }

    @GetMapping("/manager-drivers")
    public ResponseEntity<Page<DriverDto>> getManagerTeam(Pageable pageable){

        ResponseEntity<Page<DriverDto>> responseEntity;
        User user;
        Page<DriverDto> driverList;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            user = userService.getByUserName(((UserDetails)principal).getUsername()).orElse(null);
            if(user != null){
                if(user.getRole().getRolName() == RolName.ROL_MANAGER)
                    driverList = driverService.findAll(user.getTeam().getId(), pageable);
                else driverList = driverService.findAll(pageable);
                responseEntity = new ResponseEntity<>(driverList, HttpStatus.OK);
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
