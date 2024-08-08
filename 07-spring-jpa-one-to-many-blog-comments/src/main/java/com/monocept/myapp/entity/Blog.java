package com.monocept.myapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "blog")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "blog")
	@JsonManagedReference
	private List<Comment> comments;

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public void removeComment(Comment comment) {
		if (comments.contains(comment)) {
			comments.remove(comment);
		}
	}

}
