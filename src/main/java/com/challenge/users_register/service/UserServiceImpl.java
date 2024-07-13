package com.challenge.users_register.service;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.exception.UserAlreadyExistsException;
import com.challenge.users_register.model.Phone;
import com.challenge.users_register.model.User;
import com.challenge.users_register.repository.UserRepository;
import com.challenge.users_register.utils.JwtTokenUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${validation.password.regex}")
    private String passwordRegex;

    @SneakyThrows
    @Override
    public User save(CreateUserRequest createUserRequest) {
        Optional<User> userOptional = findByEmail(createUserRequest.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User email already exists.");
        } else {
            User newUser = new User();
            newUser.setEmail(createUserRequest.getEmail());
            newUser.setPassword(createUserRequest.getPassword());
            newUser.setPhones(createUserRequest.getPhones());

            for (Phone phone: createUserRequest.getPhones()) {
                phone.setUser(newUser);
            }

            final String token = jwtTokenUtil.generateToken(createUserRequest.getEmail());
            newUser.setToken(token);
            newUser.setLastLogin(Instant.now());

            return userRepository.save(newUser);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isValidPassword(String password) {
        return password.matches(passwordRegex);
    }
}
