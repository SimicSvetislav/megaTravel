package com.project.megatravel.users.services;




import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.repository.AdminRepository;
import com.project.megatravel.users.repository.AgentRepository;
import com.project.megatravel.users.repository.KorisnikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikRepository userRepository;

    @Autowired
    AgentRepository agentRepo;
    
    @Autowired
    AdminRepository adminRepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

    	
        KrajnjiKorisnik user = userRepository.getByEmail(username);
        Agent agent = agentRepo.getByEmail(username);
        Administrator admin = adminRepo.getByEmail(username);
        
        
        if(user != null) {
        	System.out.println("Nasao usera");
        	 return UserPrinciple.build(user);
        } else if(agent != null) {
        	 return UserPrinciple.build(agent);
        } else if(admin != null) {
        	return UserPrinciple.build(admin);
        } else {
        	System.out.println("Nista nije nasao?");
        	return null;
        }
               

       
    }
}