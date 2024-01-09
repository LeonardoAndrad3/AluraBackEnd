package br.com.stream.challenge.exceptions;

import com.fasterxml.jackson.annotation.JsonAlias;

public class NotFindException extends RuntimeException {
    public NotFindException(String message) {
        super(message);
    }
}
