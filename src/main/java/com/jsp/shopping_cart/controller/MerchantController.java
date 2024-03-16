package com.jsp.shopping_cart.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dto.Merchant;
import com.jsp.shoppingcart.dao.MerchantDao;

@Controller
public class MerchantController {
	
	@Autowired
	MerchantDao dao;
	
	@RequestMapping("/addmerchant")
	public ModelAndView addMerchant() {
		Merchant m=new Merchant();
		ModelAndView mav=new ModelAndView();
		mav.addObject("merchantobj", m);
		mav.setViewName("merchantform");
		return mav;
		
	}
	
	@RequestMapping("/savemerchant")
	public ModelAndView saveMerchant(@ModelAttribute("merchantobj")Merchant m) {
		dao.saveMerchant(m);
		ModelAndView mav=new ModelAndView();
		mav.addObject("mesg", "Data saved successfully");
		mav.setViewName("menu");
		return mav;
	}
	
	
	@RequestMapping("/loginvalidation")
	public ModelAndView login(ServletRequest req,HttpSession session) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		Merchant m=dao.login(email, password);
		ModelAndView mav=new ModelAndView();
		
		if(m!=null) {
			mav.addObject("mesg","Login successful");
			mav.setViewName("merchantoptions");
			session.setAttribute("merchantinfo", m);
			return mav;
		}
		else {
			mav.addObject("mesg","Invalid credentials");
			mav.setViewName("merchantloginform");
			return mav;
		}
	}

}
