package com.project.megatravel.controller.ws;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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



@Endpoint
public class BookingEndPoint {
	
	@Autowired
	private RestTemplate restClient;
	
	private static final Logger log = LoggerFactory.getLogger(BookingEndPoint.class);

	private static final String NAMESPACE_URI = "www.model.megatravel.project.com/reservations/managment";

	private static final String RESERVATIONS = "http://reservations/";

	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingsRequest")
	@ResponsePayload
	public GetBookingsResponse getBookings(@RequestPayload GetBookingsRequest request) {
		
		log.info("getBookings webservice method invoked");
		
		GetBookingsResponse response = new GetBookingsResponse();
		ResponseEntity<List<RezervacijaKorisnika>> r = restClient.exchange(
						 RESERVATIONS,
						  HttpMethod.GET,
						  null,
						  new ParameterizedTypeReference<List<RezervacijaKorisnika>>(){});
		response.getRezervacijaKorisnika().addAll(r.getBody());
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUpcomingBookingsRequest")
	@ResponsePayload
	public GetUpcomingBookingsResponse getUpcomingBookings(@RequestPayload GetUpcomingBookingsRequest request) {
		
		log.info("getUpcomingBookings webservice method invoked");

		return new GetUpcomingBookingsResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingRequest")
	@ResponsePayload
	public GetBookingResponse getBooking(@RequestPayload GetBookingRequest request) {
		
		log.info("getBooking webservice method invoked");
		
		GetBookingResponse response = new GetBookingResponse();
		ResponseEntity<RezervacijaKorisnika> r = restClient.getForEntity(RESERVATIONS + request.getBookingId(), RezervacijaKorisnika.class);
		response.setRezervacijaKorisnika(r.getBody());
		
		return new GetBookingResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "makeBookingRequest")
	@ResponsePayload
	public MakeBookingResponse makeBooking(@RequestPayload MakeBookingRequest request) {
		
		log.info("makeBooking webservice method invoked");

		/// TODO: PROVERA DA LI JE VEC ZAUZET SMESTAJ ZA TAJ TERMIN
		
		return new MakeBookingResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "confirmBookingRequest")
	@ResponsePayload
	public ConfirmBookingResponse confirmBooking(@RequestPayload ConfirmBookingRequest request) {
		
		log.info("confirmBooking webservice method invoked");
		
		/// TODO: MENJANJE STATUSA REZERVACIJE


		return new ConfirmBookingResponse();
	}
	

}
