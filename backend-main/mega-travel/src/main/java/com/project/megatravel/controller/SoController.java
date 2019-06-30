package com.project.megatravel.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.repository.SoRepository;

@RestController
@RequestMapping("/so")
@CrossOrigin(origins = "http://localhost:4300")
public class SoController {
	
	@Autowired
	private SoRepository repo;

	@RequestMapping(method = RequestMethod.GET, path="/all")
	public ResponseEntity<Collection<SmestajniObjekat>> getAllSo() {
		System.out.println("Bio ovde");
		return new ResponseEntity<Collection<SmestajniObjekat>>(repo.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public ResponseEntity<SmestajniObjekat> getReservation(@PathVariable("id") Long id) {
		
		
		SmestajniObjekat rez = repo.getOneById(id);
		
		/*DodatnaUsluga du = new DodatnaUsluga();
		du.setId(1L);
		du.setIme("Bazen");

		rez.getDodatnaUsluga().add(du);
		repo.save(rez);*/
		return new ResponseEntity<SmestajniObjekat>(rez, HttpStatus.OK);
	}
	
	
	

}
