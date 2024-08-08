package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Account;
import com.monocept.myapp.entity.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query("select coalesce(sum(a.balance), 0) from Account a where a.customer = ?1")
	double getTotalBalance(Customer customer);


}
