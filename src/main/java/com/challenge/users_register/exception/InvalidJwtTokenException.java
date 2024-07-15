package com.challenge.users_register.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidJwtTokenException extends Exception {
    public InvalidJwtTokenException(String msg) {
        super(msg);
    }
}