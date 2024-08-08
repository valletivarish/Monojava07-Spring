package com.monocept.myapp.dto;

import java.time.LocalDateTime;

import com.monocept.myapp.entity.TransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class TransactionRequestDto {
	private long id;
	
	private AccountRequestDto senderAccount;
	
	private AccountRequestDto receiverAccount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	
	private LocalDateTime transactionDate;
	
	@NotNull
	private double amount;
}
