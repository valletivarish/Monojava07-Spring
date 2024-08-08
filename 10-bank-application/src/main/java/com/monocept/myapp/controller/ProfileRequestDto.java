package com.monocept.myapp.controller;

import lombok.Data;

@Data
public class ProfileRequestDto {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
}
