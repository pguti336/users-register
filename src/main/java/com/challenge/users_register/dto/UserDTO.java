package com.challenge.users_register.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;

    private Instant created;

    private Instant modified;

    @JsonProperty("last_login")
    private Instant lastLogin;

    @JsonProperty("is_active")
    private boolean active;

    private String token;
}