package com.monocept.myapp.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundException e) {
		ResourceErrorResponse error = new ResourceErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ResourceErrorResponse>(error, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler
	public ResponseEntity<ResourceErrorResponse> handleException(Exception e) {
		ResourceErrorResponse error = new ResourceErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("An unexpected error occurred.");
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ResourceErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
