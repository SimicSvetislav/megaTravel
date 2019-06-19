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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@RestController
@CrossOrigin
@RequestMapping("")
public class AdminController {

	private static final String USERS_MS = "http://users/";
	//private static final String SEARCH_MS = "http://search/";
	private static final String MAIN_MS = "http://main/";
	
	//@Autowired
	//private AdminService service;
	
	@Autowired
	private RestTemplate restClient;
	
	@RequestMapping(method = RequestMethod.POST, path="/agent")
	public ResponseEntity<Agent> registration(@RequestBody Agent korisnik) {
		
		return new ResponseEntity<Agent>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/agent")
	public ResponseEntity<Agent> updateAgent(@RequestBody Agent korisnik) {
		
		return new ResponseEntity<Agent>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/agent/{id}")
	public ResponseEntity<Agent> deleteAgent(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "agent/" + id;
		
		restClient.delete(url);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Agent> getUser(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "agent/" + id;
		
		ResponseEntity<Agent> response = restClient.getForEntity(url, Agent.class);
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/user/{id}")
	public ResponseEntity<KrajnjiKorisnik> deleteUser(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "user/" + id;
		
		restClient.delete(url);
		
		KrajnjiKorisnik kk = new KrajnjiKorisnik();
		kk.setId(id);
		
		return new ResponseEntity<>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/block/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KrajnjiKorisnik> blockUser(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "user/block/" + id;
		
		ResponseEntity<KrajnjiKorisnik> response = restClient.getForEntity(url, KrajnjiKorisnik.class);
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/activate/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KrajnjiKorisnik> activateUser(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "user/activate/" + id;
		
		ResponseEntity<KrajnjiKorisnik> response = restClient.getForEntity(url, KrajnjiKorisnik.class);
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent", produces = "application/json")
	@ResponseBody
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
		
		String url = USERS_MS + "user";
		
		ResponseEntity<List<KrajnjiKorisnik>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<KrajnjiKorisnik>>(){});
		
		return response;
	
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/extras", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<DodatnaUsluga>> getExtras() {
		
		String url = MAIN_MS + "extras";
		
		ResponseEntity<List<DodatnaUsluga>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<DodatnaUsluga>>(){});
		
		return response;
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/extras/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> deleteExtra(@PathVariable("id") Long id) {
		
		String url = MAIN_MS + "extras/" + id;
		
		restClient.delete(url);
		
		DodatnaUsluga du = new DodatnaUsluga();
		du.setId(id);
		
		return new ResponseEntity<DodatnaUsluga>(du, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/extras", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> addExtra(@RequestBody DodatnaUsluga extra) {
		
		String url = MAIN_MS + "extras";
		
		ResponseEntity<DodatnaUsluga> response = restClient.postForEntity(url, extra, DodatnaUsluga.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/extras", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> updateExtra(@RequestBody DodatnaUsluga extra) {
		
		String url = MAIN_MS + "extras";
		
		restClient.put(url, extra);
		
		DodatnaUsluga du = new DodatnaUsluga();
		du.setId(extra.getId());
		
		return new ResponseEntity<DodatnaUsluga> (du, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/types", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<TipSmestaja>> getTypes() {
		
		String url = MAIN_MS + "types";
		
		ResponseEntity<List<TipSmestaja>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<TipSmestaja>>(){});
		
		return response;
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/types/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> deleteType(@PathVariable("id") Long id) {
		
		String url = MAIN_MS + "types/" + id;
		
		restClient.delete(url);
		
		TipSmestaja tip = new TipSmestaja();
		tip.setId(id);
		
		return new ResponseEntity<TipSmestaja>(tip, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/types", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> addType(@RequestBody TipSmestaja type) {
		
		String url = MAIN_MS + "types";
		
		ResponseEntity<TipSmestaja> response = restClient.postForEntity(url, type, TipSmestaja.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/types", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> updateType(@RequestBody TipSmestaja type) {
		
		String url = MAIN_MS + "types";
		
		restClient.put(url, type);
		
		TipSmestaja t = new TipSmestaja();
		t.setId(type.getId());
		
		return new ResponseEntity<TipSmestaja> (t, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/cat", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<KategorijaSm>> getCategories() {
		
		String url = MAIN_MS + "cat";
		
		ResponseEntity<List<KategorijaSm>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<KategorijaSm>>(){});
		
		return response;
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/cat/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KategorijaSm> deleteCategory(@PathVariable("id") Long id) {
		
		String url = MAIN_MS + "cat/" + id;
		
		restClient.delete(url);
		
		KategorijaSm cat = new KategorijaSm();
		cat.setId(id);
		
		return new ResponseEntity<KategorijaSm>(cat, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/cat", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> addCategory(@RequestBody KategorijaSm cat) {
		
		String url = MAIN_MS + "cat";
		
		ResponseEntity<TipSmestaja> response = restClient.postForEntity(url, cat, TipSmestaja.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/cat", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KategorijaSm> updateCategory(@RequestBody KategorijaSm cat) {
		
		String url = MAIN_MS + "cat";
		
		restClient.put(url, cat);
		
		KategorijaSm c = new KategorijaSm();
		c.setId(cat.getId());
		
		return new ResponseEntity<KategorijaSm> (c, HttpStatus.OK);
	}
	
}
