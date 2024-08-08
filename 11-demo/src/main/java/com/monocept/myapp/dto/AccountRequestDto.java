package com.monocept.myapp.dto;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class AccountRequestDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;

	private BankRequestDto bankRequestDto;

	private CustomerRequestDto customerRequestDto;

	private List<TransactionRequestDto> sentTransactions;
	
	private List<TransactionRequestDto> receiverTransactions;

	private double balance=1000.0;
}
