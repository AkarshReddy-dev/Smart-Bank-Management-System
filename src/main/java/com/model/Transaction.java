package com.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	
	private LocalDateTime date;
	
	private String type;
	
	private String senderAccountNumber;
	
	private String receiverAccountNumber;
	
	private long amount;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	

	public Transaction() {
		super();
	}



	public Transaction(long transactionId, LocalDateTime date, String type, String senderAccountNumber,
			String receiverAccountNumber, long amount, Customer customer) {
		super();
		this.transactionId = transactionId;
		this.date = date;
		this.type = type;
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amount = amount;
		this.customer = customer;
	}



	public Transaction(LocalDateTime date, String type, String senderAccountNumber, String receiverAccountNumber, long amount,
			Customer customer) {
		super();
		this.date = date;
		this.type = type;
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amount = amount;
		this.customer = customer;
	}



	public long getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}



	public LocalDateTime getDate() {
		return date;
	}



	public void setDate(LocalDateTime date) {
		this.date = date;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}



	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}



	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}



	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}



	public long getAmount() {
		return amount;
	}



	public void setAmount(long amount) {
		this.amount = amount;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", date=" + date + ", type=" + type
				+ ", senderAccountNumber=" + senderAccountNumber + ", receiverAccountNumber=" + receiverAccountNumber
				+ ", amount=" + amount + ", customer=" + customer + "]";
	}
	
	
	
	

}
