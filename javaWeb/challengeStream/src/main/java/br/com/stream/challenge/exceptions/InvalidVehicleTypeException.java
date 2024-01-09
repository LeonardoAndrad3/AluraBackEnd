package br.com.stream.challenge.exceptions;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class InvalidVehicleTypeException extends RuntimeException {
    public InvalidVehicleTypeException(@JsonAlias("error") String message) {
        super(message);
    }
}
