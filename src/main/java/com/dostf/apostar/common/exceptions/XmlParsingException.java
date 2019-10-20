package com.dostf.apostar.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class XmlParsingException extends RuntimeException {
    public XmlParsingException(String message) {
        super(message);
    }
}
