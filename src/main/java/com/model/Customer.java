package com.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String mobileNumber;
	
	@Column(unique = true, nullable = false)
	private String accountNumber;
	
	@Column(nullable = false)
	private String password;
	
	private long balance;
	
	private boolean firstLogin;
	
	private boolean status;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Loan> loan;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Transaction> transaction;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public List<Loan> getLoan() {
		return loan;
	}

	public void setLoan(List<Loan> loan) {
		this.loan = loan;
	}

	public Customer() {
		super();
	}

	

	public Customer(long customerId, String name, String email, String mobileNumber, String accountNumber,
			String password, long balance, boolean firstLogin, boolean status, List<Loan> loan,
			List<Transaction> transaction) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountNumber = accountNumber;
		this.password = password;
		this.balance = balance;
		this.firstLogin = firstLogin;
		this.status = status;
		this.loan = loan;
		this.transaction = transaction;
	}
	
	

	public Customer(String name, String email, String mobileNumber, String accountNumber, String password, long balance,
			boolean firstLogin, boolean status, List<Loan> loan, List<Transaction> transaction) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountNumber = accountNumber;
		this.password = password;
		this.balance = balance;
		this.firstLogin = firstLogin;
		this.status = status;
		this.loan = loan;
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", accountNumber=" + accountNumber + ", password=" + password + ", balance=" + balance
				+ ", firstLogin=" + firstLogin + ", status=" + status + ", loan=" + loan + ", transaction="
				+ transaction + "]";
	}

	
	
	
	

	

	
	
	
	
		
	
	
	

}
