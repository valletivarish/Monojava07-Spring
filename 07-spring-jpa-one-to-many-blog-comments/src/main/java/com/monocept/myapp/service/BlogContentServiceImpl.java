package com.monocept.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monocept.myapp.dto.BlogDTO;
import com.monocept.myapp.dto.CommentDTO;
import com.monocept.myapp.entity.Blog;
import com.monocept.myapp.entity.Comment;
import com.monocept.myapp.exception.BlogNotFoundException;
import com.monocept.myapp.exception.CommentNotFoundException;
import com.monocept.myapp.repository.BlogRepository;
import com.monocept.myapp.repository.CommentRepository;
import com.monocept.myapp.util.PagedResponse;

@Service
public class BlogContentServiceImpl implements BlogContentService {

	private BlogRepository blogRepository;
	private CommentRepository commentRepository;

	public BlogContentServiceImpl(BlogRepository blogRepository, CommentRepository commentRepository) {
		this.blogRepository = blogRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public PagedResponse<BlogDTO> getAllBlogs(int size, int page, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Blog> blogPage = blogRepository.findAll(pageable);
		List<BlogDTO> blogDTOs = convertBlogToBlogDTO(blogPage.getContent());
		return new PagedResponse<>(blogDTOs, blogPage.getNumber(), blogPage.getSize(), blogPage.getTotalElements(),
				blogPage.getTotalPages(), blogPage.isLast());
	}

	@Override
	public BlogDTO getBlogById(int id) {
		Blog blog = blogRepository.findById(id).orElse(null);
		if (blog == null) {
			throw new BlogNotFoundException("Blog not found with the id: " + id);
		}
		return convertBlogToBlogDTO(blog);
	}

	@Override
	public BlogDTO createAndUpdateBlog(BlogDTO blogDTO) {
		Blog blog = convertBlogDTO_to_Blog(blogDTO);

		if (blogDTO.getId() == 0) {
			return convertBlogToBlogDTO(blogRepository.save(blog));
		} else {
			Blog existingBlog = blogRepository.findById(blog.getId()).orElse(null);
			if (existingBlog == null) {
				throw new BlogNotFoundException("Blog not found with the id: " + blogDTO.getId());
			}

			existingBlog.setTitle(blog.getTitle());
			existingBlog.setCategory(blog.getCategory());
			existingBlog.setData(blog.getData());
			existingBlog.setPublishedDate(blog.getPublishedDate());
			existingBlog.setPublished(blog.isPublished());

			existingBlog.getComments().clear();
			existingBlog.getComments().addAll(blog.getComments());
			for (Comment comment : existingBlog.getComments()) {
				comment.setBlog(existingBlog);
			}

			return convertBlogToBlogDTO(blogRepository.save(existingBlog));
		}
	}

	@Override
	public String deleteBlog(int id) {
		Blog blog = blogRepository.findById(id).orElse(null);
		if (blog == null) {
			throw new BlogNotFoundException("Blog not found with the id: " + id);
		}
		blogRepository.delete(blog);
		return "Deleted Successfully";
	}

	private BlogDTO convertBlogToBlogDTO(Blog blog) {
		BlogDTO blogDTO = new BlogDTO();
		blogDTO.setId(blog.getId());
		blogDTO.setTitle(blog.getTitle());
		blogDTO.setCategory(blog.getCategory());
		blogDTO.setData(blog.getData());
		blogDTO.setPublishedDate(blog.getPublishedDate());
		blogDTO.setPublished(blog.isPublished());
		blogDTO.setComments(convertCommentToCommentDTO(blog.getComments(), blogDTO));
		return blogDTO;
	}

	private List<BlogDTO> convertBlogToBlogDTO(List<Blog> blogs) {
		List<BlogDTO> blogDTOs = new ArrayList<>();
		for (Blog blog : blogs) {
			blogDTOs.add(convertBlogToBlogDTO(blog));
		}
		return blogDTOs;
	}

	private Blog convertBlogDTO_to_Blog(BlogDTO blogDTO) {
		Blog blog = new Blog();
		blog.setId(blogDTO.getId());
		blog.setTitle(blogDTO.getTitle());
		blog.setCategory(blogDTO.getCategory());
		blog.setData(blogDTO.getData());
		blog.setPublishedDate(blogDTO.getPublishedDate());
		blog.setPublished(blogDTO.isPublished());
		blog.setComments(convertCommentDTOToComment(blogDTO.getComments(), blog));
		return blog;
	}

	private List<CommentDTO> convertCommentToCommentDTO(List<Comment> comments, BlogDTO blogDTO) {
		List<CommentDTO> commentDTOs = new ArrayList<>();
		for (Comment comment : comments) {
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setId(comment.getId());
			commentDTO.setDescription(comment.getDescription());
			commentDTOs.add(commentDTO);
		}
		return commentDTOs;
	}

	private List<Comment> convertCommentDTOToComment(List<CommentDTO> commentDTOs, Blog blog) {
		List<Comment> comments = new ArrayList<>();
		for (CommentDTO commentDTO : commentDTOs) {
			Comment comment = new Comment();
			comment.setId(commentDTO.getId());
			comment.setDescription(commentDTO.getDescription());
			comment.setBlog(blog);
			comments.add(comment);
		}
		return comments;
	}

	@Override
	public BlogDTO addComment(int id, CommentDTO commentDTO) {
		Blog blog = blogRepository.findById(id).orElse(null);
		if (blog == null) {
			throw new BlogNotFoundException("Blog not found with the id " + id);
		}
		Comment comment = convertCommentDTOToComment(commentDTO, blog);
		blog.addComment(comment);
		return convertBlogToBlogDTO(blogRepository.save(blog));
	}

	private Comment convertCommentDTOToComment(CommentDTO commentDTO, Blog blog) {
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setDescription(commentDTO.getDescription());
		comment.setBlog(blog);
		return comment;
	}

	@Override
	public BlogDTO removeComment(int blogid, int commentid) {
		Blog blog = blogRepository.findById(blogid).orElse(null);
		Comment comment = commentRepository.findById(commentid).orElse(null);

		if (blog == null) {
			throw new BlogNotFoundException("Blog not found with " + blogid);
		}
		if (comment == null) {
			throw new CommentNotFoundException("Comment not found with " + commentid);
		}
		blog.removeComment(comment);
		return convertBlogToBlogDTO(blogRepository.save(blog));
	}

}
