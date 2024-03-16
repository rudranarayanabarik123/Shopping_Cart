package com.jsp.shoppingcart.helper;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class[] configclasses= {ConfigClass.class};
		return configclasses;
		//return new Class[] {ConfigClass.class};
	}

	@Override
	protected String[] getServletMappings() {
		String[] url= {"/"};
		return url;
		//return new String[] {"/"};
	}

}
