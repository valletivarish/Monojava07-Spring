package com.monocept.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monocept.myapp.entity.Student;
import com.monocept.myapp.exception.StudentApiException;
import com.monocept.myapp.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Override
	public List<Student> findAll() {
		return repository.findAll();
	}

	@Override
	public Student findById(Long id) {
		Optional<Student> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new StudentApiException(HttpStatus.NOT_FOUND, "Student with id was not found"+id);
		
	}

	@Transactional
	@Override
	public Student save(Student student) {
		return repository.save(student);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

	@Override
	public List<Student> saveAll(List<Student> studentList) {
		return repository.saveAll(studentList);
	}

}
