package com.guanyiping.ticketing.service;

import com.guanyiping.ticketing.entity.User;
import com.guanyiping.ticketing.repository.UserRepository;
import com.guanyiping.ticketing.request.LoginRequest;
import com.guanyiping.ticketing.request.UserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public User register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequest.getPassWord(), u.getPassWord()))
                .map(User::getEmail)
                .orElse(null);

    }
}
