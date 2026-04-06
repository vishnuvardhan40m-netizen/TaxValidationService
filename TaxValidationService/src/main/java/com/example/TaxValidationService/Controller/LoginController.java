package com.example.TaxValidationService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxValidationService.Model.UsersData;
import com.example.TaxValidationService.Service.UsersDataService;

@RestController
public class LoginController {

	
	@Autowired
	UsersDataService userService;
	
	@PostMapping ("/Signup")
	public UsersData createUser(@RequestBody UsersData user) {
		return userService.createUser(user);
		
	}
}
