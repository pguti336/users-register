package com.challenge.users_register.service;

import com.challenge.users_register.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    //User update(User user);
    boolean isValidPassword(String password);
}