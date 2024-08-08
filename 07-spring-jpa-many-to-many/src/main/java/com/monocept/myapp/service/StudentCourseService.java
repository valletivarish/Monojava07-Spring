package com.monocept.myapp.service;

import java.util.List;

import com.monocept.myapp.dto.CourseResponseDTO;
import com.monocept.myapp.dto.StudentResponseDTO;
import com.monocept.myapp.entity.Course;
import com.monocept.myapp.entity.Student;

public interface StudentCourseService {

	StudentResponseDTO createStudent(Student student);

	List<StudentResponseDTO> getAllStudents();

	StudentResponseDTO getStudentByID(long id);

	String deleteStudentByID(long id);

	CourseResponseDTO createCourse(Course course);

	List<CourseResponseDTO> getAllCourses();

	CourseResponseDTO getCourseByID(int id);

	String deleteCourseByID(int id);

	CourseResponseDTO assignStudentToCourse(long studentID, int courseID);

	CourseResponseDTO deleteStudentFromCourse(long studentID, int courseID);

}
