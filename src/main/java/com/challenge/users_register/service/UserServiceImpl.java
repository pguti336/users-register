package com.challenge.users_register.service;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.exception.UserAlreadyExistsException;
import com.challenge.users_register.model.Phone;
import com.challenge.users_register.model.User;
import com.challenge.users_register.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${validation.password.regex}")
    private String passwordRegex;
    @Autowired
    private AuthService authService;

    @Override
    public Optional<User> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @SneakyThrows
    @Override
    public User save(CreateUserRequest createUserRequest) {
        Optional<User> userOptional = findByEmail(createUserRequest.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User email already exists.");
        } else {
            User newUser = new User();
            newUser.setEmail(createUserRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            newUser.setPhones(createUserRequest.getPhones());

            for (Phone phone: newUser.getPhones()) {
                phone.setUser(newUser);
            }

            final String token = authService.generateToken(newUser);
            newUser.setToken(token);

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

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
