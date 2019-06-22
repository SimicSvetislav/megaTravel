package com.project.megatravel.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.ui.LogoutPageGeneratingWebFilter;
import org.springframework.stereotype.Service;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.security.service.UserDetailsServiceImpl;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Properties properties;
	
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
	
	

}
