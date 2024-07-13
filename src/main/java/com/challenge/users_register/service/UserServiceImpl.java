package com.challenge.users_register.service;

import com.challenge.users_register.exception.UserAlreadyExistsException;
import com.challenge.users_register.model.Phone;
import com.challenge.users_register.model.User;
import com.challenge.users_register.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${validation.password.regex}")
    private String passwordRegex;

    @SneakyThrows
    @Override
    public User save(User user) {
        Optional<User> userOptional = findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User email already exists.");
        } else {
            for (Phone phone: user.getPhones()) {
                phone.setUser(user);
            }
            return userRepository.save(user);
        }
    }

//    public User update(User user) {
//        Optional<User> userOptional = findByEmail(user.getEmail());
//        if (userOptional.isPresent()) {
//            return userRepository.save(user);
//        } else {
//            return null;
//            //throw new UsernameNotFoundException("Email not found.");
//        }
//    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isValidPassword(String password) {
        return password.matches(passwordRegex);
    }
}
