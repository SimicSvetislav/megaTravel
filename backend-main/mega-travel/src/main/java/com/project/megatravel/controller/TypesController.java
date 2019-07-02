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

import com.project.megatravel.exceptions.ValueConflictException;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.services.TypesService;

@RestController
@CrossOrigin
public class TypesController {
	
	@Autowired
	private TypesService service;
	
	@RequestMapping(method = RequestMethod.GET, path="/types")
	public ResponseEntity<List<TipSmestaja>> getExtras() {
		
		return new ResponseEntity<List<TipSmestaja>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/types/{id}")
	public ResponseEntity<TipSmestaja> deleteAgent(@PathVariable("id") Long id) {
		
		try {
			service.removeById(id);
		} catch (ValueConflictException e) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<TipSmestaja>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/types", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TipSmestaja> addExtra(@RequestBody TipSmestaja t) {
		
		TipSmestaja tip = service.save(t);
		
		return new ResponseEntity<TipSmestaja>(tip, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/types")
	@ResponseBody
	public ResponseEntity<TipSmestaja> updateExtra(@RequestBody TipSmestaja t) {
		
		try {
			service.update(t);
		} catch (ValueConflictException e) {
			return new ResponseEntity<TipSmestaja>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<TipSmestaja>(HttpStatus.OK);
	}

}
