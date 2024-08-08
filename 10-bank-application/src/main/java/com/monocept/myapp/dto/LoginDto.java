package com.monocept.myapp.dto;

public class LoginDto {
	private String email;
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String usernameOrEmail, String password) {
		super();
		this.email = usernameOrEmail;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + "]";
	}

}

