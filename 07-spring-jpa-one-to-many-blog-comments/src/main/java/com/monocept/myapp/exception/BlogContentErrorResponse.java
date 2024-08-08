package com.monocept.myapp.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogContentErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timeStamp;
	
	
	
}
