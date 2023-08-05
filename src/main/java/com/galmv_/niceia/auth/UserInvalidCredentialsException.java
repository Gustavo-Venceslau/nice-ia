package com.galmv_.niceia.auth;

import java.io.Serial;

public class UserInvalidCredentialsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserInvalidCredentialsException(String message){
        super(message);
    }
}
