package com.monocept.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public Employee findByName(String name);
	public List<Employee> findByActiveTrue();
	public List<Employee> findByActiveFalse();
	public Employee findByEmail(String email);
	public List<Employee> findByNameStartingWith(String name);
	public List<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation);
	public List<Employee> findBySalaryBetween(Double start,Double end);
	public int countByActiveTrue();
	public int countByDesignation(String designation);
	public int countBySalaryGreaterThan(double salary);
}
