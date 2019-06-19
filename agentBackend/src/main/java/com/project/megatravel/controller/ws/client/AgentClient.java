package com.project.megatravel.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.megatravel.model.messages.agent.managment.GetBookingsRequest;
import com.project.megatravel.model.messages.agent.managment.GetBookingsResponse;

public class AgentClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AccomodationRatingClient.class);

	public GetBookingsResponse getCountry(String country) {

		GetBookingsRequest request = new GetBookingsRequest();
		

		log.info("Requesting location for " + country);

		GetBookingsResponse response = (GetBookingsResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/agentBackend", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/messages/agent/managment"));
		
		
        
		return response;
	}
}
