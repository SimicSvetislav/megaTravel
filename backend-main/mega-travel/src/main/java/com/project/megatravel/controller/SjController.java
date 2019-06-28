package com.project.megatravel.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;

@RestController
@RequestMapping("/sj")
@CrossOrigin(origins = "http://localhost:4300")
public class SjController {
	
	@Autowired
	private SjRepository repo;
	
	@Autowired
	private SoRepository repoSo;

	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public ResponseEntity<SmestajnaJedinica> getReservation(@PathVariable("id") Long id) {
		SmestajnaJedinica rez = repo.getOneById(id);
		
		return new ResponseEntity<SmestajnaJedinica>(rez, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/units/{id}")
	public ResponseEntity<Collection<SmestajnaJedinica>> getUnitsOfObject(@PathVariable("id") Long id) {
		Collection<SmestajnaJedinica> rez = repo.getAll();
		Collection<SmestajnaJedinica> ret = new ArrayList<>();
		for (SmestajnaJedinica smestajnaJedinica : rez) {
			if(smestajnaJedinica.getSObjekat() == id) {
				ret.add(smestajnaJedinica);
			}
		}
		
		return new ResponseEntity<Collection<SmestajnaJedinica>>(ret, HttpStatus.OK);
	}
	
	
	//POKUSAJ DA SE VRATI OBJEKAT NA OSNOVU JEDINICE AL OVO NECE PITI VODE, NEK OSTANE ZA SAD TU
	@RequestMapping(method = RequestMethod.GET, path="/object/{id}")
	public ResponseEntity<SmestajniObjekat> getObjectByUnit(@PathVariable("id") Long id) { //u path variable mi je id smestajne jedinice
		
		SmestajniObjekat retVal = new SmestajniObjekat();
		
	
		Collection<SmestajnaJedinica> rez = repo.getAll();
		for (SmestajnaJedinica s : rez) {
			if(s.getId().equals(id)) {
				System.out.println("sdfsdfsadf " + s.getId());
				retVal = repoSo.getOneById(s.getId());
				break;
			}
		}
		
	
		return new ResponseEntity<SmestajniObjekat>(retVal, HttpStatus.OK);
	
	}
	
}
