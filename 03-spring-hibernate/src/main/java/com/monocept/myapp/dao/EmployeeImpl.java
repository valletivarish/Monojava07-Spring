package com.monocept.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeImpl implements EmployeeDao {
	EntityManager entityManager;

	public EmployeeImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e",Employee.class);
		List<Employee> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		Query query = entityManager.createQuery("select e from Employee e where e.name=?1");
		query.setParameter(1, name);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		Employee e = entityManager.find(Employee.class, employee.getId());
		if(e!=null) {
			entityManager.merge(employee);
		}
		
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		if(employee!=null)
		{
			entityManager.remove(employee);
		}
	}

	@Override
	@Transactional
	public void updateEmployeeUsingQuery(Employee employee) {
		Query query = entityManager.createQuery("update Employee e set e.name=?1,e.salary=?2 where e.id=?3");
		query.setParameter(1, employee.getName());
		query.setParameter(2, employee.getSalary());
		query.setParameter(3, employee.getId());
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		
	}

	@Override
	@Transactional
	public void deleteEmployeeUsingQuery(int id) {
		Query query = entityManager.createQuery("delete from Employee e where e.id=?1");
		query.setParameter(1, id);
		int executeUpdate = query.executeUpdate();
		System.out.println(executeUpdate);
		
	}

}
