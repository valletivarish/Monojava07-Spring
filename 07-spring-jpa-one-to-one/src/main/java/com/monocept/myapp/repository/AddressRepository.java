package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
