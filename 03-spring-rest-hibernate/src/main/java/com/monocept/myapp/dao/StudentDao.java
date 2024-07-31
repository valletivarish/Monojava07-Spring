package com.monocept.myapp.dao;

import java.util.List;

import com.monocept.myapp.entity.Student;

public interface StudentDao {

	List<Student> getAllStudents();

	Student getStudentById(int id);



	Student saveAndUpdate(Student student);

	void deleteStudent(int id);

}
