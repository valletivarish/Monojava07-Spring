package com.monocept.myapp.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.myapp.dto.AccountResponseDto;
import com.monocept.myapp.dto.CustomerRequestDto;
import com.monocept.myapp.dto.CustomerResponseDto;
import com.monocept.myapp.dto.ProfileRequestDto;
import com.monocept.myapp.dto.ProfileResponseDto;
import com.monocept.myapp.dto.TransactionResponseDto;
import com.monocept.myapp.dto.UserResponseDto;
import com.monocept.myapp.entity.Account;
import com.monocept.myapp.entity.Bank;
import com.monocept.myapp.entity.Customer;
import com.monocept.myapp.entity.Transaction;
import com.monocept.myapp.entity.TransactionType;
import com.monocept.myapp.entity.User;
import com.monocept.myapp.exception.NoRecordFoundException;
import com.monocept.myapp.repository.AccountRepository;
import com.monocept.myapp.repository.BankRepository;
import com.monocept.myapp.repository.CustomerRespository;
import com.monocept.myapp.repository.TransactionRepository;
import com.monocept.myapp.repository.UserRepository;
import com.monocept.myapp.util.PagedResponse;

@Service
public class BankApplicationServiceImpl implements BankApplicationService {

	private TransactionRepository transactionRepository;
	private CustomerRespository customerRespository;
	private AccountRepository accountRepository;
	private BankRepository bankRepository;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public BankApplicationServiceImpl(TransactionRepository transactionRepository,
			CustomerRespository customerRespository, AccountRepository accountRepository, BankRepository bankRepository,
			UserRepository userRepository,PasswordEncoder passwordEncoder) {
		super();
		this.transactionRepository = transactionRepository;
		this.customerRespository = customerRespository;
		this.accountRepository = accountRepository;
		this.bankRepository = bankRepository;
		this.userRepository = userRepository;
		this.passwordEncoder=passwordEncoder;
	}

	private List<TransactionResponseDto> convertTransactionToTransactionResponseDTO(List<Transaction> transactions) {
		List<TransactionResponseDto> transactionDtos = new ArrayList<TransactionResponseDto>();
		for (Transaction transaction : transactions) {
			transactionDtos.add(convertTransactionToTransactionResponseDTO(transaction));
		}
		return transactionDtos;
	}

	private TransactionResponseDto convertTransactionToTransactionResponseDTO(Transaction transaction) {
		TransactionResponseDto responseDto = new TransactionResponseDto();
		responseDto.setId(transaction.getId());
		responseDto.setSenderAccount(convertAccountToAccountResponseDto(transaction.getSenderAccount()));
		responseDto.setReceiverAccount(convertAccountToAccountResponseDto(transaction.getReceiverAccount()));
		responseDto.setTransactionType(transaction.getTransactionType());
		responseDto.setTransactionDate(transaction.getTransactionDate());
		responseDto.setAmount(transaction.getAmount());
		return responseDto;
	}

	private AccountResponseDto convertAccountToAccountResponseDto(Account account) {
		AccountResponseDto accountResponseDTO = new AccountResponseDto();
		accountResponseDTO.setAccountNumber(account.getAccountNumber());
		accountResponseDTO.setBalance(account.getBalance());
		return accountResponseDTO;
	}

	@Override
	public UserResponseDto createCustomer(CustomerRequestDto customerRequestDto, long userID) {
		User user = userRepository.findById(userID).orElse(null);
		if (user == null) {
			throw new NoRecordFoundException("User not found with the following id " + userID);
		}
		if (user.getCustomer() != null) {
			throw new NoRecordFoundException("Customer already assigned cannot create another customer to the user");
		}
		Customer customer = convertCustomerRequestToCustomer(customerRequestDto);
		user.setCustomer(customer);
		return convertUserToUserDto(userRepository.save(user));
	}

	private UserResponseDto convertUserToUserDto(User save) {
		UserResponseDto responseDto = new UserResponseDto();
		responseDto.setId(save.getId());
		responseDto.setCustomerResponseDto(convertCustomerToCustomerResponseDto(save.getCustomer()));
		responseDto.setEmail(save.getEmail());
		return responseDto;
	}

	private CustomerResponseDto convertCustomerToCustomerResponseDto(Customer save) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setCustomer_id(save.getCustomer_id());
		customerResponseDto.setFirstName(save.getFirstName());
		customerResponseDto.setLastName(save.getLastName());
		customerResponseDto.setTotalBalance(save.getTotalBalance());
		return customerResponseDto;
	}

	private Customer convertCustomerRequestToCustomer(CustomerRequestDto customerRequestDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerRequestDto.getFirstName());
		customer.setLastName(customerRequestDto.getLastName());
		customer.setTotalBalance(customerRequestDto.getTotalBalance());
//		customer.setAccounts(convertAccountRequestDtoToAccount(customerRequestDto.getAccounts()));
		return customer;
	}

//	private Account convertAccountRequestDtoToAccount(AccountRequestDto accountRequestDTO) {
//		Account account = new Account();
//		account.setAccountNumber(accountRequestDTO.getAccountNumber());
//		account.setBalance(accountRequestDTO.getBalance());
//		account.setBank(convertBankRequestDtoToBank(accountRequestDTO.getBankRequestDto()));
//		account.setReceiverTransactions(
//				convertTransactionRequestDtoToTransaction(accountRequestDTO.getReceiverTransactions()));
//		return account;
//	}

//	private List<Transaction> convertTransactionRequestDtoToTransaction(
//			List<TransactionRequestDto> receiverTransactions) {
//
//		return null;
//	}

//	private Bank convertBankRequestDtoToBank(BankRequestDto bankRequestDto) {
//		Bank bank = new Bank();
//		bank.setBank_id(bankRequestDto.getBank_id());
//		bank.setFullName(bankRequestDto.getFullName());
//		bank.setAbbreviation(bankRequestDto.getAbbreviation());
//		return bank;
//	}

	@Override
	public CustomerResponseDto createAccount(long customerID, int bankID) {
		Customer customer = customerRespository.findById(customerID).orElse(null);
		if (customer == null) {
			throw new NoRecordFoundException("Customer with id " + customerID + " is not found");
		}
		Bank bank = bankRepository.findById(bankID).orElse(null);
		if (bank == null) {
			throw new NoRecordFoundException("Bank with id " + bankID + " is not found");
		}
		Account account = new Account();
		account.setBalance(1000);
		account.setCustomer(customer);
		account.setBank(bank);
		customer.addAccount(account);
		double totalSalary = 1000;
		if (accountRepository.getTotalBalance(customer) != 0) {
			totalSalary += accountRepository.getTotalBalance(customer);
		}

		customer.setTotalBalance(totalSalary);
		customerRespository.save(customer);

		return convertCustomerAccountToCustomerResponseDto(customer);
	}

	private CustomerResponseDto convertCustomerAccountToCustomerResponseDto(Customer customer) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setFirstName(customer.getFirstName());
		customerResponseDto.setLastName(customer.getLastName());
		customerResponseDto.setCustomer_id(customer.getCustomer_id());
		customerResponseDto.setAccounts(convertAccountToAccountResponseDto(customer.getAccounts()));
		customerResponseDto.setTotalBalance(customer.getTotalBalance());

		return customerResponseDto;
	}

	private List<AccountResponseDto> convertAccountToAccountResponseDto(List<Account> accounts) {
		List<AccountResponseDto> accountResponseDTOs = new ArrayList<>();
		for (Account account : accounts) {
			accountResponseDTOs.add(convertAccountToAccountResponseDto(account));
		}
		return accountResponseDTOs;
	}

	@Override
	public List<CustomerResponseDto> getAllCustomers() {
		return convertCustomerToCustomerResponseDto(customerRespository.findAll());
	}

	private List<CustomerResponseDto> convertCustomerToCustomerResponseDto(List<Customer> customers) {
		List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerResponseDto customerResponseDto = new CustomerResponseDto();
			customerResponseDto.setCustomer_id(customer.getCustomer_id());
			customerResponseDto.setFirstName(customer.getFirstName());
			customerResponseDto.setLastName(customer.getLastName());
			customerResponseDto.setTotalBalance(customer.getTotalBalance());
			customerResponseDto.setAccounts(convertAccountToAccountResponseDto(customer.getAccounts()));
			customerResponseDtos.add(customerResponseDto);
		}
		return customerResponseDtos;
	}

	@Override
	public CustomerResponseDto getCustomerById(long customerid) {
		Customer customer = customerRespository.findById(customerid).orElse(null);
		if (customer == null) {
			throw new NoRecordFoundException("No customer found with the id " + customerid);
		}
		return convertCustomerAccountToCustomerResponseDto(customer);
	}

	@Override
	public PagedResponse<TransactionResponseDto> getAllTransactions(LocalDateTime fromDate, LocalDateTime toDate,
			int page, int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase("desc")) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}
		fromDate = fromDate.truncatedTo(ChronoUnit.SECONDS);
		toDate = toDate.truncatedTo(ChronoUnit.SECONDS);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		System.out.println("Page request: " + pageRequest);
		Page<Transaction> pagedResponse = transactionRepository.findAllByTransactionDateBetween(fromDate, toDate,
				pageRequest);
		System.out.println(
				"Fetched transactions: " + convertTransactionToTransactionResponseDTO(pagedResponse.getContent()));
		PagedResponse<TransactionResponseDto> response = new PagedResponse<>(
				convertTransactionToTransactionResponseDTO(pagedResponse.getContent()), pagedResponse.getNumber(),
				pagedResponse.getSize(), pagedResponse.getTotalElements(), pagedResponse.getTotalPages(),
				pagedResponse.isLast());
		return response;
	}

	@Override
	public TransactionResponseDto performTransaction(long senderAccountNumber, long receiverAccountNumber,
			double amount) {
		Optional<User> user = userRepository.findByEmail(getUsernameFromSecurityContext());
		List<Account> accounts = user.get().getCustomer().getAccounts();
		for (Account account : accounts) {
			if (account.getAccountNumber() == senderAccountNumber) {
				Account senderAccount = accountRepository.findById(senderAccountNumber).orElse(null);
				Account receiverAccount = accountRepository.findById(receiverAccountNumber).orElse(null);
				if (senderAccount == null || receiverAccount == null) {
					throw new NoRecordFoundException("Please check the sender account number " + senderAccountNumber
							+ " and receiver account number " + receiverAccountNumber);
				}
				if (senderAccount.equals(receiverAccount)) {
					throw new NoRecordFoundException("self transfer to the same account number not possible");
				}
				if (senderAccount.getBalance() < amount) {
					throw new NoRecordFoundException("Insufficient Funds please check the balance again");
				}
				senderAccount.setBalance(senderAccount.getBalance() - amount);
				receiverAccount.setBalance(receiverAccount.getBalance() + amount);
				accountRepository.save(senderAccount);
				accountRepository.save(receiverAccount);
				Customer senderCustomer = senderAccount.getCustomer();
				Customer receiverCustomer = receiverAccount.getCustomer();
				senderCustomer.setTotalBalance(senderCustomer.getTotalBalance() - amount);
				receiverCustomer.setTotalBalance(receiverCustomer.getTotalBalance() + amount);

				customerRespository.save(senderCustomer);
				customerRespository.save(receiverCustomer);
				Transaction transaction = new Transaction();
				transaction.setAmount(amount);
				transaction.setSenderAccount(senderAccount);
				transaction.setReceiverAccount(receiverAccount);
				transaction.setTransactionType(TransactionType.Transfer);
				return convertTransactionToTransactionResponseDTO(transactionRepository.save(transaction));
			}
		}
		throw new NoRecordFoundException("Your account number is wrong");

	}

	@Override
	public PagedResponse<TransactionResponseDto> getPassbook(long accountNumber, LocalDateTime from, LocalDateTime to, int page,
			int size, String sortBy, String direction) {
		Sort sort = Sort.by(sortBy);
		if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
			sort.descending();
		} else {
			sort.ascending();
		}

		String email = getUsernameFromSecurityContext();
		Optional<User> user = userRepository.findByEmail(email);
		if(user.get().getCustomer()==null) {
			throw new NoRecordFoundException("still you havn't have customer id");
		}
		List<Account> accounts = user.get().getCustomer().getAccounts();
		for (Account acc : accounts) {
			if (acc.getAccountNumber() == accountNumber) {
				Account account = accountRepository.findById(accountNumber).orElse(null);
//				if (account == null) {
//					throw new NoRecordFoundException(
//							"No account found with the account number " + accountNumber + " Please check again");
//				}
				PageRequest pageRequest = PageRequest.of(page, size, sort);
				Page<Transaction> pagedResponse = transactionRepository.getPassbook(account, to,from,pageRequest);

				return new PagedResponse<TransactionResponseDto>(
						convertTransactionToTransactionResponseDTO(pagedResponse.getContent(), accountNumber),
						pagedResponse.getNumber(), pagedResponse.getSize(), pagedResponse.getTotalElements(),
						pagedResponse.getTotalPages(), pagedResponse.isLast());
			}
		}
		throw new NoRecordFoundException("Please give valid account number");

	}

	private List<TransactionResponseDto> convertTransactionToTransactionResponseDTO(List<Transaction> passbook,
			long accountNumber) {
		List<TransactionResponseDto> list = new ArrayList<>();
		for (Transaction transaction : passbook) {
			TransactionResponseDto responseDto = new TransactionResponseDto();
			responseDto.setAmount(transaction.getAmount());
			responseDto.setSenderAccount(convertAccountToAccountResponseDto(transaction.getSenderAccount()));
			responseDto.setReceiverAccount(convertAccountToAccountResponseDto(transaction.getReceiverAccount()));
			responseDto.setId(transaction.getId());
			responseDto.setTransactionDate(transaction.getTransactionDate());
			if (transaction.getSenderAccount().getAccountNumber() == accountNumber) {
				responseDto.setTransactionType(TransactionType.Debited);
			} else {
				responseDto.setTransactionType(TransactionType.Credited);
			}
			list.add(responseDto);
		}
		return list;
	}

	@Override
	public String updateProfile(ProfileRequestDto profileRequestDto) {
		User user = userRepository.findByEmail(getUsernameFromSecurityContext()).orElse(null);
		if(user.getCustomer()==null) {
			throw new NoRecordFoundException("Cannot update the customer details still you havn't have customer id");
		}
		Customer customer = user.getCustomer();
		if(profileRequestDto.getEmail()!=null && !profileRequestDto.getEmail().isEmpty() && profileRequestDto.getEmail().length()!=0) {
			user.setEmail(profileRequestDto.getEmail());  
		}
		if(profileRequestDto.getFirstName()!=null && !profileRequestDto.getFirstName().isEmpty() && profileRequestDto.getFirstName().length()!=0) {
			customer.setFirstName(profileRequestDto.getFirstName());
		}
		if(profileRequestDto.getLastName()!=null && !profileRequestDto.getLastName().isEmpty() && profileRequestDto.getLastName().length()!=0) {
			customer.setLastName(profileRequestDto.getLastName());
		}
		if(profileRequestDto.getPassword()!=null && !profileRequestDto.getPassword().isEmpty() && profileRequestDto.getPassword().length()!=0) {
			user.setPassword(passwordEncoder.encode(profileRequestDto.getPassword()));
		}

		userRepository.save(user);
		
		return "user succesfully updated";
	}

	private String getUsernameFromSecurityContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return userDetails.getUsername();
		}
		return null;
	}

}
