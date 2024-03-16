package com.jsp.shoppingcart.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shopping_cart.dto.Merchant;
import com.jsp.shopping_cart.dto.Product;

@Repository
public class MerchantDao {
	@Autowired
	EntityManagerFactory emf;
	
	@Autowired
	ProductDao pdao;
	
	
	public void saveMerchant(Merchant merchant) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(merchant);
		et.commit();
	}

	public Merchant login(String email,String password) {
		EntityManager em=emf.createEntityManager();
		Query query=em.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		
//		Merchant merchant=(Merchant)query.getSingleResult();
//		if(merchant!=null) {
//			return merchant;
//		}
//		else {
//			return null;
//		}
		try {
			Merchant merchant=(Merchant)query.getSingleResult();
			return merchant;
			
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public void updateMerchant(Merchant m) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(m);
		et.commit();
		
	}
	
	public void deleteMerchantById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Merchant m=em.find(Merchant.class, id);
		et.begin();
		em.remove(m);
		et.commit();
	}
	
	public Merchant findMerchantById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		Merchant m=em.find(Merchant.class, id);
		if(m!=null)
			return m;
		else
			return null;
		
	}
	public Merchant deleteProductFromMerchant(int merchant_id,int product_id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Merchant m=em.find(Merchant.class, merchant_id);
		List<Product> products=m.getProducts();
//		products.stream().filter(product->product.getId()!=product_id).collect(Collectors.toList());
//		in place of the above line we are using the list below.
		List<Product> productslist=new ArrayList<Product>();
		for(Product p:products) {
			if(p.getId()!=product_id)
			{
				productslist.add(p);
			}
		}
//		Product p=pdao.findProductById(product_id);
//		products.remove(p);
//		m.setProducts(products);
		
		m.setProducts(productslist);//it is for list of products
		return m;
	}
}
