package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.constants.Message.DUPLICATE_USER_MESSAGE;
import static com.example.demo.util.constants.Message.INVALID_CREDENTIALS_MESSAGE;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MyUserDetailsServiceImpl myUserDetailsServiceImpl, JwtUtil jwtUtil, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.myUserDetailsServiceImpl = myUserDetailsServiceImpl;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public UserDto login(String userEmail, String password) throws DataNotFoundException {
        User user = userRepository.getByEmail(userEmail);
        DataNotFoundException.check(user == null || bCryptPasswordEncoder.matches(password, user.getMPassword()), INVALID_CREDENTIALS_MESSAGE);
        return new UserDto(user.getMEmail(), user.getMPassword());
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

    public JwtResponse authenticate(UserDto userDto) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(),
                            userDto.getPassword()
                    ));

        } catch (BadCredentialsException e) {
            System.out.println("aaaaa");
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = myUserDetailsServiceImpl.loadUserByUsername(userDto.getEmail());

        final String token =
                jwtUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);

    }


}
