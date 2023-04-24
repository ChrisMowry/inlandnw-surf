//package com.inlandnwsurf.rest.exception;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@RequiredArgsConstructor
//public class ErrorResponse {
//    private final int status;
//    private final String message;
//    private String stackTrace;
//    private List<ValidationError> errors;
//
//    ErrorResponse(int status, String message){
//    	this.status = status;
//    	this.message = message;
//    }
//
//    @Getter
//    @Setter
//    @RequiredArgsConstructor
//    private static class ValidationError {
//        private final String field;
//        private final String message;
//        ValidationError(String field, String message){
//        	this.field = field;
//        	this.message = message;
//        }
//    }
//
//    public void addValidationError(String field, String message){
//        if(Objects.isNull(errors)){
//            errors = new ArrayList<>();
//        }
//        errors.add(new ValidationError(field, message));
//    }
//}
