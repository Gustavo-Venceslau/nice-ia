package com.galmv_.niceia.domain.reaction.exceptions;

import java.io.Serial;

public class ReactionNotFoundedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ReactionNotFoundedException(String message){
        super(message);
    }

}
