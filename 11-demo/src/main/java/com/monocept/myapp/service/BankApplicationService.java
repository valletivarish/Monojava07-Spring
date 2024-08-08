package com.monocept.myapp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.monocept.myapp.dto.CustomerRequestDto;
import com.monocept.myapp.dto.CustomerResponseDto;
import com.monocept.myapp.dto.ProfileRequestDto;
import com.monocept.myapp.dto.ProfileResponseDto;
import com.monocept.myapp.dto.TransactionResponseDto;
import com.monocept.myapp.dto.UserResponseDto;
import com.monocept.myapp.util.PagedResponse;

public interface BankApplicationService {


	UserResponseDto createCustomer(CustomerRequestDto customerRequestDto, long userID);

	CustomerResponseDto createAccount(long customerID,int bankID);

	List<CustomerResponseDto> getAllCustomers();

	CustomerResponseDto getCustomerById(long customerid);

	PagedResponse<TransactionResponseDto> getAllTransactions(LocalDateTime fromDate, LocalDateTime toDate, int page, int size,
			String sortBy, String direction);

	TransactionResponseDto performTransaction(long senderAccountNumber, long receiverAccountNumber, double amount);

	PagedResponse<TransactionResponseDto> getPassbook(long accountNumber, LocalDateTime fromDate, LocalDateTime toDate, int page, int size, String sortBy, String direction);

	String updateProfile(ProfileRequestDto profileRequestDto);

	

}
