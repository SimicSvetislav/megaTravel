package com.project.megatravel.restclient;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.megatravel.dto.KrajnjiKorisnikDTO;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.util.Creator;

@RestController
@CrossOrigin
public class RuleBasedModuleClient {
	
	@Autowired
	private RestTemplate restClient;
	
	@RequestMapping(value = "/testclient", method=RequestMethod.GET)
	public String testClient() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	    return restClient.exchange("http://localhost:8020/item", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/client/classify", method=RequestMethod.GET)
	public String classifyClient() {
		
		KrajnjiKorisnikDTO kk = Creator.createKrajnjiKorisnikDTO(1L, "NA", "02/02/2018");
		
		HttpEntity <KrajnjiKorisnikDTO> entity = new HttpEntity<KrajnjiKorisnikDTO>(kk);
	    
	    return restClient.exchange("http://localhost:8020/client/classify", HttpMethod.PUT, entity, String.class).getBody();
	}
	
	

}
