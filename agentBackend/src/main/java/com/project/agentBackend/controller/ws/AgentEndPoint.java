package com.project.agentBackend.controller.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.agentBackend.model.messages.agent.GetBookingsRequest;
import com.project.agentBackend.model.messages.agent.GetBookingsResponse;



@Endpoint
public class AgentEndPoint {
	private static final String NAMESPACE_URI = "www.model.megatravel.project.com/messages/agent";

	
	/*										//dvotacka u namespacu
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public Temp getCountry(@RequestPayload Temp request) {
		

		return new Temp();
	}*/
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingsRequest")
	@ResponsePayload
	public GetBookingsResponse getAnswer(@RequestPayload GetBookingsRequest request) {
		

		return new GetBookingsResponse();
	}
	
	
}