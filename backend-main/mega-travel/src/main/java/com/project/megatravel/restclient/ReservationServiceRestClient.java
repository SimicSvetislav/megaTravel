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

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@RestController
@CrossOrigin
public class ReservationServiceRestClient {

	@Autowired
	private RestTemplate restClient;
	
	private static final String RESERVATIONS = "http://reservations/";
	
	@RequestMapping(method = RequestMethod.POST, path="/")
	public ResponseEntity<RezervacijaKorisnika> makeReservation(@RequestBody RezervacijaKorisnika rezervacija) {
		String url = RESERVATIONS;
		ResponseEntity<RezervacijaKorisnika> response = restClient.postForEntity(url, rezervacija, RezervacijaKorisnika.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/")
	public ResponseEntity<RezervacijaKorisnika> updateReservation(@RequestBody RezervacijaKorisnika rezervacija) {
		String url = RESERVATIONS;
		
		restClient.put(url, rezervacija);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	public ResponseEntity<RezervacijaKorisnika> removeReservation(@PathVariable("id") Long id) {
		String url = RESERVATIONS + id;
		
		restClient.delete(url);
		
		return new ResponseEntity<RezervacijaKorisnika>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public ResponseEntity<RezervacijaKorisnika> getReservation(@PathVariable("id") String id) {
		String url = RESERVATIONS + id;
		
		return restClient.getForEntity(url, RezervacijaKorisnika.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservations() {
		String url = RESERVATIONS;
		
		ResponseEntity<List<RezervacijaKorisnika>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RezervacijaKorisnika>>(){});

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/byuser/{id}")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservationsByUser(@PathVariable Long id) {
		String url = RESERVATIONS + "user/" + id;
		
		ResponseEntity<List<RezervacijaKorisnika>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RezervacijaKorisnika>>(){});

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/object/{id}")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservationsByObject(@PathVariable Long id) {
		String url = RESERVATIONS + "object/" + id;
		
		ResponseEntity<List<RezervacijaKorisnika>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RezervacijaKorisnika>>(){});

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/unit/{id}")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservationsByUnit(@PathVariable Long id) {
		String url = RESERVATIONS + "unit/" + id;
		
		ResponseEntity<List<RezervacijaKorisnika>> response = restClient.exchange(
				  url,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RezervacijaKorisnika>>(){});

		return response;
	}
	
}
