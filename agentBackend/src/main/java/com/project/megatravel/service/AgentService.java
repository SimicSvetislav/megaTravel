package com.project.megatravel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.megatravel.controller.ws.client.AccomodationClient;
import com.project.megatravel.controller.ws.client.AgentClient;
import com.project.megatravel.controller.ws.client.BookingClient;
import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.security.service.UserDetailsServiceImpl;
import com.project.megatravel.util.Creator;
import com.project.megatravel.util.errors.AuthentificationException;

@Service
public class AgentService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private AgentClient agentWsClient;
	
	@Autowired
	private AccomodationClient accomodationWsClient;
	
	@Autowired
	private BookingClient bookingWsClient;
	
	@Autowired
	private AccomodationService accomodationService;
	
	@Autowired
	private BookingService bookingService;
	
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
	
	public void syncData() throws AuthentificationException, Exception {
//		String username = properties.getProperty("username", "");
//		String pass = properties.getProperty("password", "");
//		
//		// autentifikacija
//		Agent a = agentWsClient.agentAuthentification(Creator.createKredencijali(username, pass)).getAgent();
//		if(a == null) {
//			System.out.println("user service Error");
//			throw new AuthentificationException();
//		}
		
		// autentifikacija
		Agent a = agentAuthentification();
		
		try {
			//prikupljanje resursa putem ws
			List<TipSmestaja> tipovi = accomodationWsClient.getTypes().getTipSmestaja();
			List<DodatnaUsluga> usluge = accomodationWsClient.getExtras().getDodatnaUsluga();
			List<SmestajniObjekat> objekti = accomodationWsClient.getObjects(a).getSmestajniObjekat();
			List<KategorijaSm> kategorije = accomodationWsClient.getCategories().getKategorijaSm();

			List<SmestajnaJedinica> jedinice = new ArrayList<SmestajnaJedinica>();
			for(SmestajniObjekat objekat : objekti) {
				jedinice.addAll(objekat.getSmestajnaJedinica());
			}
			
			List<RezervacijaKorisnika> rezervacije = bookingWsClient.getBookings(a).getRezervacijaKorisnika();
			List<RezervacijaKorisnika> rezervacijeZaCuvanje = new ArrayList<RezervacijaKorisnika>();
			for(RezervacijaKorisnika rez : rezervacije) {
				SmestajnaJedinica jed = jedinice.stream().filter(jedinica -> jedinica.getId() == rez.getSmestajnaJedinica()).findFirst().orElse(null);
				if(jed != null) {
					rezervacijeZaCuvanje.add(rez);
				}
			}
			
			//upisivanje u lokalnu bazu
			accomodationService.overrideData(tipovi, kategorije, usluge, objekti, jedinice);
			bookingService.overrideData(rezervacijeZaCuvanje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	public Agent agentAuthentification() throws AuthentificationException {
		String username = properties.getProperty("username", "");
		String pass = properties.getProperty("password", "");
		
		Agent a = agentWsClient.agentAuthentification(Creator.createKredencijali(username, pass)).getAgent();
		if(a == null) {
			System.out.println("user service Error");
			throw new AuthentificationException();
		}
		return a;
	}
	
	public Agent agentByMail(String mail) {
		return agentWsClient.getAgent(mail).getAgent();
	}
	

}
