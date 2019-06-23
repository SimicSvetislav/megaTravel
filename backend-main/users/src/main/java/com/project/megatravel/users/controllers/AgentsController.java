package com.project.megatravel.users.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.users.request.LoginForm;
import com.project.megatravel.users.UsersApplication;
import com.project.megatravel.users.services.AgentsService;
import com.project.megatravel.users.services.EmailService;

@RestController
@CrossOrigin
public class AgentsController {
	
	private final static Logger logger = Logger.getLogger(AgentsController.class.getName());
	
	@Autowired
	private AgentsService service;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@RequestMapping(method = RequestMethod.POST, path="/agent", produces="application/json")
	public ResponseEntity<Agent> registration(@RequestBody Agent korisnik) {
		
		String generatedPassword = service.generatePassword();
		logger.info("Password for agent: " + korisnik.getKorisnickoIme() + " is " + generatedPassword);
		korisnik.setSifra(encoder.encode(generatedPassword));
		
		emailService.sendSimpleMessage(korisnik.getEmail(), "Agent registration", 
				"Greetings,\n\n"
				+ "This email address is linked to one of registered agents.\n"
				+ "Your credentials are the following.\n\n"
				+ "\tusername: " + korisnik.getKorisnickoIme() + "\n"
				+ "\tpassword: " + generatedPassword + "\n\n"
				+ "Sincerely,\n"
				+ "Megatravel team");
		
		Agent agent = service.save(korisnik);
		
		return new ResponseEntity<Agent>(agent, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/agent")
	public ResponseEntity<Agent> updateUser(@RequestBody Agent korisnik) {
		
		Agent a = service.save(korisnik);
		
		return new ResponseEntity<Agent>(a, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/agent/{id}")
	public ResponseEntity<Agent> deleteAgent(@PathVariable("id") Long id) {
		
		service.deleteById(id);
		
		return new ResponseEntity<Agent>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Agent> getUser(@PathVariable("id") Long id) {
		
		return new ResponseEntity<Agent>(service.getById(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/agent")
	public ResponseEntity<List<Agent>> getUsers() {
		
		return new ResponseEntity<List<Agent>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/agent/verify", produces="application/json")
    public ResponseEntity<Agent> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
    	Agent agent = service.agentVerify(loginRequest);
    		
        return ResponseEntity.ok(agent);
    }
	
	@RequestMapping(method = RequestMethod.GET, path="/agent/mail/{mail}", produces="application/json")
    public ResponseEntity<Agent> agentByEmail(@PathVariable("mail") String mail) {
    	Agent agent = service.agentByEmail(mail);
    		
        return ResponseEntity.ok(agent);
    }

}
