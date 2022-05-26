package com.jwt.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.project.helper.JwtUtil;
import com.jwt.project.model.JwtRequest;
import com.jwt.project.model.JwtResponse;
import com.jwt.project.services.CustomUserDetailsService;

@RestController
public class JwtController {
	
	Logger logger = LoggerFactory.getLogger(JwtController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService costomUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		System.out.println(jwtRequest);
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( jwtRequest.getUserName(), jwtRequest.getPassword()));
		
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		// fine area
		UserDetails userDetails = this.costomUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
	 	
		String token = this.jwtUtil.generateToken(userDetails);
		logger.info("JWT Token is :- {}",token);
		
		return new ResponseEntity(new JwtResponse(token), HttpStatus.OK);
	}

}
