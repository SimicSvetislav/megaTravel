package com.project.megatravel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import com.project.megatravel.dto.ReservationDTO;
import com.project.megatravel.dto.SmestajnaJedinicaDTO;
import com.project.megatravel.dto.SmestajniObjekatDTO;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.service.AccomodationService;
import com.project.megatravel.service.AgentService;
import com.project.megatravel.service.BookingService;
import com.project.megatravel.util.errors.AuthentificationException;
import com.project.megatravel.util.errors.UnitIsBookedException;


@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private AccomodationService accService;
	
	@Autowired
	private AgentService agentService;

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
	public ResponseEntity<List<ReservationDTO>> getAllBookingsPreview() {
		try {
			Collection<RezervacijaKorisnika> bookings = bookingService.getAll();
			
//			List<ReservationDTO> answer = new ArrayList<ReservationDTO>();
//			bookings.forEach(book -> {
//				SmestajnaJedinica jed = accService.getObjectUnit(book.getSmestajnaJedinica());
//				SmestajniObjekat obj = accService.getAccomodationObject(jed.getSObjekat());
//				answer.add(new ReservationDTO(book, new SmestajnaJedinicaDTO(jed, new SmestajniObjekatDTO(obj))));
//			});
			
			return new ResponseEntity<>(formatListToDTO(bookings), HttpStatus.OK);
		} catch (Exception e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaKorisnika> makeBooking(@RequestBody RezervacijaKorisnika booking) {
		try {
			Agent agent = agentService.agentAuthentification();
			//booking.setKorisnik(agent.getId());
			booking.setKorisnik(null);
			
			RezervacijaKorisnika rez = bookingService.makeReservation(booking);
			
			return new ResponseEntity<>(rez, HttpStatus.CREATED);
		} catch (AuthentificationException authException) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (UnitIsBookedException unitIsBooked) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	
	@RequestMapping(value="/all/unit/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaKorisnika>> getReservationsByObject(@PathVariable Long unitId) {
		try {
			Collection<RezervacijaKorisnika> bookings = bookingService.getAllByUnit(unitId);
			
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (Exception e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/all/object/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaKorisnika>> getReservationsByUnit(@PathVariable Long unitId) {
		try {
			Collection<RezervacijaKorisnika> bookings = bookingService.getAllByObject(unitId);
			
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (Exception e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private List<ReservationDTO> formatListToDTO(Collection<RezervacijaKorisnika> bookings){
		List<ReservationDTO> answer = new ArrayList<ReservationDTO>();
		bookings.forEach(book -> {
			SmestajnaJedinica jed = accService.getObjectUnit(book.getSmestajnaJedinica());
			SmestajniObjekat obj = accService.getAccomodationObject(jed.getSObjekat());
			answer.add(new ReservationDTO(book, new SmestajnaJedinicaDTO(jed, new SmestajniObjekatDTO(obj))));
		});
		
		return answer;
	}
	
//	@RequestMapping(value="/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<RezervacijaKorisnika>> test() throws XMLDBException {
//		bookingService.test();
//		return new ResponseEntity<>(new ArrayList<RezervacijaKorisnika>(), HttpStatus.OK);
//	}
	
	
}
