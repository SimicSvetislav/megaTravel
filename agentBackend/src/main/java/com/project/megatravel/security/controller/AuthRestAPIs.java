package com.project.megatravel.security.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.repository.AgentRepository;
import com.project.megatravel.security.jwt.JwtProvider;
import com.project.megatravel.security.request.LoginForm;
import com.project.megatravel.security.request.SignUpForm;
import com.project.megatravel.security.response.JwtResponse;
import com.project.megatravel.util.Creator;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AgentRepository agentRepo;
    
 
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
   
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
    	Agent agent;
    	
    	 
    		agent= agentRepo.getByEmail(loginRequest.getEmail());
    		if(agent == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    	
    	
    	Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
			            new UsernamePasswordAuthenticationToken(
			                    loginRequest.getEmail(),
			                    loginRequest.getPassword()
			            )
			    );
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("ne validan");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Get auth: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         //   KrajnjiKorisnik korisnik = userRepository.getByEmail(loginRequest.getEmail());
         //   Optional<AbstractUser> user = userRepository.findByEmail(loginRequest.getEmail());
            
            
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), agent.getId()));

    	
        
       
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Agent> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        	
    		Agent agent = Creator.createAgent(encoder.encode(signUpRequest.getPassword()),
        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber());
        	
    		agent = agentRepo.save(agent);
            return new ResponseEntity<Agent>(agent, HttpStatus.CREATED);   
    }
}