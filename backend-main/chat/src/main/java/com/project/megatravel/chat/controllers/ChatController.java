package com.project.megatravel.chat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.chat.services.ChatService;
import com.project.megatravel.model.chat.Poruka;

@RestController
@CrossOrigin
public class ChatController {

	@Autowired
	private ChatService service;
	
	@RequestMapping(method = RequestMethod.POST, path="/")
	public ResponseEntity<Boolean> sendMessage(@RequestBody Poruka poruka) {
		
		
		
		return new ResponseEntity<>(true, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	public ResponseEntity<List<Poruka>> getMessagesByConversation(@PathVariable("id") Long id) {
		
		List<Poruka> poruke = service.getMessagesByConversation(id);
		
		return new ResponseEntity<>(poruke, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello4")
	public String test() {
		
		return "Hello 4";
		
	}
	
}
