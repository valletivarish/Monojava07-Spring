package com.monocept.myapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{



}
