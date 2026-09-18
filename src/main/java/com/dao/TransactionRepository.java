package com.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.Transaction;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public class TransactionRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Transaction transaction) {
		
		sessionFactory.getCurrentSession().persist(transaction);
		
	}
	
	public Transaction getById(long id) {
		
		return sessionFactory.getCurrentSession().get(Transaction.class, id);
		
	}
	
	public List<Transaction> getBySenderAccountNumber(Customer customer) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Transaction t where t.customer=:cust",Transaction.class)
												.setParameter("cust", customer)
												.list();
		
	}
	
	public List<Transaction> getByReceiverAccountNumber(String receiverAccountNumber) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Transaction t where t.receiverAccountNumber=:acc",Transaction.class)
												.setParameter("acc", receiverAccountNumber)
												.list();
		
	}
	
	public List<Transaction> getAll(){
		
		return sessionFactory.getCurrentSession().createQuery("from Transaction",Transaction.class).list();
		
	}
	
	public void update(Transaction transaction) {
		
		sessionFactory.getCurrentSession().merge(transaction);
		
	}
	
	public void delete(Transaction transaction) {
		
		sessionFactory.getCurrentSession().remove(transaction);
		
	}

	public List<Transaction> getTransacionsByMonth(String month,LocalDateTime startDate,LocalDateTime endDate) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Transaction t where t.date between :startDate and :endDate ",Transaction.class)
												.setParameter("startDate", startDate)
												.setParameter("endDate", endDate)
												.list();
		
	}

	public List<Transaction> getTransactionByYear(int year, LocalDateTime startDate, LocalDateTime endDate) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Transaction t where t.date between :startDate and :endDate ",Transaction.class)
												.setParameter("startDate", startDate)
												.setParameter("endDate", endDate)
												.list();
		
	}

}
