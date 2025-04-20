package com.mdp.core.filter;

import javax.servlet.*;
import java.io.IOException;
 
public class SetCharacterEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 if (request.getCharacterEncoding() == null) {  
	            request.setCharacterEncoding("UTF-8");  
	        }  
		 chain.doFilter(request, response);  

	}

	@Override
	public void destroy() {
		

	}

}
