package com.dostf.apostar.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceNotAvailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceNotAvailableException(String message) {
        super(message);
    }
}
