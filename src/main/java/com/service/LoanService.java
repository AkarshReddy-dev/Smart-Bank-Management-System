package com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerRepository;
import com.dao.LoanRepository;
import com.dao.TransactionRepository;
import com.model.Customer;
import com.model.Loan;
import com.model.Transaction;

@Service
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Loan applyLoan(Loan loan) {
		
		String loanType = loan.getLoanType();
		long amount = loan.getAmount();
		long tenure = loan.getTenure();
		
		float interest=0;
		
		if(loan.getLoanType().equals("Personal Loan")) {
			
			interest=12.5f;
		}else if (loan.getLoanType().equals("Home Loan")) {
			interest=13.5f;
		}else if (loan.getLoanType().equals("Education Loan")) {
			
			interest = 14f;
			
		}
		
		loan.setInterest(interest);
		loan.setLoanStatus("Pending");
		loanRepository.save(loan);
		
		System.out.println(loanType);
		System.out.println(interest);
		
		return loan;		
	}
	
	public List<Loan> getAllLoans() {
		
		return loanRepository.getAll();
		
	}
	
public List<Loan> getAllPendingLoans() {
		
		return loanRepository.getAllPendingLoans();
		
	}

	public void updateStatus(int id,String status) {
		
		Loan loan = loanRepository.getById(id);
		loan.setLoanStatus(status);
		
		if(status.equals("ACCEPTED")) {
			loan.getCustomer().setBalance(loan.getCustomer().getBalance()+ loan.getAmount());
			
			Transaction transaction=new Transaction();
			transaction.setCustomer(loan.getCustomer());
			transaction.setType("CREDIT");
			transaction.setSenderAccountNumber("BANK");
			transaction.setReceiverAccountNumber(loan.getCustomer().getAccountNumber());
			transaction.setAmount(loan.getAmount());
			transaction.setDate(LocalDateTime.now());
			
			transactionRepository.save(transaction);
		}
		
		loanRepository.update(loan);
		customerRepository.update(loan.getCustomer());
		
		
		
	}

	public List<Loan> getAllLoansByCustomer(Customer customer) {
		
		List<Loan> allLoans = loanRepository.getAllLoansByCustomer(customer);
		
		return allLoans;
		
	}

}
