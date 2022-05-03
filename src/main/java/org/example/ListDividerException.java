package org.example;

public class ListDividerException extends RuntimeException{
    String message;

    public ListDividerException(String message) {
        this.message = message;
    }

    public ListDividerException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public ListDividerException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public ListDividerException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
