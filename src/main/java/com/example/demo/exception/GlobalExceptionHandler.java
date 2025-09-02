package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	  @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	        ErrorResponse errorDetails = new ErrorResponse(
	                LocalDateTime.now(),
	                HttpStatus.NOT_FOUND.value(),
	                "Not Found",
	                ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }
	
	  // You can add more handlers for other exceptions here
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
	        ErrorResponse errorDetails = new ErrorResponse(
	                LocalDateTime.now(),
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "Internal Server Error",
	                ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
