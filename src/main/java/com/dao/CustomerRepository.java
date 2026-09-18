package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Customer;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public class CustomerRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Customer customer) {
		
		sessionFactory.getCurrentSession().persist(customer);
		
	}
	
	public Customer getById(long id) {
		
		return sessionFactory.getCurrentSession().get(Customer.class, id);
		
	}
	
	public List<Customer> getByAccountNumber(String accountNumber) {
		
		return sessionFactory.getCurrentSession()
												.createQuery("from Customer c where c.accountNumber=:acc",Customer.class)
												.setParameter("acc", accountNumber)
												.list();
		
	}
	
	public List<Customer> getAll(){
		
		return sessionFactory.getCurrentSession().createQuery("from Customer",Customer.class).list();
		
	}
	
	public void update(Customer customer) {
		
		sessionFactory.getCurrentSession().merge(customer);
		
	}
	
	public void delete(Customer customer) {
		
		sessionFactory.getCurrentSession().remove(customer);
		
	}

	public Customer findByAccountNumber(String accountNumber) {
		
		return sessionFactory.getCurrentSession()
											.createQuery("from Customer c where c.accountNumber = :acc",Customer.class)
											.setParameter("acc", accountNumber)
											.uniqueResult();
	}

}
