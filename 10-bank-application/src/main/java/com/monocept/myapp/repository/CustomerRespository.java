package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Customer;

public interface CustomerRespository extends JpaRepository<Customer, Long>{

}
