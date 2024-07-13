package com.challenge.users_register.controller;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.dto.UserDTO;
import com.challenge.users_register.exception.ErrorResponse;
import com.challenge.users_register.model.User;
import com.challenge.users_register.repository.UserRepository;
import com.challenge.users_register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    private ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {

        if (!userService.isValidPassword(createUserRequest.getPassword())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Password does not match with requirements."));
        }

        User newUserRequest = new User();
        newUserRequest.setEmail(createUserRequest.getEmail());
        newUserRequest.setPassword(createUserRequest.getPassword());
        newUserRequest.setPhones(createUserRequest.getPhones());

        User savedUser = userService.save(newUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedUser));
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}