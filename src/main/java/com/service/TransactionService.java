package com.service;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerRepository;
import com.dao.TransactionRepository;
import com.model.Customer;
import com.model.Transaction;

@Service
public class TransactionService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	private TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		
		this.transactionRepository=transactionRepository;
		
	}

	public void storeTransaction(Customer customer,String accountNumber,long amount) {
		
		Transaction transaction=new Transaction();
		
		transaction.setCustomer(customer);
		transaction.setDate(LocalDateTime.now());
		transaction.setType("DEBIT");
		transaction.setSenderAccountNumber(customer.getAccountNumber());
		transaction.setReceiverAccountNumber(accountNumber);
		transaction.setAmount(amount);
		
		transactionRepository.save(transaction);
		
		Transaction creditTransaction=new Transaction();
		
		creditTransaction.setCustomer(customerRepository.findByAccountNumber(accountNumber));
		creditTransaction.setDate(LocalDateTime.now());
		creditTransaction.setType("CREDIT");
		creditTransaction.setSenderAccountNumber(customer.getAccountNumber());
		creditTransaction.setReceiverAccountNumber(accountNumber);
		creditTransaction.setAmount(amount);
		
		transactionRepository.save(creditTransaction);
		
	}

	public List<Transaction> getAllTransactions(Customer customer) {
		
		List<Transaction> allTransactions = transactionRepository.getBySenderAccountNumber(customer);
		
		return allTransactions;
	}

	public List<Transaction> getAllTransactions() {
		
		List<Transaction> allTransactions = transactionRepository.getAll();
		
		return allTransactions;
		
	}

	public List<Transaction> getTransactionsByMonth(String month) {
		
		YearMonth yearMonth=YearMonth.parse(month);
		
		LocalDateTime startDate =yearMonth.atDay(1).atTime(00,00);
		LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23,59);
		List<Transaction> transacionsByMonth = transactionRepository.getTransacionsByMonth(month,startDate,endDate);
		
		return transacionsByMonth;
		
	}

	public List<Transaction> getTransactionsByYear(int year) {
		
		Year y=Year.of(year);
		
		LocalDateTime startDate=y.atMonth(1).atDay(1).atTime(00, 00);
		LocalDateTime endDate=y.atMonth(12).atDay(31).atTime(23, 59);
		
		List<Transaction> transactionByYear = transactionRepository.getTransactionByYear(year,startDate,endDate);
		
		return transactionByYear;
		
	}
	
	
	
	

}
