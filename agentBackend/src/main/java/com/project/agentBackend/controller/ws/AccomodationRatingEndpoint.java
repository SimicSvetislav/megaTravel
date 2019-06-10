package com.project.agentBackend.controller.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.agentBackend.model.messages.rating.Ocene;
import com.project.agentBackend.model.messages.rating.ws.ws.GetRatingRequest;
import com.project.agentBackend.model.messages.rating.ws.ws.GetRatingResponse;

@Endpoint
public class AccomodationRatingEndpoint {

private static final String NAMESPACE_URI = "www.model.megatravel.project.com/messages/rating/ws";

	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRatingRequest")
	@ResponsePayload
	public GetRatingResponse getRating(@RequestPayload GetRatingRequest rating) {
		
		return new GetRatingResponse();
	}
	
	
	
	
}
