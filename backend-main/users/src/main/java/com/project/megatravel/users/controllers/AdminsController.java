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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.users.repository.AdminRepository;
import com.project.megatravel.users.services.AdminService;
import com.project.megatravel.users.services.AgentsService;

@RestController
@CrossOrigin
public class AdminsController {

	@Autowired
	private AdminService service;
	
	@RequestMapping(method = RequestMethod.POST, path="/admin", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Administrator> add(@RequestBody Administrator korisnik) {
		
		Administrator admin = service.save(korisnik);
		
		return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/admin")
	public ResponseEntity<Administrator> updateUser(@RequestBody Administrator korisnik) {
		
		Administrator admin = service.save(korisnik);
		
		return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/admin/{id}")
	public ResponseEntity<Administrator> registration(@PathVariable("id") Long id) {
		
		Administrator a = service.deleteById(id);

		return new ResponseEntity<Administrator>(a, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/admin/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Administrator> getUser(@PathVariable("id") Long id) {
		
		Administrator a = service.getOneById(id);
		
		if(a == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Administrator>(a, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/admin", produces = "application/json")
	public ResponseEntity<List<Administrator>> getUsers() {
		
		List<Administrator> admins = service.getAll();
		
		return new ResponseEntity<List<Administrator>>(admins, HttpStatus.OK);
	
	}
}
