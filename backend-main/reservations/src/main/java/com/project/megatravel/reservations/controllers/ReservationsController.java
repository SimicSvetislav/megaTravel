package com.project.megatravel.reservations.controllers;

import java.util.ArrayList;
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

import com.project.megatravel.model.dto.ReservationDTO;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.reservations.services.EmailService;
import com.project.megatravel.reservations.services.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("")
public class ReservationsController {

	@Autowired
	private ReservationService service;
	
	@Autowired
	private EmailService emailSender;
	
	@RequestMapping(method = RequestMethod.POST, path="/")
	public ResponseEntity<RezervacijaKorisnika> makeReservation(@RequestBody RezervacijaKorisnika rezervacija) {
		System.out.println("Rezervisao ????");
		RezervacijaKorisnika rez = service.makeReservation(rezervacija);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/")
	public ResponseEntity<RezervacijaKorisnika> updateReservation(@RequestBody RezervacijaKorisnika rezervacija) {
		
		System.out.println("not support?");
		RezervacijaKorisnika rez = service.updateReservation(rezervacija);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	public ResponseEntity<RezervacijaKorisnika> removeReservation(@PathVariable("id") Long id) {
		
		RezervacijaKorisnika rez = service.deleteRez(id);
		
		return new ResponseEntity<RezervacijaKorisnika>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public ResponseEntity<RezervacijaKorisnika> getReservation(@PathVariable("id") Long id) {
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
		List<ReservationDTO> dtoList = new ArrayList<>();
		List<RezervacijaKorisnika> rez = service.getAllByUser(id);
		System.out.println("BIO OVDE ???");
		for (RezervacijaKorisnika rezervacijaKorisnika : rez) {
			System.out.println("Ima nesto ?");
			ReservationDTO dto = new ReservationDTO(rezervacijaKorisnika);
			dtoList.add(dto);
			
		}
		
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
	
	@RequestMapping(method = RequestMethod.GET, path="/rate/{roomId}/{ocena}/{userId}")
	public ResponseEntity<?> rateReservation(@PathVariable String roomId, @PathVariable int ocena, @PathVariable String userId) {
			
		/*RezervacijaKorisnika rez = service.getById(userId);
		
		rez.getSmestajnaJedinica().getClas
		
		
		return new ResponseEntity<?>(HttpStatus.OK);*/
		return null;
	}
	
	// Nalazi agenta koji iznajmljuje smestaj u okviru rezervacije
	@RequestMapping(method = RequestMethod.GET, path="/resAgent/{id}", produces="application/json")
	@ResponseBody
	public ResponseEntity<Agent> getAgentByReservation(@PathVariable("id") Long id) {
			
		Agent agent = service.getAgentByReservation(id);
		
		return new ResponseEntity<Agent>(agent, HttpStatus.OK);
	}
		
	@RequestMapping(method = RequestMethod.GET, path="/reservation/report/{id}", produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> getReservationHtml(@PathVariable("id") Long id) {
		
		String rawHtml = service.generateHTML(id);
		
		// Ovo pozivati nakon uspesne rezervacije
		/*InputStream inputStream = service.generateHTMLForMail(id);
		
		emailSender.sendMessageWithAttachmentFromInputStream("<e-mail>", "Reservation confirmation", 
				"You have successfully made reservation!\n\nSincerely,\nMegatravel team", 
				inputStream);
		
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		return new ResponseEntity<String>(rawHtml, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/confirm/{Id}", produces="application/json")
	public ResponseEntity<RezervacijaKorisnika> confirmBooking(@PathVariable("Id") Long id) {
		RezervacijaKorisnika rezervacija = service.getById(id);
		rezervacija.setStanje("Potvrdjeno"); // potvrdjeno
		
		RezervacijaKorisnika azuriranaRezervacija = service.updateReservation(rezervacija);
		
		return new ResponseEntity<RezervacijaKorisnika>(azuriranaRezervacija, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/agent/makeBooking", produces="application/json", consumes = "application/json")
	public ResponseEntity<RezervacijaKorisnika> bookingFromAgent(@RequestBody RezervacijaKorisnika rezervacija) {
		
		RezervacijaKorisnika azuriranaRezervacija = service.makeReservation(rezervacija);
		
		return new ResponseEntity<RezervacijaKorisnika>(azuriranaRezervacija, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/userByRes/{id}", produces="application/json")
	public ResponseEntity<KrajnjiKorisnik> getUserByRes(@PathVariable("id") Long id) {
		
		KrajnjiKorisnik kk = service.getUser(id);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
	}
	
}
