package com.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Admin;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public class AdminRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Admin admin) {
		
		sessionFactory.getCurrentSession().persist(admin);
		
	}
	
	public Admin getById(long id) {
		
		return sessionFactory.getCurrentSession().get(Admin.class, id);
		
	}
	
	public void update(Admin admin) {
		
		sessionFactory.getCurrentSession().merge(admin);
		
	}
	
	public void delete(Admin admin) {
		
		sessionFactory.getCurrentSession().remove(admin);
		
	}
	
	public Admin getByName(String name) {
		
		return sessionFactory.getCurrentSession()
										.createQuery("from Admin a where a.name=:name",Admin.class)
										.setParameter("name",name)
										.uniqueResult();
		
	}

}
