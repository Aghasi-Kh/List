package com.example.demo.controller;

import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.service.MyUserDetailsServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.DataNotFoundException;
import com.example.demo.util.exception.DuplicateDataException;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/")
@RestController
public class UserController{
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
    public List<User> users() {
        return userServiceimpl.users();
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
    public ResponseEntity registerUser(@Valid @RequestBody User user) throws DuplicateDataException {
        user.setMPassword(Md5Encoder.encode(user.getMPassword()));
        userServiceimpl.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = myUserDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());

        final String token =
                jwtUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
