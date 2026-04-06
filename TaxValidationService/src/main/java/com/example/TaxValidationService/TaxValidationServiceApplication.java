package com.example.TaxValidationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaxValidationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxValidationServiceApplication.class, args);
		
		System.out.println(new BCryptPasswordEncoder().encode("password"));
	}

}
