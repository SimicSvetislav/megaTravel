package com.project.megatravel.security.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.TKorisnik;
import com.project.megatravel.repository.AgentRepository;
import com.project.megatravel.repository.KorisnikRepository;
import com.project.megatravel.security.jwt.JwtProvider;
import com.project.megatravel.security.request.LoginForm;
import com.project.megatravel.security.response.JwtResponse;
import com.project.megatravel.service.UserService;
import com.project.megatravel.util.Creator;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	UserService usersService;
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    KorisnikRepository userRepository;
    
    @Autowired
    AgentRepository agentRepo;
    
  


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    Creator creator;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

//    	KrajnjiKorisnik korisnik;
//    	Administrator admin;
    	Agent agent;
    	
    	TKorisnik retVal = null;
    	
    	
    	 
    		agent= agentRepo.getByEmail(loginRequest.getEmail());
    		if(agent == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    		retVal = agent;
    	
    	
    	Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Get auth: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         //   KrajnjiKorisnik korisnik = userRepository.getByEmail(loginRequest.getEmail());
         //   Optional<AbstractUser> user = userRepository.findByEmail(loginRequest.getEmail());
            
            
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), retVal.getId()));

    	
        
       
    }

//    @PostMapping(value = "/signup/{role}")
//    public ResponseEntity<TKorisnik> registerUser(@Valid @RequestBody SignUpForm signUpRequest,@PathVariable String role) {
//        	
//    	
//    	if(role.contains("admin")) {
//    		Administrator kk = Creator.createAdmin(encoder.encode(signUpRequest.getPassword()),
//        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber(),
//        			signUpRequest.getFirstName(),signUpRequest.getLastName());
//        	
//            adminRepo.save(kk);
//
//
//            return new ResponseEntity<TKorisnik>(kk, HttpStatus.CREATED);
//    	} else if(role.contains("user")) {
//    		KrajnjiKorisnik kk = Creator.createKrajnjiKorisnik(encoder.encode(signUpRequest.getPassword()),
//        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber(),
//        			signUpRequest.getFirstName(),signUpRequest.getLastName());
//        	
//
//
//            userRepository.save(kk);
//
//
//            return new ResponseEntity<TKorisnik>(kk, HttpStatus.CREATED);
//    	} else if(role.contains("agent")) {
//    		Agent kk = Creator.createAgent(encoder.encode(signUpRequest.getPassword()),
//        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber());
//        	
//
//
//    		agentRepo.save(kk);
//
//
//            return new ResponseEntity<TKorisnik>(kk, HttpStatus.CREATED);
//    	} else {
//    		  return new ResponseEntity<TKorisnik>(HttpStatus.FORBIDDEN);
//    	}
//
//      
//    }
}