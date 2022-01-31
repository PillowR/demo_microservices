package com.urbanisationsi.microservices_produit.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends RuntimeException {

	private static final long serialVersionUID = -8370853424778032266L;

	public ProduitIntrouvableException(String message) {
		super(message);
	}

}
