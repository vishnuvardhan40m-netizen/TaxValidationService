package com.example.TaxValidationService.Security;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.TaxValidationService.Model.UsersData;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtService {
	private SecretKey secretKey;
	
	
	public JwtService() {
		try {
			KeyGenerator key= KeyGenerator.getInstance("HmacSHA256");
			
			this.secretKey=key.generateKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public String generateToken(UsersData user) {

Map<String, Object> claims = new HashMap<>();
claims.put("role", "ROLE"+user.getRole());

return Jwts.builder()
    .setClaims(claims)
    .setSubject(user.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
    //.signWith(secretKey,SignatureAlgorithm.HS256)
    .signWith(secretKey)
    .compact();


	 				
	
	
	
}

public String getUsername(String token) {
	Claims claim=(Claims) Jwts.parser().setSigningKey(secretKey).parse(token).getBody();
	
	return claim.getSubject();
}

public boolean validateToken(String token) {
	Claims claim=(Claims) Jwts.parser().setSigningKey(secretKey).parse(token).getBody();
	return !claim.getExpiration().before(new Date());
}
	

}
