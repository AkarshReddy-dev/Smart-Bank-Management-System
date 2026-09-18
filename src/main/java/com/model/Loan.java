package com.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Loan")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	private String loanType;
	
	private long amount;
	
	private long tenure;
	
	private float interest;
	
	private String loanStatus;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	

	public Loan() {
		super();
	}



	public Loan(int loanId, String loanType, long amount, long tenure, float interest,
			String loanStatus) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.amount = amount;
		this.tenure = tenure;
		this.interest = interest;
		this.loanStatus = loanStatus;
	}



	public Loan(String loanType, long amount, long tenure, float interest, String loanStatus) {
		super();
		this.loanType = loanType;
		this.amount = amount;
		this.tenure = tenure;
		this.interest = interest;
		this.loanStatus = loanStatus;
	}



	public int getLoanId() {
		return loanId;
	}



	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}



	public String getLoanType() {
		return loanType;
	}



	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}



	public long getAmount() {
		return amount;
	}



	public void setAmount(long amount) {
		this.amount = amount;
	}



	public long getTenure() {
		return tenure;
	}



	public void setTenure(long tenure) {
		this.tenure = tenure;
	}



	public float getInterest() {
		return interest;
	}



	public void setInterest(float interest) {
		this.interest = interest;
	}



	public String getLoanStatus() {
		return loanStatus;
	}



	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanType=" + loanType + ", amount="
				+ amount + ", tenure=" + tenure + ", interest=" + interest + ", loanStatus=" + loanStatus + "]";
	}
	
	
	
	
	

}
