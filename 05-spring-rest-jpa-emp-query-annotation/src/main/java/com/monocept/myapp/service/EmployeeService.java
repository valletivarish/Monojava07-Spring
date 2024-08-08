package com.monocept.myapp.service;

import com.monocept.myapp.dto.EmployeeDTO;
import com.monocept.myapp.dto.EmployeeResponseDTO;
import com.monocept.myapp.util.PagedResponse;

import jakarta.validation.Valid;

public interface EmployeeService {

	PagedResponse<EmployeeResponseDTO> getAllEmployees(int page,int size, String sortBy, String direction);

	EmployeeDTO getEmployeeById(int id);

	EmployeeDTO saveAndUpdateEmployee(@Valid EmployeeDTO employeeDTO);

	void deleteEmployee(int id);

	EmployeeDTO getEmployeeByName(String name);

	PagedResponse<EmployeeDTO> getEmployeeByActiveTrue(int page, int size, String sortBy, String direction);
	
	PagedResponse<EmployeeDTO> getEmployeeByActiveFalse(int page, int size, String sortBy, String direction);

	EmployeeDTO getEmployeeByEmail(String email);

	PagedResponse<EmployeeDTO> getEmployeeNameStartingWith(String prefix, int page, int size, String sortBy, String direction);

	PagedResponse<EmployeeDTO> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation, int size, int page, String sortBy, String direction);

	PagedResponse<EmployeeDTO> getEmployeeSalaryBetween(Double startSalary, Double endSalary, int page, int size, String sortBy, String direction);

	int getEmployeeCountAndActive();

	int getEmployeeCountAndDesignation(String designation);

	int countSalaryGreaterthan(double salary);

}
