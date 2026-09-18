package com.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dao.CustomerRepository;
import com.model.Customer;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository)
	{
		this.customerRepository=customerRepository;
	}

	public Customer loginValidation(Customer customer) {
		
		Customer dbCustomer = customerRepository.findByAccountNumber(customer.getAccountNumber());
		
		if(dbCustomer ==  null) {
			
			return null;
		}
		else if (dbCustomer.getPassword().equals( customer.getPassword())) {
			
			return dbCustomer;
			
		}
		else {
			
			return null;
			
		}
		
		
	}

	public void updateCustomerAfterReset(String accountNumber,String password) {
		
		Customer customer = customerRepository.findByAccountNumber(accountNumber);
		customer.setFirstLogin(false);;
		customer.setPassword(password);
		customerRepository.update(customer);	
	}

	public String generateOTP() {
		
		String numbers="1234567890";
		int length=6;
		
		Random random=new Random();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<length;i++) {
			
			int number=random.nextInt(numbers.length());
			sb.append(numbers.charAt(number));
			
		}
		
		String otp=sb.toString();
		
		return otp;
		
	}

	public Customer getByAccountNumber(String accountNumber) {
		
		Customer dbAccountNumber = customerRepository.findByAccountNumber(accountNumber);
		return dbAccountNumber;
		
	}
	
	public Customer getByCustomerId(long id) {
		
		Customer dbAccountNumber = customerRepository.getById(id);
		return dbAccountNumber;
		
	}

	public String updateFundTransfer(Customer customer, String accountNumber, long amount) {
		
		Customer sender = customerRepository.findByAccountNumber(customer.getAccountNumber());
		
		Customer receiver = customerRepository.findByAccountNumber(accountNumber);
		
		if(receiver==null || sender.equals(receiver)) {
			
			return "Invalid Account";
			
		}
		else if (sender.getBalance() >= amount) {
			
			receiver.setBalance(receiver.getBalance() + amount);
			sender.setBalance(sender.getBalance() - amount);
			customerRepository.update(sender);
			customerRepository.update(receiver);
			
			return "Fund Transfer Success!!!";
		}
		else {
			return "Insufficient Balance";
		}
			
			
			
			
	
		
		
	}

	public List<Customer> getAllCustomers() {
		
		List<Customer> allCustomers = customerRepository.getAll();
		
		return allCustomers;
		
	}

	public void updateStatus(Customer customer) {
		
		customerRepository.update(customer);
		
	}
	
	

}
