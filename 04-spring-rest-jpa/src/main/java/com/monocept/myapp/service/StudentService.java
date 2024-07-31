package com.monocept.myapp.service;

import java.util.List;

import com.monocept.myapp.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();

	Student getStudentById(int id);



	Student saveAndUpdate(Student student);

	void deleteStudent(int id);
}
