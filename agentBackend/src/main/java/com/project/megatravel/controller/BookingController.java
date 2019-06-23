package com.project.megatravel.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.service.BookingService;


@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

//	@RequestMapping(value="preview/agentId/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<RezervacijaKorisnika>> getAllBookingsPreview(@PathVariable("agentId") String agentId) {
//		
//		
//		return new ResponseEntity<>(new ArrayList<RezervacijaKorisnika>(), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="/all/agentId/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<RezervacijaKorisnika>> getAllBookingsUserInfo(@PathVariable("agentId") String agentId) {
//		
//		
//		return new ResponseEntity<>(new ArrayList<RezervacijaKorisnika>(), HttpStatus.OK);
//	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaKorisnika>> getAllBookingsPreview() {
		try {
			Collection<RezervacijaKorisnika> bookings = bookingService.getAll();
			
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaKorisnika> makeBooking(@RequestBody RezervacijaKorisnika booking) {
		try {
			RezervacijaKorisnika rez = bookingService.makeReservation(booking);
			
			return new ResponseEntity<>(rez, HttpStatus.CREATED);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/{bookingId}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaKorisnika> getBooking(@PathVariable("bookingId") String bookingId) {
		try {
			Long id = Long.parseLong(bookingId);
			RezervacijaKorisnika rez = bookingService.getById(id);
			
			return new ResponseEntity<>(rez, HttpStatus.OK);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
	
	@RequestMapping(value="/confirmation/bookingId/{bookingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaKorisnika> confirmBooking(@PathVariable("bookingId") String bookingId) {
		try {
			Long id = Long.parseLong(bookingId);
			RezervacijaKorisnika rez = bookingService.confirmBooking(id);
			
			return new ResponseEntity<>(rez, HttpStatus.OK);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/all/unit/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaKorisnika>> getReservationsByObject(@PathVariable Long unitId) {
		try {
			Collection<RezervacijaKorisnika> bookings = bookingService.getAllByUnit(unitId);
			
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/all/object/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaKorisnika>> getReservationsByUnit(@PathVariable Long unitId) {
		try {
			Collection<RezervacijaKorisnika> bookings = bookingService.getAllByObject(unitId);
			
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaKorisnika>> test() throws XMLDBException {
		bookingService.test();
		return new ResponseEntity<>(new ArrayList<RezervacijaKorisnika>(), HttpStatus.OK);
	}
	
	
}
