package com.project.megatravel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

	@RequestMapping(value="/proba", method = RequestMethod.GET)
	public ResponseEntity<String> test() {
		System.out.println("Pogodio sam");
		String str = "Pogodio sam";
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	@RequestMapping(value="/proba2", method = RequestMethod.GET)
	public String testLogin(@RequestBody String string) {
		
		System.out.println("Testiranje rest controllera: " + string);
		
		return "Uspesno!";
		
	}
}
