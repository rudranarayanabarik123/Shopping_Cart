package com.jsp.shopping_cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shopping_cart.dto.Merchant;
import com.jsp.shopping_cart.dto.Product;
import com.jsp.shoppingcart.dao.MerchantDao;
import com.jsp.shoppingcart.dao.ProductDao;

@Controller
public class ProductController {
	@Autowired
	ProductDao dao;
	
	@Autowired
	MerchantDao mdao;

	@RequestMapping("/addproduct")
	public ModelAndView addProduct() {
		Product p=new Product();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productobj", p);
		mav.setViewName("productform");
		return mav;
	}
	
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("productobj") Product p,HttpSession session) {
		Merchant m=(Merchant)session.getAttribute("merchantinfo");
		List<Product> products=m.getProducts();
		if(products.size()>0) {
			products.add(p);
			m.setProducts(products);
		}
		else {
			List<Product> productslist=new ArrayList<Product>();
			productslist.add(p);
			m.setProducts(productslist);
		}
		dao.saveProduct(p);
		mdao.updateMerchant(m);
		ModelAndView mav=new ModelAndView();
		mav.addObject("mesg","product saved successfully");
		mav.setViewName("merchantoptions");
		return mav;
		
	}
	@RequestMapping("/deleteproduct")
	public ModelAndView deleteProduct(@RequestParam("id") int id,HttpSession session) {
		Merchant m=(Merchant)session.getAttribute("merchantinfo");
		Merchant m1=mdao.deleteProductFromMerchant(m.getId(), id);
		mdao.updateMerchant(m1);
		dao.deleteProductById(id);
		session.setAttribute("merchantinfo", m1);//storing updated merchant information with same key so that it will replace the old information
		ModelAndView mav=new ModelAndView();
		mav.setViewName("viewallproducts");
		return mav;
	}
	
	@RequestMapping("/displayproducts")
	public ModelAndView displayProducts() {
		List<Product> products=dao.fetchAllProducts();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productslist", products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}
	@RequestMapping("/displayproductsbybrand")
	public ModelAndView displayProductsByBrand(ServletRequest req) {
		String brand=req.getParameter("brand");
		List<Product> products=dao.findProductByBrand(brand);
		ModelAndView mav=new ModelAndView();
		mav.addObject("productslist", products);
		mav.setViewName("viewallproductstocustomer");
		return mav;
	}
}
