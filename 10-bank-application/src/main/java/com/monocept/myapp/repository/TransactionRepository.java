package com.monocept.myapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monocept.myapp.entity.Account;
import com.monocept.myapp.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	Page<Transaction> findAllByTransactionDateBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
	
	@Query("select t from Transaction t where t.senderAccount = :account or t.receiverAccount = :account")
	List<Transaction> getPassbook(@Param("account") Account account);

	
//	 @Query("select new TransactionResponseDto(t.amount, t.id, t.receiverAccount, t.senderAccount, t.transactionDate, " +
//	           "case when t.senderAccount = :account then 'Debited' else 'Credited' end) " +
//	           "from Transaction t where t.senderAccount = :account or t.receiverAccount = :account")
//	    List<TransactionResponseDto> getPassbook(Account account);
}
