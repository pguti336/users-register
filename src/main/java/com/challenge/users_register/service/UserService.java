package com.challenge.users_register.service;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findById(UUID uuid);

    User save(CreateUserRequest user);
    Optional<User> findByEmail(String email);
    boolean isValidPassword(String password);

    User update(User user);
}