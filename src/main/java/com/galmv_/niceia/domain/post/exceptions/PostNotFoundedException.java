package com.galmv_.niceia.domain.post.exceptions;

import java.io.Serial;

public class PostNotFoundedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public PostNotFoundedException(String message){
        super(message);
    }

}
