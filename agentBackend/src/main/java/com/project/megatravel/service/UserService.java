package com.project.megatravel.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.megatravel.controller.ws.client.AgentClient;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.security.service.UserDetailsServiceImpl;
import com.project.megatravel.util.Creator;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private AgentClient agentWsClient;
	
	@Autowired
	private UserDetailsServiceImpl loggedUserServie;
	
//	@Autowired
//    private Authentication authentication;
	
//	@Autowired
//	private UserPrinciple userPrinciple;
	
	public String genPassword(String pass) {
		System.out.println("username iz konf fajla:" + properties.getProperty("username"));
		Agent a = loggedUserServie.getLoggedUser();
		if(a != null) {
			System.out.println("username logovanog je: "  + a.getKorisnickoIme());
		} else {
			System.out.println("nijedan korisnik nije ulogovan");
		}
		
		return passwordEncoder.encode(pass);
	}
	
	public void syncData() throws Exception {
		String username = properties.getProperty("username", "");
		String pass = properties.getProperty("password", "");
		
		// autentifikacija
		Agent a = agentWsClient.agentAuthentification(Creator.createKredencijali(username, pass)).getAgent();
		if(a == null) {
			System.out.println("user service Error");
			throw new Exception();
		}
		
	}
	
	

}
