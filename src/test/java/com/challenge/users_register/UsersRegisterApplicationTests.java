package com.challenge.users_register;

import com.challenge.users_register.dto.CreateUserRequest;
import com.challenge.users_register.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersRegisterApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldCreateUser() {
		CreateUserRequest newUserRequest = new CreateUserRequest();
		newUserRequest.setEmail("test@test.com");
		newUserRequest.setPassword("P4s$word");
		ResponseEntity<UserDTO> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void shouldNotCreateUserIfEmailExists() {
		CreateUserRequest newUserRequest = new CreateUserRequest();
		newUserRequest.setEmail("test1@test1.com");
		newUserRequest.setPassword("P4s$word");
		ResponseEntity<?> createResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		ResponseEntity<?> secondCreateResponse = restTemplate.postForEntity("/api/users", newUserRequest, UserDTO.class);
		assertThat(secondCreateResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
	}
}
