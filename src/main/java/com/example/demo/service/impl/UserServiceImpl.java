package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    @Override
    public List<User> users() {
        return userRepository.users();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User newUser) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(newUser.getmUserID());

        if (user.isPresent()) {
            return userRepository.save(newUser);
        } else {
            throw new UserNotFoundException(newUser.getmUserID());
        }
    }

    @Override
    public User deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }


}
