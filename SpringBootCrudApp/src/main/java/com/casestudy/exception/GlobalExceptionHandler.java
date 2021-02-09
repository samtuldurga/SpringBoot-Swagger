package com.casestudy.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.casestudy.model.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	  @ExceptionHandler(EmployeeNotFoundException.class)
	    public ResponseEntity<?> resourceNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
	         ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	    }

}
