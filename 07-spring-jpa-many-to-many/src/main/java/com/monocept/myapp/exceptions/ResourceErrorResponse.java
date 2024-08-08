package com.monocept.myapp.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ResourceErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timeStamp;
}
