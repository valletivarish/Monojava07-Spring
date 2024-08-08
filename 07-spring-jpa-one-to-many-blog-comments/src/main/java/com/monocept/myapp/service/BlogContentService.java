package com.monocept.myapp.service;

import com.monocept.myapp.dto.BlogDTO;
import com.monocept.myapp.dto.CommentDTO;
import com.monocept.myapp.util.PagedResponse;

public interface BlogContentService {

	PagedResponse<BlogDTO> getAllBlogs(int size, int page, String sortBy, String direction);

	BlogDTO getBlogById(int id);

	BlogDTO createAndUpdateBlog(BlogDTO blogDTO);

	String deleteBlog(int id);

	BlogDTO addComment(int id, CommentDTO commentDTO);

	BlogDTO removeComment(int blogid, int commentid);

}
