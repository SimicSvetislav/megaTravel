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
import com.project.megatravel.services.CategoriesService;

@RestController
@CrossOrigin
public class CategoriesController {
	
	@Autowired
	private CategoriesService service;
	
	@RequestMapping(method = RequestMethod.GET, path="/cat")
	public ResponseEntity<List<KategorijaSm>> getCategory() {
		
		return new ResponseEntity<List<KategorijaSm>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/cat/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
		
		service.removeById(id);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/cat", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KategorijaSm> addCategory(@RequestBody KategorijaSm c) {
		
		KategorijaSm cat = null;
		try {
			cat = service.save(c);
		} catch (ValueConflictException e) {
			return new ResponseEntity<KategorijaSm>(cat, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<KategorijaSm>(cat, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/cat")
	@ResponseBody
	public ResponseEntity<KategorijaSm> updateCategory(@RequestBody KategorijaSm c) {
		
		try {
			service.save(c);
		} catch (ValueConflictException e) {
			return new ResponseEntity<KategorijaSm>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<KategorijaSm>(HttpStatus.OK);
	}

}
