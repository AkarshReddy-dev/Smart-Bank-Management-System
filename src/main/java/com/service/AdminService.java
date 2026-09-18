package com.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminRepository;
import com.dao.CustomerRepository;
import com.model.Admin;
import com.model.Customer;

@Service
public class AdminService {
	
	private CustomerRepository customerRepository;
	
	public AdminService(CustomerRepository customerRepository)
	{
		this.customerRepository=customerRepository;
	}
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Customer createCustomer(Customer customer)
	{
		generateAccountNumber(customer);
		generateTPassword(customer);
		
		customer.setStatus(true);
		
		customerRepository.save(customer);
		
		return customer;
	}

	public Customer generateAccountNumber(Customer customer) {
		
		String accountNumber="";
		
		do {
		
		String characters="0123456789";
		int length=11;
		Random random=new Random();
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<length;i++) {
			
			int number=random.nextInt(characters.length());
			sb.append(characters.charAt(number));
			
			
		}
		
		accountNumber=sb.toString();
		
		} while (customerRepository.findByAccountNumber(accountNumber)!=null);
			
		customer.setAccountNumber(accountNumber);
		
		return customer;	
	}

	public Customer generateTPassword(Customer customer) {
		
		String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdfghijklmnopqrstuwxyl!@#$%&*!()_0123456789";
		int length=8;
		Random random=new Random();
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<length;i++) {
			
			int number=random.nextInt(characters.length());
			sb.append(characters.charAt(number));
			
			
		}
		
		String password=sb.toString();
		
		customer.setPassword(password);
		customer.setFirstLogin(true);
		
		return customer;	
		
	}

	public Admin loginValidation(Admin admin) {
		
		Admin dbAdmin = adminRepository.getByName(admin.getName());
		
		if(dbAdmin==null) {
			
			return null;
		}
		else if (admin.getPassword().equals(dbAdmin.getPassword())) {
			
			return dbAdmin;
			
		}
		else {
			
			return null;
		}
		
	}

}
