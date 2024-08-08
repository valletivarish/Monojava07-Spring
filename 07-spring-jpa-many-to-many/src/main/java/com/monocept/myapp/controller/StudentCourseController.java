package com.monocept.myapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.dto.CourseResponseDTO;
import com.monocept.myapp.dto.StudentResponseDTO;
import com.monocept.myapp.entity.Course;
import com.monocept.myapp.entity.Student;
import com.monocept.myapp.service.StudentCourseService;


@RestController
@RequestMapping("api")
public class StudentCourseController {
	private StudentCourseService studentCourseService;
	
	public StudentCourseController(StudentCourseService studentCourseService) {
		super();
		this.studentCourseService = studentCourseService;
	}
	
	
	@PostMapping("students")
	public StudentResponseDTO createStudent(@RequestBody Student student) {
		return studentCourseService.createStudent(student);
	}
	
	@GetMapping("students")
	public List<StudentResponseDTO> getAllStudents() {
		return studentCourseService.getAllStudents();
	}
	@GetMapping("students/{studentid}")
	public StudentResponseDTO getStudentByID(@PathVariable(name = "studentid")long id) {
		return studentCourseService.getStudentByID(id);
	}
	@DeleteMapping("students/{studentid}")
	public String deleteStudentByID(@PathVariable(name = "studentid")long id) {
		String message=studentCourseService.deleteStudentByID(id);
		return message;
	}
	@PostMapping("courses")
	public CourseResponseDTO createCourse(@RequestBody Course course) {
		return studentCourseService.createCourse(course);
	}
	@GetMapping("courses")
	public List<CourseResponseDTO> getAllCourses() {
		return studentCourseService.getAllCourses();
	}
	@GetMapping("courses/{courseid}")
	public CourseResponseDTO getCourseByID(@PathVariable(name = "courseid")int id) {
		return studentCourseService.getCourseByID(id);
	}
	@DeleteMapping("courses/{courseid}")
	public String deleteCourseByID(@PathVariable(name = "courseid")int id) {
		String message=studentCourseService.deleteCourseByID(id);
		return message;
	}
	@PostMapping("students/{studentid}/courses/{courseid}")
	public CourseResponseDTO assign(@PathVariable(name = "studentid")long studentID, @PathVariable(name = "courseid")int courseID) {
		return studentCourseService.assignStudentToCourse(studentID,courseID);
	}
	@DeleteMapping("students/{studentid}/courses/{courseid}")
	public CourseResponseDTO delete(@PathVariable(name = "studentid")long studentID,@PathVariable(name = "courseid")int courseID) {
		return studentCourseService.deleteStudentFromCourse(studentID,courseID);
		
	}
	
	
	



}
