package com.project.megatravel.controller.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.megatravel.model.accomodation.managment.DeleteObjectRequest;
import com.project.megatravel.model.accomodation.managment.DeleteObjectResponse;
import com.project.megatravel.model.accomodation.managment.DeleteUnitRequest;
import com.project.megatravel.model.accomodation.managment.DeleteUnitResponse;
import com.project.megatravel.model.accomodation.managment.GetAllCategoriesRequest;
import com.project.megatravel.model.accomodation.managment.GetAllCategoriesResponse;
import com.project.megatravel.model.accomodation.managment.GetAllExtrasRequest;
import com.project.megatravel.model.accomodation.managment.GetAllExtrasResponse;
import com.project.megatravel.model.accomodation.managment.GetAllTypesRequest;
import com.project.megatravel.model.accomodation.managment.GetAllTypesResponse;
import com.project.megatravel.model.accomodation.managment.GetObjectRequest;
import com.project.megatravel.model.accomodation.managment.GetObjectResponse;
import com.project.megatravel.model.accomodation.managment.GetObjectsRequest;
import com.project.megatravel.model.accomodation.managment.GetObjectsResponse;
import com.project.megatravel.model.accomodation.managment.GetUnitRequest;
import com.project.megatravel.model.accomodation.managment.GetUnitResponse;
import com.project.megatravel.model.accomodation.managment.GetUnitsRequest;
import com.project.megatravel.model.accomodation.managment.GetUnitsResponse;
import com.project.megatravel.model.accomodation.managment.SendObjectRequest;
import com.project.megatravel.model.accomodation.managment.SendObjectResponse;
import com.project.megatravel.model.accomodation.managment.SendUnitRequest;
import com.project.megatravel.model.accomodation.managment.SendUnitResponse;

@Endpoint
public class AccomodationEndpoint {
	
	private static final Logger log = LoggerFactory.getLogger(AccomodationEndpoint.class);

	private static final String NAMESPACE_URI = "www.model.megatravel.project.com/accomodation/managment";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendObjectRequest")
	@ResponsePayload
	public SendObjectResponse sendObject(@RequestPayload  SendObjectRequest request) {
		
		log.info("sendObject webservice method invoked");
		
		return new  SendObjectResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getObjectsRequest")
	@ResponsePayload
	public GetObjectsResponse getObjects(@RequestPayload GetObjectsRequest request) {
		
		log.info("getObjects webservice method invoked");

		return new GetObjectsResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getObjectRequest")
	@ResponsePayload
	public GetObjectResponse getObject(@RequestPayload GetObjectRequest request) {
		
		log.info("getObject webservice method invoked");

		return new GetObjectResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteObjectRequest")
	@ResponsePayload
	public DeleteObjectResponse deleteObject(@RequestPayload DeleteObjectRequest request) {

		log.info("deleteObject webservice method invoked");

		return new  DeleteObjectResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendUnitRequest")
	@ResponsePayload
	public  SendUnitResponse sendUnit(@RequestPayload  SendUnitRequest request) {

		log.info("sendUnit webservice method invoked");

		return new  SendUnitResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUnitsRequest")
	@ResponsePayload
	public GetUnitsResponse getUnits(@RequestPayload GetUnitsRequest request) {

		log.info("getUnits webservice method invoked");

		return new GetUnitsResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUnitRequest")
	@ResponsePayload
	public GetUnitResponse getUnit(@RequestPayload GetUnitRequest request) {

		log.info("getUnit webservice method invoked");
		
		return new GetUnitResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUnitRequest")
	@ResponsePayload
	public DeleteUnitResponse deletetUnit(@RequestPayload DeleteUnitRequest request) {
		
		log.info("deletetUnit webservice method invoked");

		
		return new DeleteUnitResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllExtrasRequest")
	@ResponsePayload
	public GetAllExtrasResponse getExtras(@RequestPayload GetAllExtrasRequest request) {

		log.info("getExtras webservice method invoked");

		return new GetAllExtrasResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTypesRequest")
	@ResponsePayload
	public GetAllTypesResponse getTypes(@RequestPayload GetAllTypesRequest request) {

		log.info("getTypes webservice method invoked");
		
		return new GetAllTypesResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCategoriesRequest")
	@ResponsePayload
	public GetAllCategoriesResponse getCategories(@RequestPayload GetAllCategoriesRequest request) {
	
		log.info("getCategories webservice method invoked");

		return new GetAllCategoriesResponse();
	}
	
	
}