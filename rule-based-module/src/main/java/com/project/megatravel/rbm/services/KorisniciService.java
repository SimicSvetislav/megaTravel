package com.project.megatravel.rbm.services;

import javax.annotation.PostConstruct;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.dto.KrajnjiKorisnikDTO;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.util.Creator;

@Service
public class KorisniciService {

	private static Logger log = LoggerFactory.getLogger(KorisniciService.class);

	private final KieContainer kieContainer;
	private KieSession kSession;

	@Autowired
	public KorisniciService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
		this.kSession =  kieContainer.newKieSession("ksession-rules");
	}
	
	@PostConstruct
	public void init() {
		
		
	}

	public String classify(KrajnjiKorisnikDTO kk) {
	   
        kSession.getAgenda().getAgendaGroup("klijent").setFocus();
		
		KrajnjiKorisnik korisnik = Creator.createKrajnjiKorisnik(kk.getId(), kk.getKategorija(), kk.getDatumRegistracije());
		
		RezervacijaKorisnika rez = Creator.createRezervacija(4, 310, 0, "10/04/2018", "03/05/2019", "10/05/2019", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez.setProcenatOtkazivanje(-1.0);
		korisnik.getRezervacije().add(rez);
		
		kSession.insert(korisnik);
		
		int fired = kSession.fireAllRules();
		
		System.out.println("Fired - " + fired );
		
		return korisnik.getKategorija();
		
	}
}
