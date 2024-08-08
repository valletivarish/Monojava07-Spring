package com.monocept.myapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monocept.myapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("select e from Employee e where e.name=?1")
	public Employee findByName(String name);

	@Query("select e from Employee e where e.active=true")
	public Page<Employee> findByActiveTrue(Pageable pageable);

	@Query("select e from Employee e where e.active=false")
	public Page<Employee> findByActiveFalse(Pageable pageable);

	@Query("select e from Employee e where e.email=?1")
	public Employee findByEmail(String email);

//	@Query("select e from Employee e where e.name like ?1%") //jpql queries 
	@Query("select e from Employee e where e.name like :name%") // jpql queries with @param anotation
	public Page<Employee> findByNameStartingWith(@Param("name") String name, Pageable pageable);

	@Query("select e from Employee e where e.salary>?1 and e.designation=?2")
	public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary, String designation, Pageable pageable);

	@Query("select e from Employee e where e.salary between ?1 and ?2")
	public Page<Employee> findBySalaryBetween(Double start, Double end, Pageable pageable);

	@Query("select count(e) from Employee e where e.active=true")
	public int countByActiveTrue();

	@Query("select count(e) from Employee e where e.designation=?1")
	public int countByDesignation(String designation);

	@Query("select count(e) from Employee e where e.salary>?1 and e.active=true")
	public int countBySalaryGreaterThanAndActive(double salary);
}
