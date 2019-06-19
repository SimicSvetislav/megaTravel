package com.project.megatravel.controller.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.project.megatravel.model.reservations.managment.AnswerMessageRequest;
import com.project.megatravel.model.reservations.managment.AnswerMessageResponse;
import com.project.megatravel.model.reservations.managment.ConfirmBookingRequest;
import com.project.megatravel.model.reservations.managment.ConfirmBookingResponse;
import com.project.megatravel.model.reservations.managment.GetBookingRequest;
import com.project.megatravel.model.reservations.managment.GetBookingResponse;
import com.project.megatravel.model.reservations.managment.GetBookingsRequest;
import com.project.megatravel.model.reservations.managment.GetBookingsResponse;
import com.project.megatravel.model.reservations.managment.GetMessageRequest;
import com.project.megatravel.model.reservations.managment.GetMessageResponse;
import com.project.megatravel.model.reservations.managment.MakeBookingRequest;
import com.project.megatravel.model.reservations.managment.MakeBookingResponse;



@Endpoint
public class BookingEndPoint {

	private static final String NAMESPACE_URI = "www.model.megatravel.project.com/reservations/managment";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingsRequest")
	@ResponsePayload
	public GetBookingsResponse getBookings(@RequestPayload GetBookingsRequest request) {
		

		return new GetBookingsResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingRequest")
	@ResponsePayload
	public GetBookingResponse getBooking(@RequestPayload GetBookingRequest request) {
		

		return new GetBookingResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "makeBookingsRequest")
	@ResponsePayload
	public MakeBookingResponse makeBooking(@RequestPayload MakeBookingRequest request) {
		

		return new MakeBookingResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "confirmBookingRequest")
	@ResponsePayload
	public ConfirmBookingResponse confirmBooking(@RequestPayload ConfirmBookingRequest request) {
		

		return new ConfirmBookingResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageRequest")
	@ResponsePayload
	public GetMessageResponse confirmBooking(@RequestPayload GetMessageRequest request) {
		

		return new GetMessageResponse();
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "answerMessageRequest")
	@ResponsePayload
	public AnswerMessageResponse confirmBooking(@RequestPayload AnswerMessageRequest request) {
		

		return new AnswerMessageResponse();
	}
}
