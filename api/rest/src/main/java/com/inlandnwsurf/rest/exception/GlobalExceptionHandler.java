//package com.inlandnwsurf.rest.exception;
//
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//	public static final String TRACE = "trace";
//
//	@Value("spring.application.debug")
//	private boolean debugMode;
//
//	private ResponseEntity<Object> buildErrorResponse(Exception exception,
//			HttpStatus httpStatus, WebRequest request){
//		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
//	}
//
//	private ResponseEntity<Object> buildErrorResponse(Exception exception,
//			String message, HttpStatus httpStatus, WebRequest request){
//		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
//		return ResponseEntity.status(httpStatus).body(errorResponse);
//	}
//
//	@ExceptionHandler(ElementNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<Object> handleElementNotFoundException(ElementNotFoundException elementNotFoundException,
//			WebRequest request){
//		return buildErrorResponse(elementNotFoundException, HttpStatus.NOT_FOUND, request);
//	}
//
//    private boolean isDebugOn(WebRequest request) {
//        String[] value = request.getParameterValues(TRACE);
//        return Objects.nonNull(value)
//                && value.length > 0
//                && value[0].contentEquals("true");
//    }
//
//}
