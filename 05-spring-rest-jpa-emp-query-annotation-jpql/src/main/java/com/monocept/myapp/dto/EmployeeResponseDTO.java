package com.monocept.myapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeResponseDTO {

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank(message = "email is empty")
	@Email(message = "email is not well formated")
	private String email;

	@NotNull
	private String designation;

	@NotNull
	private boolean active;

	public EmployeeResponseDTO(String name, String email, String designation, boolean active) {
		super();
		this.name = name;
		this.email = email;
		this.designation = designation;
		this.active = active;
	}

	public EmployeeResponseDTO() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EmployeeResponseDTO [name=" + name + ", email=" + email + ", designation=" + designation + ", active="
				+ active + "]";
	}
	
	

}
