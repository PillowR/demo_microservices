package com.urbanisationsi.microservices_contrat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContratIntrouvableException extends RuntimeException {
	private static final long serialVersionUID = -8370853424778032266L;

	public ContratIntrouvableException(String message) {
		super(message);
	}
}
