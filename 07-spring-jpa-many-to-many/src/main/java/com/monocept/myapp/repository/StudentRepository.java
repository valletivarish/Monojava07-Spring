package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query("select s from Student s where s.id=?1")
	Student findById(long id);
	
}
