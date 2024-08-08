package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

}
