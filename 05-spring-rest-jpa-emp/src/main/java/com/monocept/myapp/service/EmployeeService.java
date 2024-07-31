package com.monocept.myapp.service;

import java.util.List;

import org.springframework.http.HttpStatusCode;

import com.monocept.myapp.controller.util.PagedResponse;
import com.monocept.myapp.dto.EmployeeDTO;
import com.monocept.myapp.dto.EmployeeResponseDTO;
import com.monocept.myapp.entity.Employee;

import jakarta.validation.Valid;

public interface EmployeeService {

	PagedResponse<EmployeeDTO> getAllEmployees(int page,int size, String sortBy, String direction);

	EmployeeDTO getEmployeeById(int id);

	EmployeeDTO saveAndUpdateEmployee(@Valid EmployeeDTO employeeDTO);

	void deleteEmployee(int id);

	EmployeeDTO getEmployeeByName(String name);

	List<EmployeeDTO> getEmployeeByActiveTrue();
	
	List<EmployeeDTO> getEmployeeByActiveFalse();

	EmployeeDTO getEmployeeByEmail(String email);

	List<EmployeeDTO> getEmployeeNameStartingWith(String prefix);

	List<EmployeeDTO> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation);

	List<EmployeeDTO> getEmployeeSalaryBetween(Double startSalary, Double endSalary);

	int getEmployeeCountAndActive();

	int getEmployeeCountAndDesignation(String designation);

	int countSalaryGreaterthan(double salary);

}
