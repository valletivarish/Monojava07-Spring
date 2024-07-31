package com.monocept.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
@Repository
public class StudentDaoImpl implements StudentDao {
	private EntityManager entityManager;
	
	public StudentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Student> getAllStudents() {
		Query query = entityManager.createQuery("select s from Student s");
		
		return query.getResultList();
	}

	@Override
	public Student getStudentById(int id) {
		Student student = entityManager.find(Student.class, id);
		return student;
	}

	@Override
	@Transactional
	public Student saveAndUpdate(Student student) {
		return entityManager.merge(student);

		
	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
		Student student=entityManager.find(Student.class, id);
		if(student!=null) {
			entityManager.remove(student);
		}
		else {
			System.out.println("No student found with id "+id);
		}
		
	}



}
