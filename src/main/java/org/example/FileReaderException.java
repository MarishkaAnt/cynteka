package org.example;

public class FileReaderException extends RuntimeException{
    String message;

    public FileReaderException(String message) {
        this.message = message;
    }

    public FileReaderException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public FileReaderException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public FileReaderException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
