package com.project.megatravel.controller.ws;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.services.CategoriesService;
import com.project.megatravel.services.ExtrasService;
import com.project.megatravel.services.SjService;
import com.project.megatravel.services.SoService;
import com.project.megatravel.services.TypesService;

@Endpoint
public class AccomodationEndpoint {
	
	private static final Logger log = LoggerFactory.getLogger(AccomodationEndpoint.class);

	private static final String NAMESPACE_URI = "www.model.megatravel.project.com/accomodation/managment";
	
	@Autowired
	private ExtrasService extrasService;
	
	@Autowired
	private TypesService typesService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private SoService sOService;
	
	@Autowired
	private SjService sjService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendObjectRequest")
	@ResponsePayload
	public SendObjectResponse sendObject(@RequestPayload  SendObjectRequest request) {
		
		log.info("sendObject webservice method invoked");
		
		SendObjectResponse response = new SendObjectResponse();
		SmestajniObjekat objekat = sOService.save(request.getSmestajniObjekat());
		response.setSmestajniObjekat(objekat);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getObjectsRequest")
	@ResponsePayload
	public GetObjectsResponse getObjects(@RequestPayload GetObjectsRequest request) {
		Agent a = new Agent();
		
		log.info("getObjects webservice method invoked");
		
		GetObjectsResponse response = new GetObjectsResponse();
		List<SmestajniObjekat> objects = sOService.getAllAgentObject(request.getAgent().getId());
		response.getSmestajniObjekat().addAll(objects);
		
		return new GetObjectsResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getObjectRequest")
	@ResponsePayload
	public GetObjectResponse getObject(@RequestPayload GetObjectRequest request) {
		
		log.info("getObject webservice method invoked");
		
		GetObjectResponse response = new GetObjectResponse();
		SmestajniObjekat objekat = sOService.getOneById(request.getObjectId());
		response.setSmestajniObjekat(objekat);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteObjectRequest")
	@ResponsePayload
	public DeleteObjectResponse deleteObject(@RequestPayload DeleteObjectRequest request) {

		log.info("deleteObject webservice method invoked");
		
		DeleteObjectResponse response = new DeleteObjectResponse();
		SmestajniObjekat objekat = sOService.deleteById(request.getObjectId());
		response.setSmestajniObjekat(objekat);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendUnitRequest")
	@ResponsePayload
	public  SendUnitResponse sendUnit(@RequestPayload  SendUnitRequest request) {

		log.info("sendUnit webservice method invoked");
		
		SendUnitResponse response = new SendUnitResponse();
		SmestajnaJedinica jedinica = sjService.save(request.getSmestajnaJedinica());
		response.setSmestajnaJedinica(jedinica);
				
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUnitsRequest")
	@ResponsePayload
	public GetUnitsResponse getUnits(@RequestPayload GetUnitsRequest request) {

		log.info("getUnits webservice method invoked");
		
		GetUnitsResponse response = new GetUnitsResponse();
		Collection<SmestajnaJedinica> jedinice = sjService.getObjectUnits(request.getObjectId());
		response.getSmestajnaJedinica().addAll(jedinice);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUnitRequest")
	@ResponsePayload
	public GetUnitResponse getUnit(@RequestPayload GetUnitRequest request) {

		log.info("getUnit webservice method invoked");
		
		GetUnitResponse response = new GetUnitResponse();
		SmestajnaJedinica jedinica = sjService.getOneById(request.getUnitId());
		response.setSmestajnaJedinica(jedinica);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUnitRequest")
	@ResponsePayload
	public DeleteUnitResponse deletetUnit(@RequestPayload DeleteUnitRequest request) {
		
		log.info("deletetUnit webservice method invoked");
		
		DeleteUnitResponse response = new DeleteUnitResponse();
		SmestajnaJedinica jedinica = sjService.deleteById(request.getUnitId());
		response.setSmestajnaJedinica(jedinica);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllExtrasRequest")
	@ResponsePayload
	public GetAllExtrasResponse getExtras(@RequestPayload GetAllExtrasRequest request) {

		log.info("getExtras webservice method invoked");
		
		GetAllExtrasResponse response = new GetAllExtrasResponse();
		response.getDodatnaUsluga().addAll(extrasService.getAll());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTypesRequest")
	@ResponsePayload
	public GetAllTypesResponse getTypes(@RequestPayload GetAllTypesRequest request) {

		log.info("getTypes webservice method invoked");
		
		GetAllTypesResponse response = new GetAllTypesResponse();
		response.getTipSmestaja().addAll(typesService.getAll());
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCategoriesRequest")
	@ResponsePayload
	public GetAllCategoriesResponse getCategories(@RequestPayload GetAllCategoriesRequest request) {
	
		log.info("getCategories webservice method invoked");
		
		GetAllCategoriesResponse response = new GetAllCategoriesResponse();
		response.getKategorijaSm().addAll(categoriesService.getAll());

		return response;
	}
	
	
}