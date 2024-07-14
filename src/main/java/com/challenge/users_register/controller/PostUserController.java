package com.challenge.users_register.controller;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.dto.UserDTO;
import com.challenge.users_register.exception.ErrorResponse;
import com.challenge.users_register.model.User;
import com.challenge.users_register.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api")
public class PostUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    private ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        if (!userService.isValidPassword(createUserRequest.getPassword())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Password does not match with requirements."));
        }

        User savedUser = userService.save(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedUser));
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}