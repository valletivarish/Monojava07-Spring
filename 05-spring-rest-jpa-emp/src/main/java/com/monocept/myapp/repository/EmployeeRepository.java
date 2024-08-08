package com.monocept.myapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public Employee findByName(String name);
	public Page<Employee> findByActiveTrue(Pageable pages);
	public Page<Employee> findByActiveFalse(Pageable pageable);
	public Employee findByEmail(String email);
	public Page<Employee> findByNameStartingWith(String name, Pageable pageable);
	public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation,Pageable pageable);
	public Page<Employee> findBySalaryBetween(Double start,Double end, Pageable pageable);
	public int countByActiveTrue();
	public int countByDesignation(String designation);
	public int countBySalaryGreaterThan(double salary);
}
