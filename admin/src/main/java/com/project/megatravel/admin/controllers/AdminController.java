package com.project.megatravel.admin.controllers;

import java.util.Date;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.util.NewPassword;

@RestController
@CrossOrigin
@RequestMapping("")
public class AdminController {

	//private static final String USERS_MS = "http://host.docker.internal:8762/users/";
	private static final String USERS_MS = "http://zuul/users/";
	//private static final String SEARCH_MS = "http://search/";
	private static final String MAIN_MS = "http://zuul/main/";
	
	//@Autowired
	//private AdminService service;
	
	@Autowired
	private RestTemplate restClient;
	
	@RequestMapping(method = RequestMethod.POST, path="/agent", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Agent> registration(@RequestBody Agent korisnik) {
		
		String url = USERS_MS + "agent";

		korisnik.setDatumRegistracije(new Date());
			
		ResponseEntity<Agent> response = null;
		try {
			response = restClient.postForEntity(url, korisnik, Agent.class);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/agent", consumes="application/json", produces="application/json")
	public ResponseEntity<Agent> updateAgent(@RequestBody Agent korisnik) {
		
		String url = USERS_MS + "agent";
		
		restClient.put(url, korisnik);
		
		return new ResponseEntity<Agent>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/agent/{id}")
	public ResponseEntity<Agent> deleteAgent(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "agent/" + id;
		
		restClient.delete(url);
		
		Agent a = new Agent();
		a.setId(id);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
		
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
		
		try {
			restClient.delete(url);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		DodatnaUsluga du = new DodatnaUsluga();
		du.setId(id);
		
		return new ResponseEntity<DodatnaUsluga>(du, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/extras", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> addExtra(@RequestBody DodatnaUsluga extra) {
		
		String url = MAIN_MS + "extras";
		
		ResponseEntity<DodatnaUsluga> response = null;
		
		try {
			response = restClient.postForEntity(url, extra, DodatnaUsluga.class);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/extras", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> updateExtra(@RequestBody DodatnaUsluga extra) {
		
		String url = MAIN_MS + "extras";
		
		try {
			restClient.put(url, extra);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
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
		
		try {
			restClient.delete(url);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		TipSmestaja tip = new TipSmestaja();
		tip.setId(id);
		
		return new ResponseEntity<TipSmestaja>(tip, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/types", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> addType(@RequestBody TipSmestaja type) {
		
		String url = MAIN_MS + "types";
		
		ResponseEntity<TipSmestaja> response = null;
		
		try {
			response = restClient.postForEntity(url, type, TipSmestaja.class);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/types", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> updateType(@RequestBody TipSmestaja type) {
		
		String url = MAIN_MS + "types";
				
		try {
			restClient.put(url, type);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
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

		try {
			restClient.delete(url);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		KategorijaSm cat = new KategorijaSm();
		cat.setId(id);
		
		return new ResponseEntity<KategorijaSm>(cat, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/cat", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KategorijaSm> addCategory(@RequestBody KategorijaSm cat) {
		
		String url = MAIN_MS + "cat";
		
		ResponseEntity<KategorijaSm> response = null;
		
		try {
			response = restClient.postForEntity(url, cat, KategorijaSm.class);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/cat", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KategorijaSm> updateCategory(@RequestBody KategorijaSm cat) {
		
		String url = MAIN_MS + "cat";
		
		try {
			restClient.put(url, cat);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		KategorijaSm c = new KategorijaSm();
		c.setId(cat.getId());
		
		return new ResponseEntity<KategorijaSm> (c, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/admin", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Administrator> updateAdmin(@RequestBody Administrator korisnik) {
		
		String url = USERS_MS + "admin";
		
		restClient.put(url, korisnik);
		
		return new ResponseEntity<Administrator>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/admin/{id}/pass", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Administrator> updateAdminPassword(@RequestBody NewPassword pass, @PathVariable("id") Long id) {
		
		String url = USERS_MS + "admin/" + id + "/pass";
		
		restClient.put(url, pass);
		
		return new ResponseEntity<Administrator>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/admin", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Administrator> addAdmin(@RequestBody Administrator korisnik) {
		
		String url = USERS_MS + "admin";
		
		korisnik.setDatumRegistracije(new Date());
		
		ResponseEntity<Administrator> response = null;
		try {
			response = restClient.postForEntity(url, korisnik, Administrator.class);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 409) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}
		
		if (response.getStatusCodeValue()==409) {
			return new ResponseEntity<>(response.getBody(), HttpStatus.CONFLICT);
		}
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/admin/{id}")
	public ResponseEntity<Administrator> deleteAdmin(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "admin/" + id;
		
		restClient.delete(url);
		
		Administrator a = new Administrator();
		a.setId(id);
		
		return new ResponseEntity<>(a, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/admin/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Administrator> getAdmin(@PathVariable("id") Long id) {
		
		String url = USERS_MS + "admin/" + id;
		
		ResponseEntity<Administrator> response = restClient.getForEntity(url, Administrator.class);
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/admin", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Administrator>> getAdmins() {
		
		String url = USERS_MS + "admin";
		
		ResponseEntity<List<Administrator>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Administrator>>(){});
		
		return response;
		
	}
	
}
