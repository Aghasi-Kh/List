package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/")
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userServiceimpl;

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
    public void deleteUserById(@PathVariable Long id){
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

    @PutMapping("register")
    public ResponseEntity registerUser(@Valid @RequestBody User user) {
        user.setMPassword(Md5Encoder.encode(user.getMPassword()));
        userServiceimpl.save(user);
        return ResponseEntity.ok(user);
    }
}
