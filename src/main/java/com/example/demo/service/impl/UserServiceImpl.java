package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) throws DataNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new DataNotFoundException(id));
    }

    @Override
    public List<User> users() {
        return userRepository.users();
    }

    @Override
    public User save(@Valid User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User newUser) throws DataNotFoundException {
        Optional<User> user = userRepository.findById(newUser.getMUserID());

        if (user.isPresent()) {
            return userRepository.save(newUser);
        } else {
            throw new DataNotFoundException(newUser.getMUserID());
        }
    }

    @Override
    public void deleteUserById(Long id) {
         userRepository.deleteUserById(id);
    }


}
