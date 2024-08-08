package com.monocept.myapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.monocept.myapp.entity.Bank;
import com.monocept.myapp.entity.Customer;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class AccountResponseDto {

	private long accountNumber;


	private Bank bank;

	private Customer customer;

	private List<TransactionResponseDto> sentTransactions;
	
	private List<TransactionResponseDto> receiverTransactions;

	private double balance;
}
