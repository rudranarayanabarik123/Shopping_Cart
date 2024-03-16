package com.jsp.shopping_cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dto.Cart;
import com.jsp.shopping_cart.dto.Customer;
import com.jsp.shopping_cart.dto.Item;
import com.jsp.shopping_cart.dto.Orders;
import com.jsp.shopping_cart.dto.Product;
import com.jsp.shoppingcart.dao.CartDao;
import com.jsp.shoppingcart.dao.CustomerDao;
import com.jsp.shoppingcart.dao.OrderDao;
import com.jsp.shoppingcart.dao.ProductDao;

@Controller
public class OrdersController {
	
	@Autowired
	OrderDao dao;
	
	@Autowired
	CustomerDao cdao;
	
	@Autowired
	ProductDao pdao;
	
	@Autowired
	CartDao ctdao;
	
	@RequestMapping("/addorder")
	public ModelAndView addOrder() {
		Orders o=new Orders();
		ModelAndView mav=new ModelAndView();
		mav.addObject("ordersobj", o);
		mav.setViewName("ordersform");
		return mav;
		
	}
	@RequestMapping("/saveorder")
	public ModelAndView saveOrder(@ModelAttribute("ordersobj") Orders o,HttpSession session) {
		Customer c=(Customer)session.getAttribute("customerinfo");
		Customer customer=cdao.findCustomerById(c.getId());
		Cart cart=customer.getCart();
		
		List<Item> items=cart.getItems();
				
		List<Item> itemslist=new ArrayList<>();
		List<Item> itemswithGreaterQuantity=new ArrayList<>();
		
		for(Item i:items) {
			Product p=pdao.findProductById(i.getP_id());
			if(i.getQuantity()<p.getStock()) {
				itemslist.add(i);
				p.setStock(p.getStock()-i.getQuantity());
				
				pdao.updateProduct(p);
			}
			else {
				itemswithGreaterQuantity.add(i);
			}
		}
		
		o.setItems(itemslist);
		double totalpriceoforder=0;
		for(Item i:itemslist) {
			totalpriceoforder=totalpriceoforder+i.getPrice();
		}
		
		o.setTotalprice(totalpriceoforder);
		cart.setItems(itemswithGreaterQuantity);
		
		double totalprice=0;
		for(Item i:itemswithGreaterQuantity) {
			totalprice=totalprice+i.getPrice();
			
		}
		
		cart.setTotalprice(totalprice);
		
		List<Orders> orders=customer.getOrders();
		if(orders.size()>0) {
			orders.add(o);
			customer.setOrders(orders);
		}
		else {
			List<Orders> orders1=new ArrayList<>();
			orders1.add(o);
			customer.setOrders(orders1);
		}
		
		customer.setCart(cart);
		ctdao.updateCart(cart);
		
		dao.saveOrder(o);
		cdao.updateCustomer(customer);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("mesg", "order placed successfully");
		mav.addObject("orderdetails", o);
		mav.setViewName("customerBill");
		return mav;
				
				
		
		
		
	}

}
