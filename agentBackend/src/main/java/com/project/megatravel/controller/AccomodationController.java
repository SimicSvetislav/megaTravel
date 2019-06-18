package com.project.megatravel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.controller.ws.client.AccomodationClient;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.managment.AddObjectResponse;

@RestController
@RequestMapping("/accomodation")
public class AccomodationController {

	@Autowired
	private AccomodationClient accClient;
	
	@RequestMapping(value="/object", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmestajniObjekat>> getAllAccomodationObjects() {
		AddObjectResponse r =  accClient.getCountry("");
		return new ResponseEntity<>(new ArrayList<SmestajniObjekat>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object/agentId/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmestajniObjekat>> getAllAccomodationObjects(@PathVariable("agentId") String agentId) {
		
		
		return new ResponseEntity<>(new ArrayList<SmestajniObjekat>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object/{objectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajniObjekat> getAccomodationObject(@PathVariable("agentId") String objectId) {
		
		
		return new ResponseEntity<>(new SmestajniObjekat(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajniObjekat> addNewAccomodationObject(@RequestBody SmestajniObjekat newObject) {
		
		
		return new ResponseEntity<>(new SmestajniObjekat(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/object/{objectId}/unit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmestajnaJedinica>> getAllObjectUnits(@PathVariable("agentId") String objectId) {
		
		
		return new ResponseEntity<>(new ArrayList<SmestajnaJedinica>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object/{objectId}/unit",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinica> addNewObjectUnit(@PathVariable("agentId") String objectId, @RequestBody SmestajnaJedinica newUnit) {
		
		
		return new ResponseEntity<>(new SmestajnaJedinica(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/object/{objectId}/unit/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinica> getObjectUnit(@PathVariable("objectId") String objectId, @PathVariable("unitId") String unitId) {
		
		
		return new ResponseEntity<>(new SmestajnaJedinica(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object/{objectId}/unit/{unitId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinica> updateObjectUnit(@PathVariable("objectId") String objectId, @PathVariable("unitId") String unitId, @RequestBody SmestajnaJedinica unit) {
		
		
		//return new ResponseEntity<>(new SmestajnaJedinica(), HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(new SmestajnaJedinica(), HttpStatus.OK);
	}
	
	
	
}
