package com.project.megatravel.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.model.messages.rating.managment.AnswerMessageRequest;
import com.project.megatravel.model.messages.rating.managment.AnswerMessageResponse;
import com.project.megatravel.model.messages.rating.managment.GetCommentsByObjectRequest;
import com.project.megatravel.model.messages.rating.managment.GetCommentsByObjectResponse;
import com.project.megatravel.model.messages.rating.managment.GetCommentsByUnitRequest;
import com.project.megatravel.model.messages.rating.managment.GetCommentsByUnitResponse;
import com.project.megatravel.model.messages.rating.managment.GetMessageRequest;
import com.project.megatravel.model.messages.rating.managment.GetMessageResponse;
import com.project.megatravel.model.messages.rating.managment.GetMessagesRequest;
import com.project.megatravel.model.messages.rating.managment.GetMessagesResponse;
import com.project.megatravel.model.messages.rating.managment.GetRatingByObjectRequest;
import com.project.megatravel.model.messages.rating.managment.GetRatingByObjectResponse;
import com.project.megatravel.model.messages.rating.managment.GetRatingByUnitRequest;
import com.project.megatravel.model.messages.rating.managment.GetRatingByUnitResponse;
import com.project.megatravel.model.users.AgentKredencijali;


public class AccomodationRatingClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AccomodationRatingClient.class);
	
//	private static final String WEBSERVICE_URL = "http://localhost:8836/agent/ws/accomodationRating";
	private static final String WEBSERVICE_URL = "http://localhost:8111/ws/accomodationRating";

	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/messages/rating/managment";


	public GetRatingByObjectResponse getRatingByObject(long objectId) {

		GetRatingByObjectRequest request = new GetRatingByObjectRequest();
		request.setObjectId(objectId);
		

		log.info("Requesting location for getRatingByObject");

		GetRatingByObjectResponse response = (GetRatingByObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getRatingByObject ends successfull");

        
		return response;
	}
	
	public GetRatingByUnitResponse getRatingByUnit(long unitId) {

		GetRatingByUnitRequest request = new GetRatingByUnitRequest();
		request.setUnitId(unitId);
		

		log.info("Requesting location for getRatingByUnit");

		GetRatingByUnitResponse response = (GetRatingByUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getRatingByUnit ends successfull");

        
		return response;
	}
	
	public GetCommentsByObjectResponse getCommentsByObject(long objectId) {

		GetCommentsByObjectRequest request = new GetCommentsByObjectRequest();
		request.setObjectId(objectId);
		

		log.info("Requesting location for getRatingByObject");

		GetCommentsByObjectResponse response = (GetCommentsByObjectResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getCommentsByObject ends successfull");

        
		return response;
	}
	
	public GetCommentsByUnitResponse getCommentsByUnit(long unitId) {

		GetCommentsByUnitRequest request = new GetCommentsByUnitRequest();
		request.setUnitId(unitId);
		

		log.info("Requesting location for getRatingByUnit");

		GetCommentsByUnitResponse response = (GetCommentsByUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getCommentsByUnit ends successfull");

        
		return response;
	}
	
	public  GetMessagesResponse getMessages(AgentKredencijali agentKredencijali) {

		 GetMessagesRequest request = new  GetMessagesRequest();
		 request.setAgentKredencijali(agentKredencijali);
		

		log.info("Requesting location for getMessages");

		 GetMessagesResponse response = (GetMessagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getMessages ends successfull");

      
		return response;
	}
	
	public  GetMessageResponse getMessage(long messageId) {

		 GetMessageRequest request = new  GetMessageRequest();
		 request.setMessageId(messageId);
		 

		log.info("Requesting location for getMessage");

		 GetMessageResponse response = ( GetMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
			log.info("Requesting location for getMessage ends successfull");

       
		return response;
	}
	
	public AnswerMessageResponse answerMessage(Poruka poruka) {

		 AnswerMessageRequest request = new  AnswerMessageRequest();
		 request.setMessage(poruka);
		 

		log.info("Requesting location for answerMessage");

		 AnswerMessageResponse response = ( AnswerMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
			log.info("Requesting location for answerMessage ends successfull");

       
		return response;
	}
}