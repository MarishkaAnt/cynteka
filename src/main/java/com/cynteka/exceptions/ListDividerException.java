package com.cynteka.exceptions;

public class ListDividerException extends RuntimeException{
    String message;

    public ListDividerException(String message) {
        this.message = message;
    }

    public ListDividerException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
