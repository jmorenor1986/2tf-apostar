package com.dostf.apostar.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MandatoryFieldsMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MandatoryFieldsMissingException(String message) {
		super(message);
	}
	

}
