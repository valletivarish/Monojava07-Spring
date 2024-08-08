package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
