package com.monocept.myapp.dao;

import java.util.List;

import com.monocept.myapp.entity.Employee;

public interface EmployeeDao {

	void addEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int id);

	List<Employee> getEmployeeByName(String string);

	void updateEmployee(Employee employee);

	void deleteEmployee(int id);

	void updateEmployeeUsingQuery(Employee employee);

	void deleteEmployeeUsingQuery(int id);

}
