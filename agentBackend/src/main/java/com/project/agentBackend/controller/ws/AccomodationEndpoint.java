package com.project.agentBackend.controller.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.agentBackend.model.accomodation.managment.AddObjectRequest;
import com.project.agentBackend.model.accomodation.managment.AddObjectResponse;
import com.project.agentBackend.model.accomodation.managment.AddUnitRequest;
import com.project.agentBackend.model.accomodation.managment.AddUnitResponse;
import com.project.agentBackend.model.accomodation.managment.GetObjectRequest;
import com.project.agentBackend.model.accomodation.managment.GetObjectResponse;
import com.project.agentBackend.model.accomodation.managment.GetObjectsRequest;
import com.project.agentBackend.model.accomodation.managment.GetObjectsResponse;
import com.project.agentBackend.model.accomodation.managment.GetUnitResponse;
import com.project.agentBackend.model.accomodation.managment.GetUnitsRequest;
import com.project.agentBackend.model.accomodation.managment.GetUnitsResponse;

@Endpoint
public class AccomodationEndpoint {
	private static final String NAMESPACE_URI = "www.model.megatravel.project.com/accomodation/managment";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addObjectRequest")
	@ResponsePayload
	public AddObjectResponse addObject(@RequestPayload AddObjectRequest request) {
		
		return new AddObjectResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUnitRequest")
	@ResponsePayload
	public AddUnitResponse addUnit(@RequestPayload AddUnitRequest request) {
		
		return new AddUnitResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getObjectRequest")
	@ResponsePayload
	public GetObjectResponse getObject(@RequestPayload GetObjectRequest request) {
		
		return new GetObjectResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getObjectsRequest")
	@ResponsePayload
	public GetObjectsResponse getObjects(@RequestPayload GetObjectsRequest request) {
		
		return new GetObjectsResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUnitResponse")
	@ResponsePayload
	public GetUnitResponse getUnit(@RequestPayload GetUnitResponse request) {
		
		return new GetUnitResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUnitsResponse")
	@ResponsePayload
	public GetUnitsResponse getUnits(@RequestPayload GetUnitsRequest request) {
		
		return new GetUnitsResponse();
	}
	
	
}