package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
