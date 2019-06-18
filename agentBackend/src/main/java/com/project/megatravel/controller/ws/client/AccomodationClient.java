package com.project.megatravel.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.megatravel.model.accomodation.managment.AddObjectRequest;
import com.project.megatravel.model.accomodation.managment.AddObjectResponse;
import com.project.megatravel.model.accomodation.managment.AddUnitRequest;
import com.project.megatravel.model.accomodation.managment.AddUnitResponse;
import com.project.megatravel.model.accomodation.managment.GetObjectRequest;
import com.project.megatravel.model.accomodation.managment.GetObjectResponse;
import com.project.megatravel.model.accomodation.managment.GetObjectsRequest;
import com.project.megatravel.model.accomodation.managment.GetObjectsResponse;
import com.project.megatravel.model.accomodation.managment.GetUnitRequest;
import com.project.megatravel.model.accomodation.managment.GetUnitResponse;
import com.project.megatravel.model.accomodation.managment.GetUnitsRequest;
import com.project.megatravel.model.accomodation.managment.GetUnitsResponse;

public class AccomodationClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AccomodationClient.class);

	public AddObjectResponse getCountry(String country) {

		AddObjectRequest request = new AddObjectRequest();
		

		log.info("Requesting location for " + country);

		AddObjectResponse response = (AddObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/accomodation", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/accomodation/managment"));
		
		
        
		return response;
	}
	
	public AddUnitResponse getCountrys(String country) {

		AddUnitRequest request = new AddUnitRequest();
		

		log.info("Requesting location for " + country);

		AddUnitResponse response = (AddUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/accomodation", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/accomodation/managment"));
		
		
        
		return response;
	}
	
	public GetObjectResponse getsCountry(String country) {

		GetObjectRequest request = new GetObjectRequest();
		

		log.info("Requesting location for " + country);

		GetObjectResponse response = (GetObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/accomodation", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/accomodation/managment"));
		
		
        
		return response;
	}
	
	public GetObjectsResponse getssCountry(String country) {

		GetObjectsRequest request = new GetObjectsRequest();
		

		log.info("Requesting location for " + country);

		GetObjectsResponse response = (GetObjectsResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/accomodation", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/accomodation/managment"));
		
		
        
		return response;
	}
	
	public GetUnitResponse getCountrysss(String country) {

		GetUnitRequest request = new GetUnitRequest();
		

		log.info("Requesting location for " + country);

		GetUnitResponse response = (GetUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/accomodation", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/accomodation/managment"));
		
		
        
		return response;
	}
	
	public  GetUnitsResponse getCountryr(String country) {

		 GetUnitsRequest request = new  GetUnitsRequest();
		

		log.info("Requesting location for " + country);

		 GetUnitsResponse response = ( GetUnitsResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/ws/accomodation", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/accomodation/managment"));
		
		
        
		return response;
	}
}
