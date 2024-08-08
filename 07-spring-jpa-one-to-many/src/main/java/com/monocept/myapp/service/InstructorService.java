package com.monocept.myapp.service;

import java.util.List;

import com.monocept.myapp.entity.Course;
import com.monocept.myapp.entity.Instructor;

public interface InstructorService {

	List<Instructor> getAllInstructors();

	Instructor saveInstructor(Instructor instructor);

	Instructor getInstructorById(int id);

	String deleteInstructor(int id);

	String updateCourse(String id, Course course);

	Instructor removeCourseFromInstructor(int instructorID, int courseID);

	Instructor asignCourseToInstructor(int instructorID, int courseID);

}
