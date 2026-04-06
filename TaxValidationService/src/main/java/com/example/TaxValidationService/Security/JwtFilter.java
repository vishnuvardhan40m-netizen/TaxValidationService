package com.example.TaxValidationService.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtService jwtService;
	@Autowired
	ApplicationContext context;
	

	  @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) {
		  System.out.println(request.getServletPath());
		 
	        return request.getServletPath().equals("/Login")||(request.getServletPath().equals("/Signup"));}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Hello12");
		String token=request.getHeader("Authorization");
		token=token.substring(7);
		String username=jwtService.getUsername(token);
		if((token!=null)&&username!=null ) {
			Userddeatils user=(Userddeatils) context.getBean(UserDetailService.class).loadUserByUsername(username);
		if(user!=null&&jwtService.validateToken(token)){
			UsernamePasswordAuthenticationToken  auth =  new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			System.out.println("Hello1");
		}
		
		}
		filterChain.doFilter(request, response );
	}

}
