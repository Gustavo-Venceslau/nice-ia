package com.galmv_.niceia.domain.student.exceptions;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message){
        super(message);
    }
}
