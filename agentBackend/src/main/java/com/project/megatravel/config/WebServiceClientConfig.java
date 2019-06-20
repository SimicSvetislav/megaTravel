//package com.project.megatravel.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//
//import com.project.megatravel.controller.ws.client.AccomodationClient;
//import com.project.megatravel.controller.ws.client.AccomodationRatingClient;
//import com.project.megatravel.controller.ws.client.AgentClient;
//import com.project.megatravel.controller.ws.client.BookingClient;
//
//@Configuration
//public class WebServiceClientConfig {
//
//	@Bean
//	public Jaxb2Marshaller marshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		// this package must match the package in the <generatePackage> specified in
//		// pom.xml
//		//marshaller.setContextPath("hello.wsdl");
//		marshaller.setContextPath("com.project.megatravel.model.messages.rating.managment");
//		return marshaller;
//	}
//	
//	@Bean
//	public AccomodationRatingClient ratingClient(Jaxb2Marshaller marshaller) {
//		AccomodationRatingClient client = new AccomodationRatingClient();
//		client.setDefaultUri("http://localhost:8080/agent");
//		client.setMarshaller(marshaller);
//		client.setUnmarshaller(marshaller);
//		return client;
//	}
//	
//	@Bean
//	public Jaxb2Marshaller agentClientMarshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		// this package must match the package in the <generatePackage> specified in
//		// pom.xml
//		//marshaller.setContextPath("hello.wsdl");
//		marshaller.setContextPath("com.project.megatravel.model.messages.agent");
//		return marshaller;
//	}
//	
//	@Bean
//	public AgentClient agentClient(Jaxb2Marshaller agentClientMarshaller) {
//		AgentClient client = new AgentClient();
//		client.setDefaultUri("http://localhost:8080/agent");
//		client.setMarshaller(agentClientMarshaller);
//		client.setUnmarshaller(agentClientMarshaller);
//		return client;
//	}
//	
//	@Bean
//	public Jaxb2Marshaller accomodationMarshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		// this package must match the package in the <generatePackage> specified in
//		// pom.xml
//		//marshaller.setContextPath("hello.wsdl");
//		marshaller.setContextPath("com.project.megatravel.model.accomodation.managment");
//		return marshaller;
//	}
//	
//	@Bean
//	public AccomodationClient accomodationClient(Jaxb2Marshaller accomodationMarshaller) {
//		AccomodationClient client = new AccomodationClient();
//		client.setDefaultUri("http://localhost:8080/agent");
//		client.setMarshaller(accomodationMarshaller);
//		client.setUnmarshaller(accomodationMarshaller);
//		return client;
//	}
//	
//	@Bean
//	public Jaxb2Marshaller bookingMarshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		// this package must match the package in the <generatePackage> specified in
//		// pom.xml
//		//marshaller.setContextPath("hello.wsdl");
//		marshaller.setContextPath("com.project.megatravel.model.reservations.managment");
//		return marshaller;
//	}
//	
//	@Bean
//	public BookingClient countryClient(Jaxb2Marshaller bookingMarshaller) {
//		BookingClient client = new BookingClient();
//		client.setDefaultUri("http://localhost:8080/agent");
//		client.setMarshaller(bookingMarshaller);
//		client.setUnmarshaller(bookingMarshaller);
//		return client;
//	}
//}
