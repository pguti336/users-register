package com.challenge.users_register.controller;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.dto.UserDTO;
import com.challenge.users_register.model.Phone;
import com.challenge.users_register.utils.CreateUserRequestTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostUserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCreateUser() {
        CreateUserRequest newUserRequest = CreateUserRequestTestUtil.getDummyCreateUserRequest("user1@test.com");

        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldNotCreateUserIfEmailExists() {
        CreateUserRequest newUserRequest = CreateUserRequestTestUtil.getDummyCreateUserRequest("user2@test.com");

        ResponseEntity<?> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<?> secondCreateResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(secondCreateResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void shouldNotCreateUserIfPasswordIsWeak() {
        CreateUserRequest newUserRequest = CreateUserRequestTestUtil.getDummyCreateUserRequest("user3@test.com");
        newUserRequest.setPassword("weakpass");
        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldNotCreateUserIfEmailIsNotValid() {
        CreateUserRequest newUserRequest = CreateUserRequestTestUtil.getDummyCreateUserRequest("user4test.com");

        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}