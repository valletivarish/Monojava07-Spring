package com.monocept.myapp.dao;

import java.util.List;

import com.monocept.myapp.entity.Student;

public interface StudentDao {
	
	
	public void save(Student student);

	public List<Student> getAllStudents();

	public Student getStudentById(int i);

	public List<Student> getStudentByFirstName(String string);

	public List<Student> getStudentByFirstNameAndLastName(String string, String string2);

	public void updateStudent(Student student);

	public void deleteStudent(int i);

	public void updateStudentWithoutMerge(Student s);

	public void deleteAllStudents(int id);


}
