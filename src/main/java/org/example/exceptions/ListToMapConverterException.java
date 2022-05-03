package org.example.exceptions;

public class ListToMapConverterException extends RuntimeException{
    String message;

    public ListToMapConverterException(String message) {
        this.message = message;
    }

    public ListToMapConverterException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public ListToMapConverterException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public ListToMapConverterException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
