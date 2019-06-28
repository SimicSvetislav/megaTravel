package com.project.megatravel.rbm.controllers;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.dto.KrajnjiKorisnikDTO;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.manager.Manager;
import com.project.megatravel.rbm.services.KorisniciService;
import com.project.megatravel.util.Creator;

@RestController
public class KorisnikController {
	private static Logger log = LoggerFactory.getLogger(KorisnikController.class);

	private final KorisniciService service;

	private final KieContainer kieContainer;

	@Autowired
	public KorisnikController(KieContainer kieContainer, KorisniciService service) {
		//log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		this.service = service;
	}

	@RequestMapping(value = "/client/classify", method=RequestMethod.PUT, consumes="application/json")
	public String classifyClient(@RequestBody KrajnjiKorisnikDTO kk) {
		
		log.info("Classifying client...");
		
		return service.classify(kk);
	}
	
	@RequestMapping(value = "/client", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> classifyClient() {
		
		log.info("Classifying client... (2)");
		
		//KieServices ks = KieServices.Factory.get();
        //KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  Manager.getSession(kieContainer);
        
        kSession.getAgenda().getAgendaGroup("klijent").setFocus(); 
		
		KrajnjiKorisnik korisnik = Creator.createKrajnjiKorisnik(1L, "NA", "10/10/2018");
		
		RezervacijaKorisnika rez = Creator.createRezervacija(4, 310, 0, "10/04/2018", "03/05/2019", "10/05/2019", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez.setProcenatOtkazivanje(-1.0);
		korisnik.getRezervacije().add(rez);
		
		kSession.insert(korisnik);
		
		int fired = kSession.fireAllRules();
		
		System.out.println("Fired - " + fired );
		
		return new ResponseEntity<String>(korisnik.getKategorija(), HttpStatus.OK);
		
		//return service.classify(kk);
	}
	
	@RequestMapping(value = "/client2", method=RequestMethod.GET)
	public String classifyClient2() {
		
		log.info("Classifying client... (2a)");
		
		KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("klijent").setFocus();
		
		KrajnjiKorisnik korisnik = Creator.createKrajnjiKorisnik(2L, "NA", "10/10/2018");
		
		RezervacijaKorisnika rez = Creator.createRezervacija(4, 310, 0, "10/04/2018", "03/05/2019", "10/05/2019", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez.setProcenatOtkazivanje(-1.0);
		korisnik.getRezervacije().add(rez);
		
		kSession.insert(korisnik);
		
		int fired = kSession.fireAllRules();
		
		System.out.println("Fired - " + fired );
		
		return korisnik.getKategorija();
		
		//return service.classify(kk);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public String getQuestions() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("smestaj").setFocus();
        
        // BRONZE
        SmestajniObjekat s = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0L, 0L));
        kSession.insert(s);
        
        SmestajniObjekat s2 = Creator.createSmestajniObjekat(2, "NA", Creator.createRejting(2L, 3L));
        kSession.insert(s2);
        
        SmestajniObjekat s3 = Creator.createSmestajniObjekat(3, "NA", Creator.createRejting(10L, 25L));
        kSession.insert(s3);
        
        // SILVER
        SmestajniObjekat s4 = Creator.createSmestajniObjekat(4, "NA", Creator.createRejting(10L, 29L));
        kSession.insert(s4);
       
        SmestajnaJedinica j1 = Creator.createSmestajnaJedinica(1L, s4);
        s4.getSmestajneJedinice().add(j1);
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2L, s4);
        s4.getSmestajneJedinice().add(j2);
        
        RezervacijaKorisnika rez1 = Creator.createRezervacija(1, 100, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j2);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/09/2018", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 500, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j2);
        
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 800, 0, "09/09/2018", "10/10/2018", "09/04/2019", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 800, 0, "01/04/2019", "10/10/2017", "19/09/2017", "REALIZOVANO", 
        		j1);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        
        // GOLD
        SmestajniObjekat s5 = Creator.createSmestajniObjekat(5, "NA", Creator.createRejting(10L, 39L));
        kSession.insert(s5);
        
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3L, s5);
        s5.getSmestajneJedinice().add(j3);
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4L, s5);
        s5.getSmestajneJedinice().add(j4);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 4, "22/05/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j3);
        
        RezervacijaKorisnika rez8 = Creator.createRezervacija(8, 100, 4, "22/05/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j3);
        
        RezervacijaKorisnika rez9 = Creator.createRezervacija(9, 100, 5, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j4);
        
        RezervacijaKorisnika rez10 = Creator.createRezervacija(10, 500, 4, "22/05/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j4);
        
        RezervacijaKorisnika rez11 = Creator.createRezervacija(10, 500, 5, "22/05/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j4);
        
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rez9);
        kSession.insert(rez10);
        kSession.insert(rez11);
        
        // NOT GOLD - poslednja ocena manja od 3
        SmestajniObjekat s6 = Creator.createSmestajniObjekat(6, "NA", Creator.createRejting(10L, 39L));
        kSession.insert(s6);
        
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5L, s6);
        s6.getSmestajneJedinice().add(j5);
        SmestajnaJedinica j6 = Creator.createSmestajnaJedinica(6L, s6);
        s6.getSmestajneJedinice().add(j6);
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j5);
        
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j6);
        
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 100, 2, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j6);
        
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j5);
        
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j6);
        
        kSession.insert(rez12);
        kSession.insert(rez13);
        kSession.insert(rez14);
        kSession.insert(rez15);
        kSession.insert(rez16);
        
        // PLATINUM
        SmestajniObjekat s7 = Creator.createSmestajniObjekat(7, "GOLD", Creator.createRejting(10L, 39L));
        kSession.insert(s7);
        
        SmestajnaJedinica j7 = Creator.createSmestajnaJedinica(7L, s7);
        s7.getSmestajneJedinice().add(j7);
        SmestajnaJedinica j8 = Creator.createSmestajnaJedinica(8L, s7);
        s7.getSmestajneJedinice().add(j8);
        
        RezervacijaKorisnika rez17 = Creator.createRezervacija(17, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez18 = Creator.createRezervacija(18, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez19 = Creator.createRezervacija(19, 100, 2, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez20 = Creator.createRezervacija(20, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j8);
        
        RezervacijaKorisnika rez21 = Creator.createRezervacija(21, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j8);
        
        kSession.insert(rez17);
        kSession.insert(rez18);
        kSession.insert(rez19);
        kSession.insert(rez20);
        kSession.insert(rez21);
        
        int fired = kSession.fireAllRules();
        
        return "OK";
        
	}
	
	
	
}
