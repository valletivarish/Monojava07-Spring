package com.monocept.myapp.dto;

import lombok.Data;

@Data
public class ProfileRequestDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
