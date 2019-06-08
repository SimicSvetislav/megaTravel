package com.project.agentBackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.agentBackend.model.chat.Poruka;
import com.project.agentBackend.model.reservations.Rezervacija;
import com.project.agentBackend.model.reservations.RezervacijaKorisnika;;


@RestController
@RequestMapping("/booking")
public class BookingController {

	@RequestMapping(value="preview/agentId/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rezervacija>> getAllBookingsPreview(@PathVariable("agentId") String agentId) {
		
		
		return new ResponseEntity<>(new ArrayList<Rezervacija>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rezervacija> makeBooking(@RequestBody Rezervacija booking) {
		
		
		return new ResponseEntity<>(new Rezervacija(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/all/agentId/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaKorisnika>> getAllBookingsUserInfo(@PathVariable("agentId") String agentId) {
		
		
		return new ResponseEntity<>(new ArrayList<RezervacijaKorisnika>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/message/{messageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> viewMessage(@PathVariable("messageId") String messageId) {
		
		
		return new ResponseEntity<>(new Poruka(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/message/answer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> answerToMessage(@RequestBody Poruka messageAnswer) {
		
		
		return new ResponseEntity<>(new Poruka(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/confirmation/bookingId/{bookingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rezervacija>> confirmBooking(@PathVariable("bookingId") String bookingId) {
		
		
		return new ResponseEntity<>(new ArrayList<Rezervacija>(), HttpStatus.OK);
	}
	
	
}
