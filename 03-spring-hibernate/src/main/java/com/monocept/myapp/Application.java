package com.monocept.myapp;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monocept.myapp.dao.EmployeeDao;
import com.monocept.myapp.entity.Employee;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	EmployeeDao employeeDao;
	
	
	

	public Application(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		//addEmployee();
		//getAllEmployees();
		//getEmployeeById();
		//getEmployeeByName();
		//updateEmployee();
		//deleteEmployee();
		//updateEmployeeUsingQuery();
		deleteEmployeeUsingQuery();
		
	}

	private void deleteEmployeeUsingQuery() {
		employeeDao.deleteEmployeeUsingQuery(10);
		
	}

	private void updateEmployeeUsingQuery() {
		Employee employee=new Employee(10,"shivamani",20000);
		employeeDao.updateEmployeeUsingQuery(employee);
		getEmployeeById();
		
	}

	private void deleteEmployee() {
		employeeDao.deleteEmployee(8);
		
	}

	private void updateEmployee() {
		Employee employee=new Employee(8,"varish valleti",2000000);
		employeeDao.updateEmployee(employee);
		
	}

	private void getEmployeeByName() {
		List<Employee> employees=employeeDao.getEmployeeByName("varish");
		for(Employee employee:employees) {
			System.out.println(employee);
		}
		
	}

	private void getEmployeeById() {
		int id=10;
		Employee employee=employeeDao.getEmployeeById(id);
		System.out.println(employee);
		
	}

	private void getAllEmployees() {
		List<Employee> employees=employeeDao.getAllEmployees();
		for(Employee employee:employees) {
			System.out.println(employee);
		}
		
	}

	private void addEmployee() {
		Employee employee=new Employee("ajay",10000);
		employeeDao.addEmployee(employee);
		
	}

}
