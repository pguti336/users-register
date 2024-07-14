package com.challenge.users_register.service;

import com.challenge.users_register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public String generateToken(com.challenge.users_register.model.User user) {
        String token = jwtService.generateToken(user.getEmail());

        user.setLastLogin(Instant.now());
        userRepository.save(user);
        return token;
    }
}
