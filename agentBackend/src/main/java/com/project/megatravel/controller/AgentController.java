package com.project.megatravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.service.AgentService;
import com.project.megatravel.util.errors.AuthentificationException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class AgentController {
	
	@Autowired
	private AgentService agentService ;

//	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
//	public ResponseEntity<String> login(@RequestBody Agent agent) {
//
//		return new ResponseEntity<>("ulogovan", HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/registration",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Agent> register(@RequestBody Agent newAgent){
//		
//		
//		return new ResponseEntity<>(new Agent(), HttpStatus.CREATED);
//	}
	
	@RequestMapping(value = "/synchronize",  method = RequestMethod.GET)
	public ResponseEntity<?> syncData(){
		try {
			agentService.syncData();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AuthentificationException aE) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	
	@RequestMapping(value = "/genPass/{pass}",  method = RequestMethod.GET)
	public ResponseEntity<String> genPass(@PathVariable("pass") String pass){
		
		return new ResponseEntity<>(agentService.genPassword(pass), HttpStatus.CREATED);
	}
}
