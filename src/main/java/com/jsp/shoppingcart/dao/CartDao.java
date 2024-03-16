package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Cart;

@Repository
public class CartDao {
	
	@Autowired
	EntityManagerFactory emf;
	
	public void saveCart(Cart cart) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(cart);
		et.commit();
	}
	public void updateCart(Cart cart) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(cart);
		et.commit();
	}
	
	public void deleteCartById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Cart cart=em.find(Cart.class, id);
		et.begin();
		em.remove(cart);
		et.commit();	
	}
	
	public Cart findCartById(int id) {
		
	EntityManager em=emf.createEntityManager();
	Cart cart=em.find(Cart.class,id);
	if(cart!=null)
		return cart;
	else 
		return null;
	}
	
	public Cart removeAllItemsFromCart(int id) {
		
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Cart cart=em.find(Cart.class, id);
		cart.setItems(null);
		cart.setTotalprice(0);
		
		et.begin();
		em.merge(cart);
		et.commit();
		
		return cart;
		
	}
	
	public void removeItemFromCartById(int cart_id,int item_id) {
		
	}

}
