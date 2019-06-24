package com.project.megatravel.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.megatravel.model.users.AgentKredencijali;
import com.project.megatravel.model.users.managment.AgentAuthentificationRequest;
import com.project.megatravel.model.users.managment.AgentAuthentificationResponse;
import com.project.megatravel.model.users.managment.GetAgentRequest;
import com.project.megatravel.model.users.managment.GetAgentResponse;
import com.project.megatravel.model.users.managment.GetUserRequest;
import com.project.megatravel.model.users.managment.GetUserResponse;

public class AgentClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AgentClient.class);
	
//	private static final String WEBSERVICE_URL = "http://localhost:8836/agent/ws/agentBackend";
	private static final String WEBSERVICE_URL = "http://localhost:8111/ws/agentBackend";

	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/users/managment";


	public AgentAuthentificationResponse agentAuthentification(AgentKredencijali kredencijali) {
		
		AgentAuthentificationRequest request = new AgentAuthentificationRequest();
		request.setAgentKredencijali(kredencijali);
		
		log.info("Requesting location for " + "agentAuthentification");

		AgentAuthentificationResponse response = new AgentAuthentificationResponse();
				
		try {
			response = (AgentAuthentificationResponse) getWebServiceTemplate()
					.marshalSendAndReceive(WEBSERVICE_URL, request,
							new SoapActionCallback(
									NAMESPACE_URL));
		} catch (WebServiceIOException e) {
			log.info("ERROR!!! Connection with web server is not established");
			response.setAgent(null);
			return response;
		} catch (Exception sE) {
			log.info("ERROR!!! Error on web service");
			response.setAgent(null);
			return response;
		}
		
		log.info("Requesting location for " + "agentAuthentification ends successfull");
		
		return response;
	}
	
	public GetAgentResponse getAgent(String email) {
		
		GetAgentRequest request = new GetAgentRequest();
		request.setEmail(email);
		
		log.info("Requesting location for " + "getAgent");

		GetAgentResponse response = new GetAgentResponse();
				
		try {
			response = (GetAgentResponse) getWebServiceTemplate()
					.marshalSendAndReceive(WEBSERVICE_URL, request,
							new SoapActionCallback(
									NAMESPACE_URL));
		} catch (WebServiceIOException e) {
			log.info("ERROR!!! Connection with web server is not established");
			response.setAgent(null);
			return response;
		} catch (Exception sE) {
			log.info("ERROR!!! Error on web service");
			response.setAgent(null);
			return response;
		}
		
		log.info("Requesting location for " + "getAgent ends successfull");
		
		return response;
	}
	
	public GetUserResponse getUserInfo(long userId) {
		
		GetUserRequest request = new GetUserRequest();
		request.setUserId(userId);
		
		log.info("Requesting location for " + "getUserInfo");

		GetUserResponse response = new GetUserResponse();
				
		try {
			response = (GetUserResponse) getWebServiceTemplate()
					.marshalSendAndReceive(WEBSERVICE_URL, request,
							new SoapActionCallback(
									NAMESPACE_URL));
		} catch (WebServiceIOException e) {
			log.info("ERROR!!! Connection with web server is not established");
			response.setKrajnjiKorisnik(null);
			return response;
		} catch (Exception sE) {
			log.info("ERROR!!! Error on web service");
			response.setKrajnjiKorisnik(null);
			return response;
		}
		
		log.info("Requesting location for " + "getUserInfo ends successfull");
		
		return response;
	}
}
