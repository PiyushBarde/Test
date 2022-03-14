package com.bridgelabz.user.exceptions;

import lombok.Data;

@Data
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
