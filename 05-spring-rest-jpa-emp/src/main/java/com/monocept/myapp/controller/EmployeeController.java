package com.monocept.myapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monocept.myapp.controller.util.PagedResponse;
import com.monocept.myapp.dto.EmployeeDTO;
import com.monocept.myapp.dto.EmployeeResponseDTO;
import com.monocept.myapp.entity.Employee;
import com.monocept.myapp.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<PagedResponse<EmployeeDTO>> getAllEmployees(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {

		PagedResponse<EmployeeDTO> employees = employeeService.getAllEmployees(page,size,sortBy,direction);
		return new ResponseEntity<PagedResponse<EmployeeDTO>>(employees, HttpStatus.OK);
	}

	@GetMapping("/{employeeid}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeid") int id) {
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		System.out.println("Received EmployeeDTO: " + employeeDTO);
		EmployeeDTO newEmployeeDTO = employeeService.saveAndUpdateEmployee(employeeDTO);
		return new ResponseEntity<EmployeeDTO>(newEmployeeDTO, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO employeeTemp = employeeService.saveAndUpdateEmployee(employeeDTO);
		return new ResponseEntity<EmployeeDTO>(employeeTemp, HttpStatus.OK);
	}

	@DeleteMapping("/{employeeid}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(name = "employeeid") int id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<EmployeeDTO> getEmployeeByName(@PathVariable(name = "name") String name) {
		EmployeeDTO employeeDTO = employeeService.getEmployeeByName(name);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable(name = "email") String email) {
		EmployeeDTO employeeDTO = employeeService.getEmployeeByEmail(email);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}

	@GetMapping("/activeTrue")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByActiveTrue() {
		List<EmployeeDTO> employeesDTO = employeeService.getEmployeeByActiveTrue();
		return new ResponseEntity<>(employeesDTO, HttpStatus.OK);
	}

	@GetMapping("/activeFalse")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByActiveFalse() {
		List<EmployeeDTO> employees = employeeService.getEmployeeByActiveFalse();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/startingWith/{prefix}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeNameStartingWith(@PathVariable(name = "prefix") String prefix) {
		List<EmployeeDTO> employees = employeeService.getEmployeeNameStartingWith(prefix);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/salaryGreaterThanAndDepartment")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeSalaryGreaterThanAndDepartment(@RequestBody Employee employee) {
		List<EmployeeDTO> employees = employeeService.getEmployeeSalaryGreaterThanAndDepartment(employee.getSalary(),
				employee.getDesignation());
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/salaryBetween/{start}/{end}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeSalaryBetween(@PathVariable(name = "start") Double startSalary,
			@PathVariable(name = "end") Double endSalary) {
		List<EmployeeDTO> employees = employeeService.getEmployeeSalaryBetween(startSalary, endSalary);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/countAndActive")
	public ResponseEntity<Integer> getEmployeeCountAndActive() {
		int count = employeeService.getEmployeeCountAndActive();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping("/countBySalaryGreaterThan/{salary}")
	public ResponseEntity<Integer> getEmployeeCountBySalaryGreaterThan(@PathVariable double salary) {
		int count = employeeService.countSalaryGreaterthan(salary);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping("/CountTheDesignation/{designation}")
	public ResponseEntity<Integer> getEmployeeCountAndDesignation(
			@PathVariable(name = "designation") String designation) {
		int count = employeeService.getEmployeeCountAndDesignation(designation);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
}
