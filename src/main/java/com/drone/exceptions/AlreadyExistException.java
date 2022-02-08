package com.drone.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String code) {
        super("This item is already exist with number "+code);
    }
}
