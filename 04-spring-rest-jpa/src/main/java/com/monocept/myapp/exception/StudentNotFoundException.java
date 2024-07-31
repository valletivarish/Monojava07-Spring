package com.monocept.myapp.exception;

public class StudentNotFoundException extends RuntimeException{
	public StudentNotFoundException(String message) {
		super(message);
	}
}
