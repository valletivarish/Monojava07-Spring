package com.monocept.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monocept.myapp.entity.Student;
import com.monocept.myapp.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;
	

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public Student saveAndUpdate(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}

}
