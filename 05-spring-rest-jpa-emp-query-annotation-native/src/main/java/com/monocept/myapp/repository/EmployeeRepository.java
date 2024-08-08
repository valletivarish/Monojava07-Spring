package com.monocept.myapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monocept.myapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query(value="select * from employee where name=:name",nativeQuery = true)
	public Employee findByName(@Param("name") String name);

	@Query(value="select * from employee where active=true",nativeQuery = true)
	public Page<Employee> findByActiveTrue(Pageable pageable);

	@Query(value = "select * from employee where active=false",nativeQuery = true)
	public Page<Employee> findByActiveFalse(Pageable pageable);
	
	@Query(value="select * from employee where email=?1",nativeQuery = true)
	public Employee findByEmail(String email);

//	@Query(value="select * from employee where name like ?1%",nativeQuery=true) //jpql queries 
	@Query(value="select * from employee  where name like :name%",nativeQuery=true) // jpql queries with @param anotation
	public Page<Employee> findByNameStartingWith(@Param("name") String name, Pageable pageable);

	@Query(value="select * from employee where salary>?1 and designation=?2",nativeQuery=true)
	public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary, String designation, Pageable pageable);

	@Query(value="select * from employee where salary between ?1 and ?2",nativeQuery=true)
	public Page<Employee> findBySalaryBetween(Double start, Double end, Pageable pageable);

	@Query(value="select count(*) from employee where active=true",nativeQuery=true)
	public int countByActiveTrue();

	@Query(value="select count(*) from employee where designation=?1",nativeQuery=true)
	public int countByDesignation(String designation);

	@Query(value="select count(*) from employee where salary>?1 and active=true",nativeQuery=true)
	public int countBySalaryGreaterThanAndActive(double salary);
}
