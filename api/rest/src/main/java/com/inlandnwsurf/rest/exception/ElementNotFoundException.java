package com.inlandnwsurf.rest.exception;

@SuppressWarnings("serial")
public class ElementNotFoundException extends Exception {

	public ElementNotFoundException(String errorMessage){
		super(errorMessage);
	}

}
