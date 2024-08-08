package com.monocept.myapp.service;

import java.util.List;

import com.monocept.myapp.entity.Employee;

public interface EmployeeService {

	List<Employee> findAllEmployees();

	Employee save(Employee employee);
	
	Employee findById(int id);

	String delete(int id);

	Employee employeeWithAddress(int id);

}
