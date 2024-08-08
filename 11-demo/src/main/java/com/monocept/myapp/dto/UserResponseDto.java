package com.monocept.myapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
@JsonInclude(value = Include.NON_NULL)
public class UserResponseDto {
	private Long id;

	private String email;
	private CustomerResponseDto customerResponseDto;
}
