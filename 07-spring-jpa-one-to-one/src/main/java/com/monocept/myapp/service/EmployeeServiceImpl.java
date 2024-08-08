package com.monocept.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.monocept.myapp.entity.Address;
import com.monocept.myapp.entity.Employee;
import com.monocept.myapp.repository.AddressRepository;
import com.monocept.myapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
	private AddressRepository addressRepository;
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.addressRepository = addressRepository;
	}
	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}
	@Override
	public Employee save(Employee employee) {
		if(employee.getId()!=0 && employee.getAddress().getId()!=0) {
			return employeeRepository.save(employee);
		}
		Employee employeeById = findById(employee.getId());
		if(employeeById!=null) {
			return employeeRepository.save(employee);
		}
		return employeeRepository.save(employee);
		
	}
	@Override
	public Employee findById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}
	@Override
	public String delete(int id) {
		Employee byId = findById(id);
		if(byId!=null) {
			employeeRepository.delete(byId);
			return "deleted";
		}
		return "id not found";

	}
	
	@Override
	public Employee employeeWithAddress(int id) {
		Address address = addressRepository.findById(id).orElse(null);
		if(address!=null) {
			Employee employee = address.getEmployee();
			return employee;
		}
		return null;
	}
	
	
	
	
	
	
	
}
