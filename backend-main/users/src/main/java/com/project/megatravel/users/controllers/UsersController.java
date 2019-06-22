package com.project.megatravel.users.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.repository.KorisnikRepository;
import com.project.megatravel.users.request.LoginForm;
import com.project.megatravel.users.response.JwtResponse;
import com.project.megatravel.users.security.jwt.JwtProvider;
import com.project.megatravel.users.services.EmailService;
import com.project.megatravel.users.services.UsersService;

@RestController
@CrossOrigin
public class UsersController {

	
	@Autowired
	private UsersService service;
	
	@Autowired
	private KorisnikRepository kr;
	
	@Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
	private EmailService emailService;
	
/*	@RequestMapping(method = RequestMethod.GET, path="/test")
	public ResponseEntity<String> test() {
		System.out.println("DSLFKJSDF SVE OKKKKK");
		return new ResponseEntity<String>("Sve ok", HttpStatus.OK);
		
	}*/
	
	@RequestMapping(method = RequestMethod.POST, path="/user")
	public ResponseEntity<KrajnjiKorisnik> registration(@RequestBody KrajnjiKorisnik korisnik) {
		
		return new ResponseEntity<KrajnjiKorisnik>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/user")
	public ResponseEntity<KrajnjiKorisnik> updateUser(@RequestBody KrajnjiKorisnik korisnik) {
		
		return new ResponseEntity<KrajnjiKorisnik>(korisnik, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/user/{id}")
	public ResponseEntity<KrajnjiKorisnik> registration(@PathVariable("id") Long id) {
		
		service.remove(id);
		
		KrajnjiKorisnik kk = new KrajnjiKorisnik();
		kk.setId(id);
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/{id}")
	public  ResponseEntity<KrajnjiKorisnik> getUser(@PathVariable("id") Long id) {
		System.out.println("Usao ovde? ");
		
		KrajnjiKorisnik kk = service.getById(id);
		
		System.out.println("KK: " + kk.getIme());
		
		return new ResponseEntity<KrajnjiKorisnik>(service.getById(id), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/block/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KrajnjiKorisnik> blockUser(@PathVariable("id") Long id) {
		
		KrajnjiKorisnik kk = service.getById(id);
		kk.setStanje("BLOKIRAN");
		
		kk = service.save(kk);
		
		emailService.sendSimpleMessage(kk.getEmail(), "Block", "Dear user,\n\n"
				+ "Your account has been blocked.\n"
				+ "Please contact admin for further steps.\n\n"
				+ "Sincerely,\n"
				+ "Megatravel team");
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/activate/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<KrajnjiKorisnik> activateUser(@PathVariable("id") Long id) {
		
		KrajnjiKorisnik kk = service.getById(id);
		kk.setStanje("AKTIVAN");
		
		kk = service.save(kk);
		
		emailService.sendSimpleMessage(kk.getEmail(), "Activated", "Dear user,\n\n"
				+ "Your account is again active.\n"
				+ "Have fun using our app.\n\n"
				+ "Sincerely,\n"
				+ "Megatravel team");
		
		return new ResponseEntity<KrajnjiKorisnik>(kk, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user")
	public ResponseEntity<List<KrajnjiKorisnik>> getUsers() {
		
		return new ResponseEntity<List<KrajnjiKorisnik>>(service.getAll(), HttpStatus.OK);
	
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/user/email")
	public String hello() {
		
		emailService.sendSimpleMessage("sveta.simic.96@gmail.com", "Test", "Testing simple mail");
		
		return "Hello world, email sent";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="user/email/attachment")
	public String sendMailWithAttachment() {
		
		emailService.sendMessageWithAttachment("sveta.simic.96@gmail.com", "Test", "Testing simple mail", "C:\\Users\\Sveta\\Desktop\\ms.pdf");
		
		return "Hello world, email sent";
	}
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

    	KrajnjiKorisnik korisnik = null;

    	
  
    	System.out.println("Ja sam korisnik");
    	korisnik = kr.getByEmail(loginRequest.getEmail());

    	System.out.println("Vratio sam korisnika " + korisnik.getEmail());
    	
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
            
            
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), korisnik.getId()));

    	
        
       
    }
	
	
}
