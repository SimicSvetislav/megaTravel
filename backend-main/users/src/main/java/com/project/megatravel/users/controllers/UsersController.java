package com.project.megatravel.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.services.UsersService;
import java.util.List;

@RestController
@CrossOrigin
public class UsersController {

	
	@Autowired
	private UsersService service;
	
	@RequestMapping(method = RequestMethod.POST, path="/")
	public ResponseEntity<KrajnjiKorisnik> registration(@RequestBody KrajnjiKorisnik korisnik) {
		
		return new ResponseEntity<KrajnjiKorisnik>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/")
	public ResponseEntity<KrajnjiKorisnik> updateUser(@RequestBody KrajnjiKorisnik korisnik) {
		
		return new ResponseEntity<KrajnjiKorisnik>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	public ResponseEntity<KrajnjiKorisnik> registration(@PathVariable("id") Long id) {
		
		KrajnjiKorisnik kk = new KrajnjiKorisnik();
		kk.setId(id);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public  ResponseEntity<KrajnjiKorisnik> getUser(@PathVariable("id") Long id) {
		
		
		return new ResponseEntity<KrajnjiKorisnik>(service.getById(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/")
	public ResponseEntity<List<KrajnjiKorisnik>> getUsers() {
		
		return new ResponseEntity<List<KrajnjiKorisnik>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello world updated";
	}
	
}
