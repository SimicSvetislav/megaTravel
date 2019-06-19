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

import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.services.UsersService;

@RestController
@CrossOrigin
public class UsersController {

	
	@Autowired
	private UsersService service;
	
/*	@RequestMapping(method = RequestMethod.GET, path="/test")
	public ResponseEntity<String> test() {
		System.out.println("DSLFKJSDF SVE OKKKKK");
		return new ResponseEntity<String>("Sve ok", HttpStatus.OK);
		
	}*/
	
	@RequestMapping(method = RequestMethod.POST, path="/user")
	public ResponseEntity<KrajnjiKorisnik> registration(@RequestBody KrajnjiKorisnik korisnik) {
		
		return new ResponseEntity<KrajnjiKorisnik>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/user")
	public ResponseEntity<KrajnjiKorisnik> updateUser(@RequestBody KrajnjiKorisnik korisnik) {
		
		return new ResponseEntity<KrajnjiKorisnik>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/user/{id}")
	public ResponseEntity<KrajnjiKorisnik> registration(@PathVariable("id") Long id) {
		
		service.remove(id);
		
		KrajnjiKorisnik kk = new KrajnjiKorisnik();
		kk.setId(id);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/{id}")
	public  ResponseEntity<KrajnjiKorisnik> getUser(@PathVariable("id") Long id) {
		
		return new ResponseEntity<KrajnjiKorisnik>(service.getById(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/block/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KrajnjiKorisnik> blockUser(@PathVariable("id") Long id) {
		
		KrajnjiKorisnik kk = service.getById(id);
		kk.setStanje("BLOKIRAN");
		
		kk = service.save(kk);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/activate/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KrajnjiKorisnik> activateUser(@PathVariable("id") Long id) {
		
		KrajnjiKorisnik kk = service.getById(id);
		kk.setStanje("AKTIVAN");
		
		kk = service.save(kk);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user")
	public ResponseEntity<List<KrajnjiKorisnik>> getUsers() {
		
		return new ResponseEntity<List<KrajnjiKorisnik>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello world updated";
	}
	
	
	
}
