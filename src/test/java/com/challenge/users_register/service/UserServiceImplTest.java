package com.challenge.users_register.service;

import com.challenge.users_register.config.TestConfig;
import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.model.User;
import com.challenge.users_register.repository.UserRepository;
import com.challenge.users_register.utils.CreateUserRequestTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        when(userRepository.findById(id)).thenReturn(Optional.of(new User(id, email, password)));

        Optional<User> optionalUser = userService.findById(id);

        assertTrue(optionalUser.isPresent());
        assertEquals(email, optionalUser.get().getEmail());
        assertEquals(password, optionalUser.get().getPassword());
    }

    @Test
    public void shouldReturnEmptyWhenUserIdNotFound() {
        UUID id = UUID.randomUUID();

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> optionalUser = userService.findById(id);

        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void shouldReturnUserWhenFindByEmail() {
        UUID id = UUID.randomUUID();
        String email = "user1@test.com";
        String password = "password";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User(id, email, password)));

        Optional<User> optionalUser = userService.findByEmail(email);

        assertTrue(optionalUser.isPresent());
        assertEquals(email, optionalUser.get().getEmail());
        assertEquals(password, optionalUser.get().getPassword());
    }

    @Test
    public void shouldReturnEmptyWhenUserEmailNotFound() {
        String email = "user1@test.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> optionalUser = userService.findByEmail(email);

        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void shouldSaveUserWhenRequestIsValid() {
//        CreateUserRequest newUserRequest = CreateUserRequestTestUtil.getDummyCreateUserRequest("new@user.com");
//        UUID id = UUID.randomUUID();
//        when(userRepository.save(new User(id, newUserRequest.getEmail(), newUserRequest.getPassword()))).thenReturn(new User(id, newUserRequest.getEmail(), newUserRequest.getPassword()));
//
//        User savedUser = userService.save(newUserRequest);
//
//        assertEquals(id, savedUser.getId());
//        assertEquals(newUserRequest.getEmail(), savedUser.getEmail());
//        assertEquals(newUserRequest.getPassword(), savedUser.getPassword());
//        assertEquals(newUserRequest.getPhones().size(), savedUser.getPhones().size());

    }

    @Test
    public void shouldNotSaveUserWhenEmailExists() {

    }
}