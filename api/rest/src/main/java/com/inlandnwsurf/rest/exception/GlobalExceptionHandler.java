package com.inlandnwsurf.rest.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage internalServerException(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage("Internal Server Error");

		return message;
	}

	@ExceptionHandler(ElementNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(ElementNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(ex.getMessage());

		return message;
	}
}
