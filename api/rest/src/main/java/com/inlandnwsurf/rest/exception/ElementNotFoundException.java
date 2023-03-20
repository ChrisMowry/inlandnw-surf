package com.inlandnwsurf.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@SuppressWarnings("serial")
public class ElementNotFoundException extends ResponseStatusException {
	
	public ElementNotFoundException(String errorMessage) {
		super(HttpStatus.NOT_FOUND, errorMessage);
	}

	/*
	 * @Override public HttpHeaders getResponseHeaders() { // return response
	 * headers }
	 */
}
