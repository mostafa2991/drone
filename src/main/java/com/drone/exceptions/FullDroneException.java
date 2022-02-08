package com.drone.exceptions;

public class FullDroneException extends RuntimeException {
    public FullDroneException() {
        super("Can't registered more drones, already 10 registered");
    }
}
