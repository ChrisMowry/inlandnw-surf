package com.inlandnwsurf.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

import com.inlandnwsurf.rest.exception.EntityNotFoundException;
import com.inlandnwsurf.rest.exception.ErrorResponse;
import com.inlandnwsurf.rest.model.location.State;
import com.inlandnwsurf.rest.service.StateService;

@RestController
@RequestMapping(value = "/api/v1/state")
public class StateController {
	
	private final StateService stateService;
	
	@Autowired
	StateController(StateService stateService){
		this.stateService = stateService;
	}
	
	@GetMapping("/")
	public ResponseEntity<Iterable<State>> getStates(){
		return ResponseEntity.ok(stateService.getStates());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<State> getState(@PathVariable String id){
		return ResponseEntity.ok(stateService.getState(id));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleItemNotFoundException( 
			EntityNotFoundException exception, WebRequest request
	  ){
	    return this.buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
	  }
	private ResponseEntity<ErrorResponse> buildErrorResponse(
		      Exception exception,
		      HttpStatus httpStatus,
		      WebRequest request
		  ) {
		    return buildErrorResponse(
		        exception, 
		        exception.getMessage(), 
		        httpStatus, 
		        request);
	}
	private ResponseEntity<ErrorResponse> buildErrorResponse(
			Exception exception,
			String message,
			HttpStatus httpStatus,
			WebRequest request
	){
		ErrorResponse errorResponse = new ErrorResponse(
				httpStatus.value(), 
		        exception.getMessage()
		    );
	}
}
