package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
