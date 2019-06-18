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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.KrajnjiKorisnik;
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

    /*@Autowired
    RoleRepository roleRepository;*/

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    Creator creator;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

    	
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
            KrajnjiKorisnik korisnik = userRepository.getByEmail(loginRequest.getEmail());
         //   Optional<AbstractUser> user = userRepository.findByEmail(loginRequest.getEmail());
            
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), korisnik.getId()));

    	
        
       
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<KrajnjiKorisnik> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        	

    	KrajnjiKorisnik kk = Creator.createKrajnjiKorisnik(encoder.encode(signUpRequest.getPassword()),
    			signUpRequest.getEmail(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber(),
    			signUpRequest.getFirstName(),signUpRequest.getLastName());
    	


        userRepository.save(kk);


        return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.CREATED);
    }
}