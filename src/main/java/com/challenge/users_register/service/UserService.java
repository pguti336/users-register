package com.challenge.users_register.service;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.model.User;

import java.util.Optional;

public interface UserService {
    User save(CreateUserRequest user);
    Optional<User> findByEmail(String email);
    boolean isValidPassword(String password);
}