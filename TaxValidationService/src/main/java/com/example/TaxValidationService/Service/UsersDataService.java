package com.example.TaxValidationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaxValidationService.Model.UsersData;
import com.example.TaxValidationService.Repo.UserRepo;

@Service
public class UsersDataService {
	
	@Autowired
	UserRepo userRepo;
	
	public UsersData createUser(UsersData user) {
		
		return userRepo.save(user);
		
		
		
	}

}
