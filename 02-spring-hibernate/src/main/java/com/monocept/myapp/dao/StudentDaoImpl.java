package com.monocept.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao {
	
	EntityManager entityManager;
	

	public StudentDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	@Transactional
	public void save(Student student) {
		this.entityManager.persist(student);
		
	}


	@Override
	public List<Student> getAllStudents() {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s",Student.class);
		List<Student> resultList = query.getResultList();
		return resultList;
	}


	@Override
	public Student getStudentById(int id) {
		Student student=entityManager.find(Student.class, id);
		return student;
	}


	@Override
	public List<Student> getStudentByFirstName(String firstName) {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s where firstName=:first",Student.class);
		query.setParameter("first", firstName);
		return query.getResultList();
	}


	@Override
	public List<Student> getStudentByFirstNameAndLastName(String firstName, String lastName) {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s where firstName=?1 and lastName=?2",Student.class);
		query.setParameter(1, firstName);
		query.setParameter(2, lastName);
		return query.getResultList();
	}


	@Override
	@Transactional
	public void updateStudent(Student student) {
		Student student2 = entityManager.find(Student.class, student.getId());
		if(student2!=null) {
			entityManager.merge(student);
		}
		else {
			System.out.println("student not found");
		}
	}


	@Override
	@Transactional
	public void deleteStudent(int id) {
		Student student=entityManager.find(Student.class, id);
		if(student!=null) {
			entityManager.remove(student);
		}
		else {
			System.out.println("no student found with id "+id);
		}
		
	}


	@Override
	@Transactional
	public void updateStudentWithoutMerge(Student s) {
		Student student = entityManager.find(Student.class, s.getId());
		if(student!=null) {
			Query query = entityManager.createQuery("update Student s set s.firstName=?1,s.lastName=?2,s.email=?3 where s.id=?4");
			query.setParameter(1, s.getFirstName());
			query.setParameter(2, s.getLastName());
			query.setParameter(3, s.getEmail());
			query.setParameter(4, s.getId());
			query.executeUpdate();
		}
		
	}


	@Override
	@Transactional
	public void deleteAllStudents(int id) {
		Query query = entityManager.createQuery("delete from Student s where s.id<=?1");
		query.setParameter(1, id);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		
	}

}
