package com.example.TaxValidationService.Security;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.TaxValidationService.Model.UsersData;

public class Userddeatils implements UserDetails {

	
	UsersData user;
	
	public UsersData getUser() {
		return user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(user.getRole());
		
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	public Userddeatils(UsersData user) {
		super();
		this.user = user;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

}
