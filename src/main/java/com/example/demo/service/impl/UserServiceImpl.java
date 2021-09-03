package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.encoder.Md5Encoder;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.constants.Message.DUPLICATE_USER_MESSAGE;
import static com.example.demo.util.constants.Message.INVALID_CREDENTIALS_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) throws DataNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found with id-" + id));
    }

    @Override
    public List<User> users() {
        return userRepository.users();
    }

    @Override
    public User save(@Valid User user) {
        return userRepository.save(user);
    }

    public User register(@Valid User user) throws DataNotFoundException {
        User duplicate = userRepository.getByEmail(user.getMEmail());
        DataNotFoundException.check(duplicate != null, DUPLICATE_USER_MESSAGE);
        return userRepository.save(user);
    }

    public User login(String username, String password) throws DataNotFoundException {
        User user = userRepository.getByUsername(username);
        DataNotFoundException.check(user == null || !Md5Encoder.matches(password, user.getMPassword()), INVALID_CREDENTIALS_MESSAGE);
        return user;

    }

    @Override
    public User update(User newUser) throws DataNotFoundException {
        Optional<User> user = userRepository.findById(newUser.getMUserID());
        if (user.isPresent()) {
            return userRepository.save(newUser);
        } else {
            throw new DataNotFoundException("User not found with id-" + newUser.getMUserID());
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }


}
