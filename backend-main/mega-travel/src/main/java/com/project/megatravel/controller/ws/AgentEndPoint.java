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
import com.project.megatravel.model.users.managment.AgentAuthentificationRequest;
import com.project.megatravel.model.users.managment.AgentAuthentificationResponse;



@Endpoint
public class AgentEndPoint {
	
	private static final Logger log = LoggerFactory.getLogger(AgentEndPoint.class);

	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/users/managment";
	
	@Autowired
	private RestTemplate restClient;
	
	private static final String USERS = "http://users/agent/verify";
	
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
			ResponseEntity<Agent> r = restClient.postForEntity(USERS, l, Agent.class);
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
}