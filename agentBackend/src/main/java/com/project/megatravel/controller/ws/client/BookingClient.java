package com.project.megatravel.controller.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.reservations.managment.ConfirmBookingRequest;
import com.project.megatravel.model.reservations.managment.ConfirmBookingResponse;
import com.project.megatravel.model.reservations.managment.GetBookingRequest;
import com.project.megatravel.model.reservations.managment.GetBookingResponse;
import com.project.megatravel.model.reservations.managment.GetBookingsRequest;
import com.project.megatravel.model.reservations.managment.GetBookingsResponse;
import com.project.megatravel.model.reservations.managment.GetUpcomingBookingsRequest;
import com.project.megatravel.model.reservations.managment.GetUpcomingBookingsResponse;
import com.project.megatravel.model.reservations.managment.MakeBookingRequest;
import com.project.megatravel.model.reservations.managment.MakeBookingResponse;
import com.project.megatravel.model.users.AgentKredencijali;

public class BookingClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(BookingClient.class);
	
	private static final String WEBSERVICE_URL = "http://localhost:8836/agent/ws/booking";
	private static final String NAMESPACE_URL = "www.model.megatravel.project.com/reservations/managment";


	public GetBookingsResponse getBookings(AgentKredencijali kredencijali) {

		GetBookingsRequest request = new GetBookingsRequest();
		request.setAgentKredencijali(kredencijali);
		

		log.info("Requesting location for getBookings");

		GetBookingsResponse response = (GetBookingsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getBooking  ends successfull");

        
		return response;
	}
	
	public GetUpcomingBookingsResponse getUpcomingBookings(AgentKredencijali kredencijali) {

		GetUpcomingBookingsRequest request = new GetUpcomingBookingsRequest();
		request.setAgentKredencijali(kredencijali);
		

		log.info("Requesting location for getBookings");

		GetUpcomingBookingsResponse response = (GetUpcomingBookingsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getBooking  ends successfull");

        
		return response;
	}
	
	public GetBookingResponse getBooking(long bookingId) {

		GetBookingRequest request = new GetBookingRequest();
		request.setBookingId(bookingId);
		

		log.info("Requesting location for getBooking");

		GetBookingResponse response = (GetBookingResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for getBooking  ends successfull");

		
        
		return response;
	}
	
	public MakeBookingResponse makeBooking(RezervacijaKorisnika rezervacija) {

		MakeBookingRequest request = new MakeBookingRequest();
		request.setRezervacijaKorisnika(rezervacija);
		

		log.info("Requesting location for makeBooking");

		MakeBookingResponse response = (MakeBookingResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for makeBooking ends successfull");

        
		return response;
	}
	
	public ConfirmBookingResponse confirmBooking(long bookingId) {

		ConfirmBookingRequest request = new ConfirmBookingRequest();
		request.setBookingId(bookingId);
		

		log.info("Requesting location for confirmBooking");

		ConfirmBookingResponse response = (ConfirmBookingResponse) getWebServiceTemplate()
				.marshalSendAndReceive(WEBSERVICE_URL, request,
						new SoapActionCallback(
								NAMESPACE_URL));
		
		log.info("Requesting location for confirmBooking ends successfull");

        
		return response;
	}
	
	
}
