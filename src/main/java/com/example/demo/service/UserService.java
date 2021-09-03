package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.util.exception.DataNotFoundException;

import java.util.List;

public interface UserService {

    User getUserById(Long id) throws DataNotFoundException;

    void deleteUserById(Long id);

    List<User> users();

    User save(User user) throws DataNotFoundException;

    User update(User user) throws DataNotFoundException;

    // User login(String email, String password) throws DataNotFoundException, DuplicateDataException;
}
