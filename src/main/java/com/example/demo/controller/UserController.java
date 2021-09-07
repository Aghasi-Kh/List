package com.example.demo.controller;


import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//worked with it

@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
@RestController
public class UserController {
    final
    UserServiceImpl userServiceimpl;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserServiceImpl userServiceimpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceimpl = userServiceimpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping("user/{id}")
    public User getById(@PathVariable Long id) throws DataNotFoundException {
        return userServiceimpl.getUserById(id);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> users() {
        List<User> users = userServiceimpl.users();
        return ResponseEntity.ok().body(users);
    }

    @Transactional
    @PostMapping("users")
    public User add(@Valid @RequestBody User user) {
        return userServiceimpl.save(user);
    }

    @Transactional
    @GetMapping("deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userServiceimpl.deleteUserById(id);
    }

    @Transactional
    @PatchMapping("users")
    public User update(@RequestBody User user) throws DataNotFoundException {
        return userServiceimpl.update(user);
    }

    @Transactional
    @PutMapping("users")
    public User updateAll(@RequestBody User user) {

        try {
            return userServiceimpl.update(user);
        } catch (DataNotFoundException e) {
            return userServiceimpl.save(user);
        }
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws DataNotFoundException {
        user.setMPassword((bCryptPasswordEncoder.encode(user.getMPassword())));
        userServiceimpl.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        JwtResponse jwtResponse;
        System.out.println(userDto.getEmail() + "     " + userDto.getPassword());
        try {
            userServiceimpl.login(userDto.getEmail(), userDto.getPassword());
            //jwtResponse = userServiceimpl.authenticate(userDto);
        } catch (DataNotFoundException e) {
            System.out.println("Stexa hasel");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDto);

        }
        return ResponseEntity.ok(userDto);

    }


}
