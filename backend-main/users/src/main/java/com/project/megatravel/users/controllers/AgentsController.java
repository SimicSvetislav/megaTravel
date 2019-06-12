package com.project.megatravel.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.users.services.AgentsService;

@RestController
@CrossOrigin
public class AgentsController {
	
	@Autowired
	private AgentsService service;
	
	@RequestMapping(method = RequestMethod.POST, path="/agent")
	public ResponseEntity<Agent> registration(@RequestBody Agent korisnik) {
		
		return new ResponseEntity<Agent>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/agent")
	public ResponseEntity<Agent> updateUser(@RequestBody Agent korisnik) {
		
		return new ResponseEntity<Agent>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/agent/{id}")
	public ResponseEntity<Agent> registration(@PathVariable("id") Long id) {
		
		Agent a = new Agent();
		a.setId(id);
		
		return new ResponseEntity<Agent>(a, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent/{id}")
	public ResponseEntity<Agent> getUser(@PathVariable("id") Long id) {
		
		
		return new ResponseEntity<Agent>(service.getById(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent")
	public ResponseEntity<List<Agent>> getUsers() {
		
		return new ResponseEntity<List<Agent>>(service.getAll(), HttpStatus.OK);
	
	}

}