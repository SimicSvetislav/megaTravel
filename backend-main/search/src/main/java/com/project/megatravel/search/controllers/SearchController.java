package com.project.megatravel.search.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("")
public class SearchController {

	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello world 2";
	}
	
}
