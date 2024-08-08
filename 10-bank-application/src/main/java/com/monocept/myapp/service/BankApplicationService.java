package com.monocept.myapp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.monocept.myapp.controller.ProfileRequestDto;
import com.monocept.myapp.controller.ProfileResponseDto;
import com.monocept.myapp.dto.CustomerRequestDto;
import com.monocept.myapp.dto.CustomerResponseDto;
import com.monocept.myapp.dto.TransactionResponseDto;
import com.monocept.myapp.util.PagedResponse;

public interface BankApplicationService {


	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

	CustomerResponseDto createAccount(long customerID,int bankID);

	List<CustomerResponseDto> getAllCustomers();

	CustomerResponseDto getCustomerById(long customerid);

	PagedResponse<TransactionResponseDto> getAllTransactions(LocalDateTime fromDate, LocalDateTime toDate, int page, int size,
			String sortBy, String direction);

	TransactionResponseDto performTransaction(long senderAccountNumber, long receiverAccountNumber, double amount);

	List<TransactionResponseDto> getPassbook(long accountNumber);

	ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, String email);

	

}
