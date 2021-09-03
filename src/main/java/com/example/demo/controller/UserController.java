package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.impl.MyUserDetailsServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//worked with it

@CrossOrigin("http://localhost:4200")
@RequestMapping("/")
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userServiceimpl;

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

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
    public ResponseEntity registerUser(@Valid @RequestBody User user) throws DataNotFoundException {
        user.setMPassword(Md5Encoder.encode(user.getMPassword()));
        userServiceimpl.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto) throws Exception {
        JwtResponse jwtResponse;
        try {
            userServiceimpl.login(userDto.getEmail(), userDto.getPassword());
            jwtResponse = authenticate(userDto);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDto);
        }
        return ResponseEntity.ok(jwtResponse);


    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody UserDto userDto) throws Exception {


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(),
                            userDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = myUserDetailsServiceImpl.loadUserByUsername(userDto.getEmail());

        final String token =
                jwtUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
