package com.challenge.users_register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private final String email;
    private final boolean authenticated;

}