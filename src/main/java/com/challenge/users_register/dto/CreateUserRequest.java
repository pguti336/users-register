package com.challenge.users_register.dto;

import com.challenge.users_register.model.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message = "Attribute 'email' should not be empty.")
    @Email(message = "Invalid email format.", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "Attribute 'password' should not be empty.")
    private String password;

    private List<Phone> phones;

}