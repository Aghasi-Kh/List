package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userServiceimpl;

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id) throws UserNotFoundException {
        return userServiceimpl.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> users() {
        return userServiceimpl.users();
    }

    @Transactional
    @PostMapping("/users")
    public User user(@Valid @RequestBody User user) {
        return userServiceimpl.save(user);
    }

    @Transactional
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceimpl.deleteUserById(id);
    }

    @Transactional
    @PatchMapping("/users")
    public User update(@RequestBody User user) throws UserNotFoundException {
        return userServiceimpl.update(user);
    }

    @Transactional
    @PutMapping("/users")
    public User updateAll(@RequestBody User user){

        try {
            return userServiceimpl.update(user);
        } catch (UserNotFoundException e) {
            return userServiceimpl.save(user);
        }
    }
}
