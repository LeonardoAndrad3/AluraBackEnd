package br.com.challengeApiFilms.service.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
