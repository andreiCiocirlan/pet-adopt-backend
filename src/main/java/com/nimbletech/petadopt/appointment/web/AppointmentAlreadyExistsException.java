package com.nimbletech.petadopt.appointment.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentAlreadyExistsException extends RuntimeException {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
