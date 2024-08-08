package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Long>{

}
