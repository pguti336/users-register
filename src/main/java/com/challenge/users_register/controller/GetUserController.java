package com.challenge.users_register.controller;

import com.challenge.users_register.dto.UserDTO;
import com.challenge.users_register.exception.ErrorResponse;
import com.challenge.users_register.model.User;
import com.challenge.users_register.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class GetUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users/{id}")
    private ResponseEntity<?> getUser(@PathVariable String id) {
        Optional<User> optionalUser = userService.findById(UUID.fromString(id));
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(convertToDto(optionalUser.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User with id " + id + " not found"));
        }
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}