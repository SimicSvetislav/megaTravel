package com.project.megatravel.users.controllers;



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
import com.project.megatravel.users.repository.AdminRepository;
import com.project.megatravel.users.repository.AgentRepository;
import com.project.megatravel.users.repository.KorisnikRepository;
import com.project.megatravel.users.request.LoginForm;
import com.project.megatravel.users.request.SignUpForm;
import com.project.megatravel.users.response.JwtResponse;
import com.project.megatravel.users.security.jwt.JwtProvider;
import com.project.megatravel.users.services.UsersService;
import com.project.megatravel.util.Creator;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	UsersService usersService;
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    KorisnikRepository userRepository;
    
    @Autowired
    AgentRepository agentRepo;
    
    @Autowired
    AdminRepository adminRepo;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    Creator creator;

    @PostMapping("/signin/{role}")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest, @PathVariable String role) {

    	KrajnjiKorisnik korisnik;
    	Administrator admin;
    	Agent agent;
    	
    	TKorisnik retVal = null;
    	
    	
    	if(role.contains("admin")) {
    		System.out.println("Ja sam admir");
    		admin  = adminRepo.getByEmail(loginRequest.getEmail());
    		if(admin == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    		retVal = admin;
    	} else if(role.contains("user")) {
    		System.out.println("Ja sam korisnik");
    		korisnik = userRepository.getByEmail(loginRequest.getEmail());
    		if(korisnik == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    		retVal = korisnik;
    	} else if(role.contains("agent")) {
    		System.out.println("Ja sam agent");
    		agent= agentRepo.getByEmail(loginRequest.getEmail());
    		if(agent == null) {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    		retVal = agent;
    	}
    	
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

    @PostMapping(value = "/signup/{role}")
    public ResponseEntity<TKorisnik> registerUser(@Valid @RequestBody SignUpForm signUpRequest,@PathVariable String role) {
        	
    	
    	if(role.contains("admin")) {
    		Administrator kk = Creator.createAdmin(encoder.encode(signUpRequest.getPassword()),
        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber(),
        			signUpRequest.getFirstName(),signUpRequest.getLastName());
        	
            adminRepo.save(kk);


            return new ResponseEntity<TKorisnik>(kk, HttpStatus.CREATED);
    	} else if(role.contains("user")) {
    		KrajnjiKorisnik kk = Creator.createKrajnjiKorisnik(encoder.encode(signUpRequest.getPassword()),
        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber(),
        			signUpRequest.getFirstName(),signUpRequest.getLastName());
        	


            userRepository.save(kk);


            return new ResponseEntity<TKorisnik>(kk, HttpStatus.CREATED);
    	} else if(role.contains("agent")) {
    		Agent kk = Creator.createAgent(encoder.encode(signUpRequest.getPassword()),
        			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber());
        	


    		agentRepo.save(kk);


            return new ResponseEntity<TKorisnik>(kk, HttpStatus.CREATED);
    	} else {
    		  return new ResponseEntity<TKorisnik>(HttpStatus.FORBIDDEN);
    	}

      
    }
}