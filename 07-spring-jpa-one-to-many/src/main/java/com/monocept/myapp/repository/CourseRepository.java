package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
