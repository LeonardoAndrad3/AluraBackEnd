package br.com.alura.exceptions;

public class ErrorConvertYearException extends RuntimeException{

    public ErrorConvertYearException(String message) {
        super(message);
    }

    public ErrorConvertYearException(String message, Throwable cause) {
        super(message, cause);
    }
}
