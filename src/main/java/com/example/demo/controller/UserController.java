package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userServiceimpl;

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id){
        return userServiceimpl.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> users(){
        return userServiceimpl.users();
    }
}
