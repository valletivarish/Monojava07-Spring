package com.monocept.myapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.dto.EmployeeDTO;
import com.monocept.myapp.dto.EmployeeResponseDTO;
import com.monocept.myapp.entity.Employee;
import com.monocept.myapp.service.EmployeeService;
import com.monocept.myapp.util.PagedResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<PagedResponse<EmployeeResponseDTO>> getAllEmployees(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {

		PagedResponse<EmployeeResponseDTO> employees = employeeService.getAllEmployees(page, size, sortBy, direction);
		return new ResponseEntity<PagedResponse<EmployeeResponseDTO>>(employees, HttpStatus.OK);
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
	public ResponseEntity<PagedResponse<EmployeeDTO>> getEmployeeByActiveTrue(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<EmployeeDTO> employeesDTO = employeeService.getEmployeeByActiveTrue(page, size, sortBy,
				direction);
		return new ResponseEntity<>(employeesDTO, HttpStatus.OK);
	}

	@GetMapping("/activeFalse")
	public ResponseEntity<PagedResponse<EmployeeDTO>> getEmployeeByActiveFalse(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<EmployeeDTO> employees = employeeService.getEmployeeByActiveFalse(page, size, sortBy, direction);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/startingWith/{prefix}")
	public ResponseEntity<PagedResponse<EmployeeDTO>> getEmployeeNameStartingWith(
			@PathVariable(name = "prefix") String prefix, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<EmployeeDTO> employees = employeeService.getEmployeeNameStartingWith(prefix, page, size, sortBy,
				direction);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/salaryGreaterThanAndDepartment")
	public ResponseEntity<PagedResponse<EmployeeDTO>> getEmployeeSalaryGreaterThanAndDepartment(
			@RequestBody Employee employee, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {

		PagedResponse<EmployeeDTO> employees = employeeService.getEmployeeSalaryGreaterThanAndDepartment(
				employee.getSalary(), employee.getDesignation(), page, size, sortBy, direction);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/salaryBetween/{start}/{end}")
	public ResponseEntity<PagedResponse<EmployeeDTO>> getEmployeeSalaryBetween(@PathVariable(name = "start") Double startSalary,
			@PathVariable(name = "end") Double endSalary, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) {
		PagedResponse<EmployeeDTO> employees = employeeService.getEmployeeSalaryBetween(startSalary, endSalary,page,size,sortBy,direction);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/countAndActive")
	public ResponseEntity<Integer> getEmployeeCountAndActive() {
		int count = employeeService.getEmployeeCountAndActive();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping("/countBySalaryGreaterThanAndActive/{salary}")
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
