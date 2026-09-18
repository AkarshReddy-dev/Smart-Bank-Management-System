package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.Loan;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public class LoanRepository{
	
	@Autowired
	private SessionFactory sessionFactory;
	
public void save(Loan loan) {
		
		sessionFactory.getCurrentSession().persist(loan);
		
	}
	
	public Loan getById(long id) {
		
		return sessionFactory.getCurrentSession().get(Loan.class, id);
		
	}
	
	public List<Loan> getByAccountNumber(String accountNumber) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Loan l where l.accountNumber=:acc",Loan.class)
												.setParameter("acc", accountNumber)
												.list();
		
	}
	
	public List<Loan> getAll(){
		
		return sessionFactory.getCurrentSession().createQuery("from Loan",Loan.class).list();
		
	}
	
	public void update(Loan loan) {
		
		sessionFactory.getCurrentSession().merge(loan);
		
	}
	
	public void delete(Loan loan) {
		
		sessionFactory.getCurrentSession().remove(loan);
		
	}
	
	public List<Loan> getAllPendingLoans(){
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Loan l where l.loanStatus=:status",Loan.class)
												.setParameter("status", "Pending")
												.list();
	}

	public List<Loan> getAllLoansByCustomer(Customer customer) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Loan l where l.customer=:cust",Loan.class)
												.setParameter("cust", customer)
												.list();
		
	}

}
