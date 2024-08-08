package com.monocept.myapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
	private int id;

	@NotBlank(message = "Description must not be blank. Please provide a description to add more details.")
	@Size(min = 8, max = 200, message = "Description must be between 8 and 200 characters.")
	private String description;

	private BlogDTO blogDTO;

}
