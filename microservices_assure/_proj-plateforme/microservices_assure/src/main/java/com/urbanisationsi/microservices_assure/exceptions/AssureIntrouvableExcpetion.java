package com.urbanisationsi.microservices_assure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssureIntrouvableExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AssureIntrouvableExcpetion(String msg) {
		super(msg);
	}

}
