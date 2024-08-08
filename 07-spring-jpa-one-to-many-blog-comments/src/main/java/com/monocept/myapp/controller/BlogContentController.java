package com.monocept.myapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.dto.BlogDTO;
import com.monocept.myapp.dto.CommentDTO;
import com.monocept.myapp.service.BlogContentService;
import com.monocept.myapp.util.PagedResponse;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/blogs")
public class BlogContentController {

	private BlogContentService blogContentService;

	public BlogContentController(BlogContentService blogContentService) {
		super();
		this.blogContentService = blogContentService;
	}

	@GetMapping
	public ResponseEntity<PagedResponse<BlogDTO>> getAllBlogs(@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<BlogDTO> blogs = blogContentService.getAllBlogs(size, page, sortBy, direction);
		return new ResponseEntity<PagedResponse<BlogDTO>>(blogs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable(name = "id") int id) {
		return new ResponseEntity<BlogDTO>(blogContentService.getBlogById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
		return new ResponseEntity<BlogDTO>(blogContentService.createAndUpdateBlog(blogDTO), HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<BlogDTO> updateBlog(@Valid @RequestBody BlogDTO blogDTO) {
		return new ResponseEntity<BlogDTO>(blogContentService.createAndUpdateBlog(blogDTO), HttpStatus.OK);

	}

	@DeleteMapping("/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable(name = "blogId") int id) {
		return new ResponseEntity<String>(blogContentService.deleteBlog(id), HttpStatus.OK);
	}

	@PostMapping("/{id}/comments")
	public ResponseEntity<BlogDTO> addComment(@PathVariable(name = "id") int id,
			@Valid @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(blogContentService.addComment(id, commentDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{blogId}/comments/{commentId}")
	public ResponseEntity<BlogDTO> deleteComment(@PathVariable(name = "blogId") int blogId,
			@PathVariable(name = "commentId") int commentId) {
		return new ResponseEntity<>(blogContentService.removeComment(blogId, commentId), HttpStatus.OK);
	}

}
