package com.project.megatravel.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
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
import com.project.megatravel.model.users.AgentKredencijali;

public class AccomodationClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AccomodationClient.class);
	
//	private static final String WEBSERVICE_URL = "http://localhost:8836/agent/ws/accomodation";
	private static final String WEBSERVICE_URL = "http://localhost:8111/ws/accomodation";

	
	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/accomodation/managment";


	public SendObjectResponse sendObject(SmestajniObjekat objekat, AgentKredencijali kredencijali) {

		SendObjectRequest request = new SendObjectRequest();
		request.setSmestajniObjekat(objekat);
		request.setAgentKredencijali(kredencijali);

		log.info("Requesting location for sendObject");

		SendObjectResponse response = (SendObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for sendObject end successfull");

        
		return response;
	}
	
	public GetObjectsResponse getObjects(AgentKredencijali agentKredencijali) {

		GetObjectsRequest request = new GetObjectsRequest();
		request.setAgentKredencijali(agentKredencijali);

		log.info("Requesting location for getObjects");

		GetObjectsResponse response = (GetObjectsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getObjects end successfull");

        
		return response;
	}
	
	public GetObjectResponse getObject(long objectId) {

		GetObjectRequest request = new GetObjectRequest();
		request.setObjectId(objectId);

		log.info("Requesting location for getObject");

		GetObjectResponse response = (GetObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getObject end successfull");

        
		return response;
	}
	
	public DeleteObjectResponse deleteObject(long objectId) {

		DeleteObjectRequest request = new DeleteObjectRequest();
		request.setObjectId(objectId);

		log.info("Requesting location for deleteObject");

		DeleteObjectResponse response = (DeleteObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for deleteObject end successfull");

        
		return response;
	}
	
	
	
	public SendUnitResponse sendUnit(SmestajnaJedinica jedinica) {

		SendUnitRequest request = new SendUnitRequest();
		request.setSmestajnaJedinica(jedinica);

		log.info("Requesting location for sendUnit");

		SendUnitResponse response = (SendUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for sendUnit end successfull");

        
		return response;
	}
	
	public GetUnitsResponse getUnits(long objectId) {

		 GetUnitsRequest request = new  GetUnitsRequest();
		request.setObjectId(objectId);

		log.info("Requesting location for getUnits");

		 GetUnitsResponse response = ( GetUnitsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getUnits end successfull");

       
		return response;
	}
	
	public GetUnitResponse getUnit(long unitId) {

		GetUnitRequest request = new GetUnitRequest();
		request.setUnitId(unitId);
		

		log.info("Requesting location for getUnit");

		GetUnitResponse response = (GetUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getUnit end successfull");

        
		return response;
	}
	
	public DeleteUnitResponse deleteUnit(long unitId) {

		DeleteUnitRequest request = new DeleteUnitRequest();
		request.setUnitId(unitId);
		
		log.info("Requesting location for deleteUnit");

		DeleteUnitResponse response = (DeleteUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for deleteUnit end successfull");

        
		return response;
	}
	
	public GetAllExtrasResponse getExtras() {

		 GetAllExtrasRequest request = new GetAllExtrasRequest();
		 request.setResourceName("extras");

		log.info("Requesting location for getExtras");

		 GetAllExtrasResponse response = ( GetAllExtrasResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getExtras end successfull");

      
		return response;
	}
	
	public GetAllTypesResponse getTypes() {

		GetAllTypesRequest request = new  GetAllTypesRequest();
		request.setResourceName("types");

		log.info("Requesting location for getTypes");

		GetAllTypesResponse response = ( GetAllTypesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getTypes end successfull");

     
		return response;
	}
	
	public GetAllCategoriesResponse getCategories() {

		 GetAllCategoriesRequest request = new   GetAllCategoriesRequest();
		 request.setResourceName("categories");


		log.info("Requesting location for getCategories");

		 GetAllCategoriesResponse response = (GetAllCategoriesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getCategories end successfull");

     
		return response;
	}
	
	
}
