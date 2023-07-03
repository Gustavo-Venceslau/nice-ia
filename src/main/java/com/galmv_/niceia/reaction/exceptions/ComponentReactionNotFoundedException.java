package com.galmv_.niceia.reaction.exceptions;

import java.io.Serial;

public class ComponentReactionNotFoundedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ComponentReactionNotFoundedException(String message){
        super(message);
    }
}
