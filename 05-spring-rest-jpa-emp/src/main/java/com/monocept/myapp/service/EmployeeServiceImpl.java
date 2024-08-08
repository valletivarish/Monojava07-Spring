package com.monocept.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monocept.myapp.dto.EmployeeDTO;
import com.monocept.myapp.dto.EmployeeResponseDTO;
import com.monocept.myapp.entity.Employee;
import com.monocept.myapp.exception.EmployeeNotFoundException;
import com.monocept.myapp.repository.EmployeeRepository;
import com.monocept.myapp.util.PagedResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public PagedResponse<EmployeeResponseDTO> getAllEmployees(int page, int size, String sortBy, String direction) {

		Pageable pageable = getPageable(page, size, sortBy, direction);

		Page<Employee> pages = employeeRepository.findAll(pageable);
		List<Employee> employees = pages.getContent();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found add some");
		}
		List<EmployeeResponseDTO> employeesDTO = convertEmployeeListToEmployeeResponseDTOList(pages.getContent());
		return new PagedResponse<EmployeeResponseDTO>(employeesDTO, pages.getNumber(), pages.getSize(),
				pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
	}

	@Override
	public EmployeeDTO getEmployeeById(int id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with id " + id + " not found");
		}
		return convertEmployeeToEmployeeDto(employee);
	}

	private List<EmployeeResponseDTO> convertEmployeeListToEmployeeResponseDTOList(List<Employee> employees) {
		List<EmployeeResponseDTO> employeeList = new ArrayList<EmployeeResponseDTO>();
		for (Employee employee : employees) {
			EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
			employeeResponseDTO.setName(employee.getName());
			employeeResponseDTO.setEmail(employee.getEmail());
			employeeResponseDTO.setDesignation(employee.getDesignation());
			employeeResponseDTO.setActive(employee.isActive());
			employeeList.add(employeeResponseDTO);

		}
		return employeeList;
	}

	@Override
	public EmployeeDTO saveAndUpdateEmployee(EmployeeDTO employeeDTO) {

		Employee newEmployee = convertEmployeeDTOToEmployee(employeeDTO);
		Employee save = null;
		if (newEmployee.getId() == 0) {
			newEmployee = employeeRepository.save(newEmployee);
			return convertEmployeeToEmployeeDto(newEmployee);
		}
		if (newEmployee.getId() != 0) {
			EmployeeDTO employeeById = getEmployeeById(newEmployee.getId());
			if (employeeById == null) {
				throw new EmployeeNotFoundException("Employee with id " + newEmployee.getId() + " not found");
			}
			save = employeeRepository.save(newEmployee);
		}
		return convertEmployeeToEmployeeDto(save);
	}

	private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		employee.setActive(employeeDTO.isActive());
		employee.setDesignation(employeeDTO.getDesignation());
		employee.setEmail(employeeDTO.getEmail());
		employee.setSalary(employeeDTO.getSalary());
		System.out.println(employee);
		return employee;
	}

	private EmployeeDTO convertEmployeeToEmployeeDto(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setDesignation(employee.getDesignation());
		employeeDTO.setActive(employee.isActive());
		employeeDTO.setSalary(employee.getSalary());
		return employeeDTO;
	}

	private List<EmployeeDTO> convertEmployeeListToEmployeeDtoList(List<Employee> employees) {
		List<EmployeeDTO> employeesDto = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			EmployeeDTO dto = new EmployeeDTO();
			dto.setId(employee.getId());
			dto.setName(employee.getName());
			dto.setEmail(employee.getEmail());
			dto.setDesignation(employee.getDesignation());
			dto.setActive(employee.isActive());
			dto.setSalary(employee.getSalary());
			employeesDto.add(dto);
		}
		return employeesDto;

	}

	@Override
	public void deleteEmployee(int id) {
		EmployeeDTO employeeDTO = getEmployeeById(id);
		if (employeeDTO == null) {
			throw new EmployeeNotFoundException("Employee with id " + id + " not found");
		}
		Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
		employeeRepository.delete(employee);

	}

	@Override
	public EmployeeDTO getEmployeeByName(String name) {
		Employee employee = employeeRepository.findByName(name);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with name " + name + " not found");
		}
		return convertEmployeeToEmployeeDto(employee);
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeByActiveTrue(int page,int size,String sortBy,String direction) {
		Pageable pageable = getPageable(page, size, sortBy, direction);
		Page<Employee> pages = employeeRepository.findByActiveTrue(pageable);
		if (pages.getContent()==null) {
			throw new EmployeeNotFoundException("No Employee is Active Currently");
		}
		List<EmployeeDTO> employeeDtoList = convertEmployeeListToEmployeeDtoList(pages.getContent());
		return new PagedResponse<EmployeeDTO>(employeeDtoList, pages.getNumber(), pages.getSize(), pages.getTotalElements(),pages.getTotalPages(), pages.isLast());
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeByActiveFalse(int page,int size,String sortBy, String direction) {
		Pageable pageable=getPageable(page, size, sortBy, direction);
		Page<Employee> page1 = employeeRepository.findByActiveFalse(pageable);
		List<Employee> employees=page1.getContent();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employees are Active Currently");
		}
		List<EmployeeDTO> toEmployeeDtoList = convertEmployeeListToEmployeeDtoList(employees);
		return new PagedResponse<>(toEmployeeDtoList, page1.getNumber(), page1.getSize(), page1.getTotalElements(), page1.getTotalPages(), page1.isLast());
	}

	@Override
	public EmployeeDTO getEmployeeByEmail(String email) {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with name " + email + " not found");
		}
		return convertEmployeeToEmployeeDto(employee);
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeNameStartingWith(String prefix,int page,int size, String sortBy,String direction) {
		Pageable pageable = getPageable(page, size, sortBy, direction);
		Page<Employee> pageEmployees = employeeRepository.findByNameStartingWith(prefix,pageable);
		List<Employee> employees=pageEmployees.getContent();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee starts with " + prefix);
		}
		List<EmployeeDTO> employeeDtoList = convertEmployeeListToEmployeeDtoList(employees);
		return new PagedResponse<>(employeeDtoList, pageEmployees.getNumber(), pageEmployees.getSize(), pageEmployees.getTotalElements(), pageEmployees.getTotalPages(), pageEmployees.isLast());
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation,int page,int size, String sortBy, String direction) {
		Pageable pageable = getPageable(page,size,sortBy,direction);
		Page<Employee> pageEmployees = employeeRepository.findBySalaryGreaterThanAndDesignation(salary, designation,pageable);
		List<Employee> employees=pageEmployees.getContent();
		System.out.println(pageEmployees);
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee matches with the condition : 'salary greater than the '"
					+ salary + "'designation'" + designation);
		}
		List<EmployeeDTO> employeeDtoList = convertEmployeeListToEmployeeDtoList(employees);
		return new PagedResponse<>(employeeDtoList, pageEmployees.getNumber(), pageEmployees.getSize(), pageEmployees.getTotalElements(), pageEmployees.getTotalPages(), pageEmployees.isLast());
	}

	private Pageable getPageable(int page, int size, String sortBy, String direction) {
	    Sort sort = Sort.by(sortBy);
	    if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
	        sort = sort.descending();
	    } else {
	        sort = sort.ascending();
	    }
	    return PageRequest.of(page, size, sort);
	}


	@Override
	public PagedResponse<EmployeeDTO> getEmployeeSalaryBetween(Double startSalary, Double endSalary,int page,int size,String sortBy,String direction) {
		Pageable pageable=getPageable(page, size, sortBy, direction);
		Page<Employee> pageEmployees = employeeRepository.findBySalaryBetween(startSalary, endSalary, pageable);
		List<Employee> employees=pageEmployees.getContent();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException(
					"No Employee matches with the condition : 'salary between '" + startSalary + " 'and' " + endSalary);
		}
		List<EmployeeDTO> employeeDtoList = convertEmployeeListToEmployeeDtoList(employees);
		return new PagedResponse<>(employeeDtoList, pageEmployees.getNumber(), pageEmployees.getSize(), pageEmployees.getTotalElements(), pageEmployees.getTotalPages(), pageEmployees.isLast());
	}

	@Override
	public int getEmployeeCountAndActive() {
		int count = employeeRepository.countByActiveTrue();
		if (count == 0) {
			throw new EmployeeNotFoundException("No Employees active currently");
		}
		return count;
	}

	@Override
	public int getEmployeeCountAndDesignation(String designation) {
		int count = employeeRepository.countByDesignation(designation);
		if (count == 0) {
			throw new EmployeeNotFoundException("No Employees with the designation " + designation);
		}
		return count;
	}

	@Override
	public int countSalaryGreaterthan(double salary) {
		int count = employeeRepository.countBySalaryGreaterThan(salary);
		if (count == 0) {
			throw new EmployeeNotFoundException("No Employees salary greater than " + salary);
		}
		return count;
	}

}
