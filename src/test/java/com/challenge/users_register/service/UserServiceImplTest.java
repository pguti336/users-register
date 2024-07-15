package com.challenge.users_register.service;

import com.challenge.users_register.model.User;
import com.challenge.users_register.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

//    @BeforeEach
//    public void setUp() {
//    }

    @Test
    public void shouldReturnUserWhenFindById() {
        UUID id = UUID.randomUUID();
        String email = "user1@test.com";
        String password = "password";

        when(userRepository.findById(id)).thenReturn(Optional.of(new User(id, email, "password")));

        Optional<User> optionalUser = userRepository.findById(id);

        assertTrue(optionalUser.isPresent());
        assertEquals(email, optionalUser.get().getEmail());
        assertEquals(password, optionalUser.get().getPassword());
    }

}