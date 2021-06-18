package com.mitali.devproblem.clientapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {

	@ExceptionHandler(InvalidIdException.class)
	public final ResponseEntity<String> invalidIdHandler(InvalidIdException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidMobileNumberException.class)
	public final ResponseEntity<String> invalidMobileNumberHandler(InvalidMobileNumberException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<String> dataNotFoundHandler(DataNotFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
