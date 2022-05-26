package com.jwt.project.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		logger.info("UserName :- {}",userName);
		if(userName.equals("Ripu")) {
			return new User("Ripu", "Ripu123", new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found !!");
		}
	}

}
