package com.example.TaxValidationService.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.TaxValidationService.Repo.UserDataRepo;
@Service
public class UserDetailService implements UserDetailsService {

	@Autowired 
	UserDataRepo  userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new Userddeatils(userRepo.findByUsername(username));
	}

}
