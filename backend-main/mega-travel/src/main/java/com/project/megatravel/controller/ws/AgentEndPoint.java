package com.project.megatravel.controller.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.managment.AgentAuthentificationRequest;
import com.project.megatravel.model.users.managment.AgentAuthentificationResponse;
import com.project.megatravel.model.users.managment.GetAgentRequest;
import com.project.megatravel.model.users.managment.GetAgentResponse;
import com.project.megatravel.model.users.managment.GetUserRequest;
import com.project.megatravel.model.users.managment.GetUserResponse;



@Endpoint
public class AgentEndPoint {
	
	private static final Logger log = LoggerFactory.getLogger(AgentEndPoint.class);

	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/users/managment";
	
	@Autowired
	private RestTemplate restClient;
	
	private static final String USERS = "http://users/";
	
										//dvotacka u namespacu
	@PayloadRoot(namespace = NAMESPACE_URL, localPart = "agentAuthentificationRequest")
	@ResponsePayload
	public AgentAuthentificationResponse agentAuthentification(@RequestPayload AgentAuthentificationRequest request) throws RestClientException {
		
		log.info("agentAuthentification webservice method invoked");
		AgentAuthentificationResponse response = new AgentAuthentificationResponse();
		
		LoginForm l = new LoginForm();
		l.setEmail(request.getAgentKredencijali().getUsername());
		l.setPassword(request.getAgentKredencijali().getPassword());
		try {
			ResponseEntity<Agent> r = restClient.postForEntity(USERS + "agent/verify", l, Agent.class);
			response.setAgent(r.getBody());
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			log.info("agentAuthentification webservice method failed");
//			response.setAgent(null);
//			return response;
			throw new RestClientException("Greska prilikom poziva users mikroservisa");
		}
		// restClient.exchange(USERS, HttpMethod.POST, );

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URL, localPart = "getAgentRequest")
	@ResponsePayload
	public GetAgentResponse getAgent(@RequestPayload GetAgentRequest request) throws RestClientException {
		
		log.info("getAgent  webservice method invoked");
		
		GetAgentResponse response = new GetAgentResponse();
		ResponseEntity<Agent> r = restClient.getForEntity(USERS + "agent/mail/" + request.getEmail(), Agent.class);
		response.setAgent(r.getBody());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URL, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUserInfo(@RequestPayload GetUserRequest request) throws RestClientException {
		
		log.info("getUserInfo  webservice method invoked");
		
		GetUserResponse response = new GetUserResponse();
		ResponseEntity<KrajnjiKorisnik> r = restClient.getForEntity(USERS + "user/" + request.getUserId(), KrajnjiKorisnik.class);
		response.setKrajnjiKorisnik(r.getBody());

		return response;
	}
}