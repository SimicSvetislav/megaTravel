package com.project.megatravel.reservations.controllers;

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

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.reservations.services.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("")
public class ReservationsController {

	@Autowired
	private ReservationService service;
	
	@RequestMapping(method = RequestMethod.POST, path="/")
	public ResponseEntity<RezervacijaKorisnika> makeReservation(@RequestBody RezervacijaKorisnika rezervacija) {
		
		RezervacijaKorisnika rez = service.makeReservation(rezervacija);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/")
	public ResponseEntity<RezervacijaKorisnika> updateReservation(@RequestBody RezervacijaKorisnika rezervacija) {
		
		RezervacijaKorisnika rez = service.updateReservation(rezervacija);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	public ResponseEntity<RezervacijaKorisnika> removeReservation(@PathVariable("id") Long id) {
		
		RezervacijaKorisnika rez = service.deleteRez(id);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public ResponseEntity<RezervacijaKorisnika> getReservation(@PathVariable("id") String id) {
		RezervacijaKorisnika rez = service.getById(id);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservations() {
		
		List<RezervacijaKorisnika> rez = service.getAll();
		
		return new ResponseEntity<List<RezervacijaKorisnika>>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/{id}")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservationsByUser(@PathVariable Long id) {
		
		List<RezervacijaKorisnika> rez = service.getAllByUser(id);
		
		return new ResponseEntity<List<RezervacijaKorisnika>>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/object/{id}")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservationsByObject(@PathVariable Long id) {
		List<RezervacijaKorisnika> rez = service.getAllByObject(id);
		
		return new ResponseEntity<List<RezervacijaKorisnika>>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/unit/{id}")
	public ResponseEntity<List<RezervacijaKorisnika>> getReservationsByUnit(@PathVariable Long id) {
		List<RezervacijaKorisnika> rez = service.getAllByUnit(id);
		
		return new ResponseEntity<List<RezervacijaKorisnika>>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello world 3";
	}
	
}
