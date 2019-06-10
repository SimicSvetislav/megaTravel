package com.project.megatravel.restclient;

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

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@RestController
@CrossOrigin
public class UsersServiceRestClient {

	@Autowired
	private RestTemplate restClient;
	
	private static final String USERS = "http://users/";
	//private static final String AGENTS = "http://users/agent/";
	private static final String ADMIN_MODULE = "http://localhost:8131/agent/";
	
	@RequestMapping(method = RequestMethod.POST, path="/user")
	public ResponseEntity<KrajnjiKorisnik> registration(@RequestBody KrajnjiKorisnik korisnik) {
		String url = USERS;
		ResponseEntity<KrajnjiKorisnik> response = restClient.postForEntity(url, korisnik, KrajnjiKorisnik.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/user")
	public ResponseEntity<KrajnjiKorisnik> updateUser(@RequestBody KrajnjiKorisnik korisnik) {
		String url = USERS;
		
		restClient.put(url, korisnik);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/user/{id}")
	public ResponseEntity<KrajnjiKorisnik> removeUser(@PathVariable("id") Long id) {
		String url = USERS + id;
		
		restClient.delete(url);
		
		return new ResponseEntity<KrajnjiKorisnik>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/{id}")
	public ResponseEntity<KrajnjiKorisnik> getUser(@PathVariable("id") String id) {
		String url = USERS + id;
		
		return restClient.getForEntity(url, KrajnjiKorisnik.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user")
	public ResponseEntity<List<KrajnjiKorisnik>> getUsers() {
		String url = USERS;
		
		ResponseEntity<List<KrajnjiKorisnik>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<KrajnjiKorisnik>>(){});

		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/agent")
	public ResponseEntity<Agent> registrationAgent(@RequestBody Agent korisnik) {
		String url = ADMIN_MODULE;
		ResponseEntity<Agent> response = restClient.postForEntity(url, korisnik, Agent.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/agent")
	public ResponseEntity<Agent> updateUser(@RequestBody Agent korisnik) {
		String url = ADMIN_MODULE;
		
		restClient.put(url, korisnik);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/agent/{id}")
	public ResponseEntity<Agent> removeAgent(@PathVariable("id") Long id) {
		String url = ADMIN_MODULE + id;
		
		restClient.delete(url);
		
		return new ResponseEntity<Agent>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent/{id}")
	public ResponseEntity<Agent> getAgent(@PathVariable("id") String id) {
		String url = ADMIN_MODULE + id;
		
		return restClient.getForEntity(url, Agent.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent")
	public ResponseEntity<List<Agent>> getAgents() {
		String url = ADMIN_MODULE;
		
		ResponseEntity<List<Agent>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Agent>>(){});

		return response;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		String url = "http://users/hello";
		return restClient.getForObject(url, String.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello2")
	public String hello2() {
		String url = "http://search/hello";
		return restClient.getForObject(url, String.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello3")
	public String hello3() {
		String url = "http://reservations/hello";
		return restClient.getForObject(url, String.class);
	}
	
}
