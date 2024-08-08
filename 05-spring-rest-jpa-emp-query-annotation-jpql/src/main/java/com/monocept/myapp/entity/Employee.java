package com.monocept.myapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank
	@Size(min = 2,max = 50)
	@Column(name = "name")
	private String name;
	
	@NotBlank(message = "email is empty")
	@Email(message = "email is not well formated")
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Column(name = "designation")
	private String designation;
	
	@NotNull
	@Column(name = "salary")
	private double salary;
	
	@NotNull
	@Column(name = "active")
	private boolean active;

	public Employee(int id, String name, String email, String designation, double salary, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.designation = designation;
		this.salary = salary;
		this.active = active;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", designation=" + designation
				+ ", salary=" + salary + ", active=" + active + "]";
	}
	
	
	
	
	
}
