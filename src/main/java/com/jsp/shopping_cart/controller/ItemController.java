package com.jsp.shopping_cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dto.Cart;
import com.jsp.shopping_cart.dto.Customer;
import com.jsp.shopping_cart.dto.Item;
import com.jsp.shopping_cart.dto.Product;
import com.jsp.shoppingcart.dao.CartDao;
import com.jsp.shoppingcart.dao.CustomerDao;
import com.jsp.shoppingcart.dao.ItemDao;
import com.jsp.shoppingcart.dao.ProductDao;

@Controller
public class ItemController {
	@Autowired
	ItemDao dao;
	
	@Autowired
	ProductDao pdao;
	
	@Autowired
	CartDao cdao;
	
	@Autowired
	CustomerDao cudao;
	
	@RequestMapping("/additem")
	public ModelAndView addItem(@RequestParam("id") int id) {
		Product p=pdao.findProductById(id);
		ModelAndView mav=new ModelAndView();
		mav.addObject("productobj", p);
		mav.setViewName("itemform");
		return mav;
		
	}
	
	@RequestMapping("/additemtocart")
	public ModelAndView addItemToCart(ServletRequest req,HttpSession session) {
		int product_id=Integer.parseInt(req.getParameter("id"));
		String brand=req.getParameter("brand");
		double price=Double.parseDouble(req.getParameter("price"));
		String model=req.getParameter("model");
		String category=req.getParameter("category");
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		
		
		Item item=new Item();
		item.setBrand(brand);
		item.setCategory(category);
		item.setModel(model);
		item.setQuantity(quantity);
		item.setP_id(product_id);
		item.setPrice(quantity*price);
		
		Customer customer=(Customer)session.getAttribute("customerinfo");
		Cart c=customer.getCart();
		
		if(c==null) {
			double totalprice=0;
			Cart cart=new Cart();
			List<Item> items=new ArrayList<>();
			items.add(item);
			
			cart.setItems(items);
			cart.setName(customer.getName());
			for(Item i:items) {
				totalprice=totalprice+i.getPrice();
			}
			cart.setTotalprice(totalprice);
			
			customer.setCart(cart);
			
			dao.saveItem(item);
			cdao.saveCart(cart);
			
			cudao.updateCustomer(customer);
			
			
			
		}
		else {
			List<Item> items=c.getItems();
			if(items.size()>0)
			{
				items.add(item);
				c.setItems(items);
				double totalprice=0;
				for(Item i:items) {
					totalprice=totalprice+i.getPrice();
				}
				c.setTotalprice(totalprice);
				customer.setCart(c);
				
				dao.saveItem(item);
				cdao.updateCart(c);
				cudao.updateCustomer(customer);
			}
			else {
				List<Item> itemslist=new ArrayList<>();
				itemslist.add(item);
				c.setItems(itemslist);
				c.setTotalprice(item.getPrice());
				
				dao.saveItem(item);
				cdao.updateCart(c);
				cudao.updateCustomer(customer);
			}
			
		}
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect://displayproducts");/*redirect is used to send request from 
														one end point to another end point*/
		return mav;
	}
	

}
