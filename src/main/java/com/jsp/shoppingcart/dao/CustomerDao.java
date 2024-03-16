package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Customer;

@Repository
public class CustomerDao {
	@Autowired
	EntityManagerFactory emf;
	
	
	public void saveCustomer(Customer customer) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(customer);
		et.commit();
	}

	public Customer login(String email,String password) {
		EntityManager em=emf.createEntityManager();
		
		Query query=em.createQuery("select c from Customer c where c.email=?1 and c.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		try {
			Customer customer=(Customer)query.getSingleResult();
			return customer;
		}catch(NoResultException e) {
			return null;
			
		}
//		Customer customer=(Customer)query.getSingleResult();
//		if(customer!=null) {
//			return customer;
//		}
//		else {
//			return null;
//		}
	}
	
	public void updateCustomer(Customer c) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(c);
		et.commit();
		
	}
	
	public void deleteCustomerById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Customer c=em.find(Customer.class, id);
		et.begin();
		em.remove(c);
		et.commit();
	}
	
	public Customer findCustomerById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Customer c=em.find(Customer.class, id);
		if(c!=null)
			return c;
		else
			return null;
		
	}

}
