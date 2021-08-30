package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> users() {
        return userRepository.users();
    }

    @Override
    public User deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }
}
