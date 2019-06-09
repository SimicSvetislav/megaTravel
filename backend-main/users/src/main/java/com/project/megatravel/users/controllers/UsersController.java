package com.project.megatravel.users.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UsersController {

	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello world";
	}
	
}
