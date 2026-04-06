package com.example.TaxValidationService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaxValidationService.Model.UsersData;

@Repository
public interface UserDataRepo extends JpaRepository<UsersData,Integer> {

	public UsersData findByUsername(String username);
	
	

}
