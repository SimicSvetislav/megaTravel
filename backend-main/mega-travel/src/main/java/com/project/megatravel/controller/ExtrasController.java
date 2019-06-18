package com.project.megatravel.controller;

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

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.services.ExtrasService;

@RestController
@CrossOrigin
public class ExtrasController {
	
	@Autowired
	private ExtrasService service;
	
	@RequestMapping(method = RequestMethod.GET, path="/extras")
	public ResponseEntity<List<DodatnaUsluga>> getExtras() {
		
		return new ResponseEntity<List<DodatnaUsluga>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/extras/{id}")
	public ResponseEntity<String> deleteAgent(@PathVariable("id") Long id) {
		
		service.removeById(id);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/extras", produces = "application/json")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> addExtra(@RequestBody DodatnaUsluga extra) {
		
		DodatnaUsluga du = service.save(extra);
		
		return new ResponseEntity<DodatnaUsluga>(du, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/extras")
	@ResponseBody
	public ResponseEntity<DodatnaUsluga> updateExtra(@RequestBody DodatnaUsluga extra) {
		
		service.save(extra);
		
		return new ResponseEntity<DodatnaUsluga>(HttpStatus.OK);
	}

}
