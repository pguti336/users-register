package com.challenge.users_register.controller;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.dto.UserDTO;
import com.challenge.users_register.model.Phone;
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
        CreateUserRequest newUserRequest = new CreateUserRequest();
        newUserRequest.setEmail("test@test.com");
        newUserRequest.setPassword("P4s$word");

        Phone phone1 = new Phone();
        phone1.setNumber("11122233");
        phone1.setCitycode("1");
        phone1.setCountrycode("55");

        Phone phone2 = new Phone();
        phone2.setNumber("22233344");
        phone2.setCitycode("1");
        phone2.setCountrycode("55");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);

        newUserRequest.setPhones(phones);

        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldNotCreateUserIfEmailExists() {
        CreateUserRequest newUserRequest = new CreateUserRequest();
        newUserRequest.setEmail("test1@test1.com");
        newUserRequest.setPassword("P4s$word");
        newUserRequest.setPhones(getPhonesList());

        ResponseEntity<?> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<?> secondCreateResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(secondCreateResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void shouldNotCreateUserIfPasswordIsWeak() {
        CreateUserRequest newUserRequest = new CreateUserRequest();
        newUserRequest.setEmail("test@test.com");
        newUserRequest.setPassword("password");
        newUserRequest.setPhones(getPhonesList());

        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldNotCreateUserIfEmailIsNotValid() {
        CreateUserRequest newUserRequest = new CreateUserRequest();
        newUserRequest.setEmail("test.com");
        newUserRequest.setPassword("P4$sword");
        newUserRequest.setPhones(getPhonesList());

        ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private List<Phone> getPhonesList() {
        Phone phone1 = new Phone();
        phone1.setNumber("11122233");
        phone1.setCitycode("1");
        phone1.setCountrycode("55");

        Phone phone2 = new Phone();
        phone2.setNumber("22233344");
        phone2.setCitycode("1");
        phone2.setCountrycode("55");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);

        return phones;
    }
}