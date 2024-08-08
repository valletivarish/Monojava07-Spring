package com.monocept.myapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.entity.Employee;
import com.monocept.myapp.service.EmployeeService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService service;

	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}
	
	@GetMapping()
	public List<Employee> getAllEmployees() {
		return service.findAllEmployees();
	}
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return service.save(employee);
		
	}
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		return service.save(employee);
	}
	@DeleteMapping("{employeeId}")
	public String deleteEmployee(@PathVariable(name = "employeeId") int id) {
		return service.delete(id);
	}
	
	@GetMapping("/employeeWithAddress/{id}")
	public Employee employeeWithAddress(@PathVariable(name = "id")int id) {
		return service.employeeWithAddress(id);
	}
	
	
}
