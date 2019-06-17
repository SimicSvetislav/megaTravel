package com.project.megatravel.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.megatravel.admin.services.AdminService;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@RestController
@CrossOrigin
@RequestMapping("")
public class AdminController {

	private static final String USERS_MS = "http://users/";
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private RestTemplate restClient;
	
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
	public ResponseEntity<List<Agent>> getAgents() {
		
		String url = USERS_MS + "agent";
		
		ResponseEntity<List<Agent>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Agent>>(){});
		
		return response;
	
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user")
	public ResponseEntity<List<KrajnjiKorisnik>> getUsers() {
		
		String url = USERS_MS;
		
		ResponseEntity<List<KrajnjiKorisnik>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<KrajnjiKorisnik>>(){});
		
		return response;
	
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/{id}/{newStatus}")
	public ResponseEntity<KrajnjiKorisnik> updateUserStatus(@PathVariable("id") Long id, @PathVariable("newStatus") String newStatus) {
		
		KrajnjiKorisnik kk = service.updateStatus(id, newStatus);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
}
