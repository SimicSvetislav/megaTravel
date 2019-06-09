package com.project.megatravel.restclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.megatravel.model.search.PretragaObjekat;

@RestController
@CrossOrigin
@RequestMapping("")
public class SearchServiceRestClient {
	
	@Autowired
	private RestTemplate restClient;
	
	
	private static final String SEARCH = "http://search/";
	
	@RequestMapping(method = RequestMethod.POST, path="/search")
	public ResponseEntity<List> search(@RequestBody PretragaObjekat po) {
		String url = SEARCH;
		
		ResponseEntity<List> response = restClient.postForEntity(url, po, List.class);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/search/objects")
	public ResponseEntity<List> searchObjects(@RequestBody PretragaObjekat po) {
		String url = SEARCH + "objects";
		
		ResponseEntity<List> response = restClient.postForEntity(url, po, List.class);
		
		return response;
	}
	

}
