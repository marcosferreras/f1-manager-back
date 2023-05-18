package com.MADG2.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.MADG2.model.mappers.teams.TeamMapper;
import com.MADG2.security.dto.*;
import com.MADG2.service.teams.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.MADG2.model.entities.Message;
import com.MADG2.security.service.RolService;
import com.MADG2.security.service.UserService;
import com.MADG2.security.jwt.JwtProvider;
import com.MADG2.security.entity.Rol;
import com.MADG2.security.entity.User;
import com.MADG2.security.enums.RolName;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    TeamService teamService;

    @Autowired
    TeamMapper teamMapper;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Invalid email"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity<>(new Message("Username already in use"), HttpStatus.BAD_REQUEST);
        if (userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity<>(new Message("Mail already in use"), HttpStatus.BAD_REQUEST);

        User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));

        if (newUser.getRoles().contains("admin")) {
            Rol rol = rolService.getByRolName(RolName.ROL_ADMIN).get();
            user.setRole(rol);
        }

        else if (newUser.getRoles().contains("manager")) {
            Rol rol = rolService.getByRolName(RolName.ROL_MANAGER).get();
            user.setRole(rol);
        }



        userService.save(user);

        return new ResponseEntity<>(new Message("User saved"), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        if(userDetails.getAuthorities().isEmpty()) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }

    @PutMapping("/update-user")
    public void updateUser(@RequestBody NewUser newUser) throws Exception {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getByUserName(((UserDetails)principal).getUsername()).get();
            if(newUser.getPassword()!=null)newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userService.update((long) user.getId(), newUser);
        }
        catch (ClassCastException e){
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/user-details")
    public ResponseEntity<User> getUserDetails(){

        ResponseEntity<User> responseEntity;
        User user;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            user = userService.getByUserName(((UserDetails)principal).getUsername()).orElse(null);
            if(user != null){
                if(user.getRole().getRolName()==RolName.ROL_MANAGER && user.getTeamId() != null)
                    user.setTeamManager(teamMapper.toDto(teamService.findById(user.getTeamId())));
                responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return responseEntity;
        }
        catch (ClassCastException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/users-without-rol")
    public Page<User> getAllUserWithoutRol(Pageable pageable, @RequestBody(required = false)UsernameDto dto){
        return userService.getAllUserWithoutRol(pageable, dto.getName());
    }

    @PostMapping("/add-rol-user")
    public void assignRolToUser(@RequestBody RolUserDto rolUserDto) throws ChangeSetPersister.NotFoundException {
        userService.setRolToUser(rolUserDto.getIdUser(), rolUserDto.getRol());
    }

    @GetMapping("/roles")
    public List<Rol> findAllRoles(){
        return rolService.findAll();
    }

    @DeleteMapping("/cancel-user/{idUser}")
    public void deleteUser(@PathVariable Long idUser){
        userService.cancelUser(idUser);
    }

    @PostMapping("/managers-without-team")
    public Page<User> getAllManagerWithoutTeam(Pageable pageable, @RequestBody(required = false)UsernameDto dto){
        return userService.getAllManagerWithoutTeam(pageable, dto.getName());
    }

    @PostMapping("/add-manager-team")
    public void assignManagerToTeam(@RequestBody ManagerTeamDto managerTeamDto) throws ChangeSetPersister.NotFoundException {
        userService.setTeamToManager(managerTeamDto.getIdUser(), managerTeamDto.getIdTeam());
    }
}
