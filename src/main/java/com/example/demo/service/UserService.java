package com.example.demo.service;

import com.example.demo.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
    public List<User> users();
    public User getUserById(Long id);
    public User register(@Valid User user);
}
