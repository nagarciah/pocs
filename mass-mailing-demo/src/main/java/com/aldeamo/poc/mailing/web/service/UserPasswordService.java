package com.aldeamo.poc.mailing.web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordService {
	public String generate(String clearText) {
		return new BCryptPasswordEncoder().encode(clearText);
	}
	
	public boolean matches(String clearText, String password){
		return new BCryptPasswordEncoder().matches(clearText, password);
	}
}
