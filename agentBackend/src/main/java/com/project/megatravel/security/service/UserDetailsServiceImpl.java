package com.project.megatravel.security.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.repository.AgentRepository;
import com.project.megatravel.repository.KorisnikRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikRepository userRepository;

    @Autowired
    private AgentRepository agentRepo;
    



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

    	
        Agent agent = agentRepo.getByEmail(username);
        // Administrator admin = adminRepo.getByEmail(username);
        
        if(agent != null) {
            return UserPrinciple.build(agent);      	
        } else {
        	return null;
        }
        
        

       
    }
    
    public Agent getLoggedUser() {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		   // username = principal.toString(); //anonimni user
			return null;
		}
		return agentRepo.getByEmail(username);
    }
    
}