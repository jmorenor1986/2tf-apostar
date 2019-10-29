package com.dostf.apostar.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MandatoryDtoMissingException extends RuntimeException{
    public MandatoryDtoMissingException(String s) {
        super(s);
    }
}
