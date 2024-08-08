package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
