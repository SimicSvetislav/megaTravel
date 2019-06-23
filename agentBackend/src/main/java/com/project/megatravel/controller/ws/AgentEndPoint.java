package com.project.megatravel.controller.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.megatravel.model.users.managment.AgentAuthentificationRequest;
import com.project.megatravel.model.users.managment.AgentAuthentificationResponse;



@Endpoint
public class AgentEndPoint {
	
	private static final Logger log = LoggerFactory.getLogger(AgentEndPoint.class);

	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/users/managment";
										//dvotacka u namespacu
	@PayloadRoot(namespace = NAMESPACE_URL, localPart = "agentAuthentificationRequest")
	@ResponsePayload
	public AgentAuthentificationResponse agentAuthentification(@RequestPayload AgentAuthentificationRequest request) {
		
		log.info("agentAuthentification webservice method invoked");


		return new AgentAuthentificationResponse();
	}
}