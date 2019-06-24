package com.project.megatravel.users.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.megatravel.users.repository.AdminRepository;
import com.project.megatravel.users.repository.AgentRepository;
import com.project.megatravel.users.repository.KorisnikRepository;

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

    	
       // KrajnjiKorisnik user = userRepository.getByEmail(username);
        
        if(userRepository.getByEmail(username) != null) {
        	return UserPrinciple.build(userRepository.getByEmail(username));
        } else if(agentRepo.getByEmail(username) != null){
        	return UserPrinciple.build(agentRepo.getByEmail(username));
        } else if(adminRepo.getByEmail(username) != null){
        	return UserPrinciple.build(adminRepo.getByEmail(username));
        } else {
        	return null;
        }
        
       
        
     //   Agent agent = agentRepo.getByEmail(username);
      //  Administrator admin = adminRepo.getByEmail(username);
        
        
     /*   if(user != null) {
        	System.out.println("Nasao usera");
        	 return UserPrinciple.build(user);
        } else if(agent != null) {
        	 return UserPrinciple.build(agent);
        } else if(admin != null) {
        	return UserPrinciple.build(admin);
        } else {
        	System.out.println("Nista nije nasao?");
        	return null;
        }*/
               

       
    }
}