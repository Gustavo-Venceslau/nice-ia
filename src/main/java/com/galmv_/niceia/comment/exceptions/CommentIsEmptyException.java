package com.galmv_.niceia.comment.exceptions;

import java.io.Serial;

public class CommentIsEmptyException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public CommentIsEmptyException(String message){
        super(message);
    }
}
