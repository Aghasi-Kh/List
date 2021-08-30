package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.util.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User getUserById(Long id) throws UserNotFoundException;
    User deleteUserById(Long id);
    List<User> users();
    User save(User user);
    User update(User user) throws UserNotFoundException;
}
