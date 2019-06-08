package com.project.megatravel.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class UsersServiceRestClient {

	@Autowired
	private RestTemplate restClient;
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String hello() {
		String url = "http://users/hello";
		return restClient.getForObject(url, String.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello2")
	public String hello2() {
		String url = "http://search/hello";
		return restClient.getForObject(url, String.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello3")
	public String hello3() {
		String url = "http://reservations/hello";
		return restClient.getForObject(url, String.class);
	}
	
}
