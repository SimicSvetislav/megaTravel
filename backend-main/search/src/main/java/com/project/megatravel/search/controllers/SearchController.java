package com.project.megatravel.search.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.search.PretragaObjekat;
import com.project.megatravel.search.services.SearchService;

@RestController
@CrossOrigin
@RequestMapping("")
public class SearchController {

	@Autowired
	private SearchService service;
	
	@RequestMapping(method = RequestMethod.POST, path="/objects")
	public List<SmestajniObjekat> searchObjects(@RequestBody PretragaObjekat po) {
		
		return service.searchObject(po);
				
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/")
	public List<SmestajniObjekat> search(@RequestBody PretragaObjekat po) {
		
		return service.search(po);
				
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		return "Hello world 2";
	}
	
}
