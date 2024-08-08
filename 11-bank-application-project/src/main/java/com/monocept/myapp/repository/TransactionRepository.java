package com.monocept.myapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monocept.myapp.entity.Account;
import com.monocept.myapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	Page<Transaction> findAllByTransactionDateBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
	
	@Query("select t from Transaction t where (t.senderAccount = :account or t.receiverAccount = :account) and t.transactionDate between :from and :to")
	Page<Transaction> getPassbook(@Param("account") Account account, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to, Pageable pageable);


	
//	 @Query("select new TransactionResponseDto(t.amount, t.id, t.receiverAccount, t.senderAccount, t.transactionDate, " +
//	           "case when t.senderAccount = :account then 'Debited' else 'Credited' end) " +
//	           "from Transaction t where t.senderAccount = :account or t.receiverAccount = :account")
//	    List<TransactionResponseDto> getPassbook(Account account);
}
