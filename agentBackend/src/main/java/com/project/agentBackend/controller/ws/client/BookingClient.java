package com.project.agentBackend.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.agentBackend.model.reservations.managment.AnswerMessageRequest;
import com.project.agentBackend.model.reservations.managment.AnswerMessageResponse;
import com.project.agentBackend.model.reservations.managment.ConfirmBookingRequest;
import com.project.agentBackend.model.reservations.managment.ConfirmBookingResponse;
import com.project.agentBackend.model.reservations.managment.GetBookingRequest;
import com.project.agentBackend.model.reservations.managment.GetBookingResponse;
import com.project.agentBackend.model.reservations.managment.GetBookingsRequest;
import com.project.agentBackend.model.reservations.managment.GetBookingsResponse;
import com.project.agentBackend.model.reservations.managment.GetMessageRequest;
import com.project.agentBackend.model.reservations.managment.GetMessageResponse;
import com.project.agentBackend.model.reservations.managment.MakeBookingRequest;
import com.project.agentBackend.model.reservations.managment.MakeBookingResponse;

public class BookingClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(AccomodationRatingClient.class);

	public GetBookingsResponse getCountry(String country) {

		GetBookingsRequest request = new GetBookingsRequest();
		

		log.info("Requesting location for " + country);

		GetBookingsResponse response = (GetBookingsResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/booking", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/reservations/managment"));
		
		
        
		return response;
	}
	
	public GetBookingResponse getCountrys(String country) {

		GetBookingRequest request = new GetBookingRequest();
		

		log.info("Requesting location for " + country);

		GetBookingResponse response = (GetBookingResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/booking", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/reservations/managment"));
		
		
        
		return response;
	}
	
	public MakeBookingResponse getCountryss(String country) {

		MakeBookingRequest request = new MakeBookingRequest();
		

		log.info("Requesting location for " + country);

		MakeBookingResponse response = (MakeBookingResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/booking", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/reservations/managment"));
		
		
        
		return response;
	}
	
	public ConfirmBookingResponse getsCountry(String country) {

		ConfirmBookingRequest request = new ConfirmBookingRequest();
		

		log.info("Requesting location for " + country);

		ConfirmBookingResponse response = (ConfirmBookingResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/booking", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/reservations/managment"));
		
		
        
		return response;
	}
	
	public  GetMessageResponse getCousntry(String country) {

		 GetMessageRequest request = new  GetMessageRequest();
		

		log.info("Requesting location for " + country);

		 GetMessageResponse response = ( GetMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/booking", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/reservations/managment"));
		
		
        
		return response;
	}
	
	public AnswerMessageResponse getCountrysss(String country) {

		 AnswerMessageRequest request = new  AnswerMessageRequest();
		

		log.info("Requesting location for " + country);

		 AnswerMessageResponse response = ( AnswerMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8836/agent/booking", request,
						new SoapActionCallback(
								"www.model.megatravel.project.com/reservations/managment"));
		
		
        
		return response;
	}
}
