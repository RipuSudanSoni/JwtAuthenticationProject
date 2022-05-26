package com.jwt.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		String text = "this is private Page";
		text += "this page is not allow to unaauthorised User";
		return text;
	}
	
	@RequestMapping(value = "/rest/getUser", method = RequestMethod.GET)
	public String getUser() {
		String userText = "{\"user\" : \"Ripu\"}";
		return userText;
	}

}
