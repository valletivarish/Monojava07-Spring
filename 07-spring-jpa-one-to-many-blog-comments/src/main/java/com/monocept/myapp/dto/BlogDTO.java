package com.monocept.myapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class BlogDTO {
	private int id;

	@NotBlank(message = "Title must not be blank. 'Please provide a title to make your blog more informative.'")
	private String title;

	@NotBlank(message = "Category must not be blank. 'Please provide a category for better organization.'")
	private String category;

	@NotBlank(message = "Data must not be blank. Please provide the required information.")
	@Size(min = 50, max = 2000, message = "Data must be between 50 and 2000 characters.")
	private String data;

	@NotNull(message = "Published date must not be null. Please provide the publication date.")
	private LocalDateTime publishedDate = LocalDateTime.now();

	private boolean published;

	private List<CommentDTO> comments;

}
