package com.monocept.myapp.controller;

import java.util.List;

import com.monocept.myapp.dto.TransactionResponseDto;
import com.monocept.myapp.entity.Bank;
import com.monocept.myapp.entity.Customer;

import lombok.Data;

@Data
public class AccountResponseDto {

	private long accountNumber;


	private Bank bank;

	private Customer customer;

	private List<TransactionResponseDto> sentTransactions;
	
	private List<TransactionResponseDto> receiverTransactions;

	private double balance;
}
