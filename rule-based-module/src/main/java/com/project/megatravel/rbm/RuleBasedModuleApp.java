package com.project.megatravel.rbm;


import java.util.logging.Logger;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.repository.KorisnikRepository;
import com.project.megatravel.rbm.repository.RezervacijeRepository;
import com.project.megatravel.util.Creator;

@SpringBootApplication
@EnableScheduling
public class RuleBasedModuleApp {

	private static Logger logger = Logger.getLogger(RuleBasedModuleApp.class.getName());

	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		

		SpringApplication.run(RuleBasedModuleApp.class, args);
		
		
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		//ApplicationContext ctx = SpringApplication.run(RuleBasedModuleApp.class, args);
		/*String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		logger.info(sb.toString());*/
		//populate();
		/*
		SjRepository sjRepo = new SjRepository();
		SoRepository soRepo = new SoRepository();
		AgentRepository aRepo = new AgentRepository();
		RezervacijeRepository rRepo = new RezervacijeRepository();
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(2, "NA", Creator.createRejting(5, 9));
		
		
		SmestajnaJedinica j = Creator.createSmestajnaJedinica(3, so, Creator.createRejting(2, 9));
		so.getSmestajneJedinice().add(j);
		//soRepo.save(so);
		
		Cenovnik cen = new Cenovnik();
		Cena c = new Cena();
		c.setIznos(15);
		cen.setCena(c);
		j.setPodrazumevaniCenovnik(cen);
		//sjRepo.save(j);
		
		KorisnikRepository krepo = new KorisnikRepository();
		
		KrajnjiKorisnik k = Creator.createKrajnjiKorisnik(2, "NA", "10/05/2019");
		k.setKorisnickoIme("pera");
		k.setSifra("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra");
		k.setEmail("pera@gmail.com");
		//krepo.save(k);
		
		k.setKorisnickoIme("steva");*/
		//k.setId(1L);
		//krepo.save(k);
		
		
		//KrajnjiKorisnik kk = krepo.getOneById(10L);
		
		
		//System.out.print(kk.getIme());
		
		/*Agent a = new Agent();
		a.setAdresa("Agentska");
		a.setDatumRegistracije(new Date());
		a.setEmail("agent@gmail.com");
		a.setId(1L);
		a.setIme("Goran");
		a.setPrezime("Goranovic");
		a.setKorisnickoIme("gg");
		a.setPoslovniMaticniBroj(32434214214L);
		a.setSifra("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra");
		
		aRepo.save(a);*/
		/*
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j);
		
		rRepo.save(rez);
		
		rez=Creator.createRezervacija(12, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
		rez.setKorisnik(Creator.createKrajnjiKorisnik(11, "", ""));
		
		rRepo.save(rez);*/
		
	}
	
	private static void populate() {
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(2, 9));
		
		RezervacijeRepository repo1 = new RezervacijeRepository();

        SmestajnaJedinica j = Creator.createSmestajnaJedinica(1, so, Creator.createRejting(2, 9));
        
        so.getSmestajneJedinice().add(j);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 110, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 120, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 130, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 140, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j);
       
        
        repo1.save(rez);
        repo1.save(rez2);
        repo1.save(rez3);
        repo1.save(rez4);
        repo1.save(rez5);
        
        /*SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2, so2, Creator.createRejting(2, 9));
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3, so2, Creator.createRejting(2, 9));
        
        so2.getSmestajneJedinice().add(j2);
        so2.getSmestajneJedinice().add(j3);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j2);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j2);
        RezervacijaKorisnika rez8 = Creator.createRezervacija(8, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j3);
        RezervacijaKorisnika rez9 = Creator.createRezervacija(9, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j3);
       
        
        repo1.save(rez6);
        repo1.save(rez7);
        repo1.save(rez8);
        repo1.save(rez9);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4, so3, Creator.createRejting(2, 9));
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5, so3, Creator.createRejting(2, 9));
        
        so3.getSmestajneJedinice().add(j4);
        so3.getSmestajneJedinice().add(j5);
        
        RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j4);
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j5);
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j4);
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j4);
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j5);
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 100, 0, "04/01/2014", "10/01/2014", "15/01/2014", "REALIZOVANO", j5);
        RezervacijaKorisnika rez17 = Creator.createRezervacija(17, 100, 0, "04/01/2011", "10/01/2011", "15/01/2011", "REALIZOVANO", j4);
        
        repo1.save(rez12);
        repo1.save(rez13);
        repo1.save(rez14);
        repo1.save(rez15);
        repo1.save(rez16);
        repo1.save(rez17);
        repo1.save(rez11);*/

	}
	
	public static void pop2() {
		
		KorisnikRepository krepo = new KorisnikRepository();
		RezervacijeRepository rrepo = new RezervacijeRepository();
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 400, 0, "10/04/2019", "17/04/2019", "20/04/2019", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez.setProcenatOtkazivanje(-1.0);
		
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "10/04/2018", "18/11/2018", "20/11/2018", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez2.setProcenatOtkazivanje(-1.0);
		
		RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 450, 0, "10/04/2018", "20/04/2019", "24/04/2019", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez3.setProcenatOtkazivanje(-1.0);
		
		RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 310, 0, "10/04/2018", "03/05/2019", "10/05/2019", "REALIZOVANO", 
				Creator.createSmestajnaJedinica(1, Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0, 0))));
		rez4.setProcenatOtkazivanje(-1.0);
		
        RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(11, Creator.createSmestajniObjekat(11, "GOLD")));
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(12, Creator.createSmestajniObjekat(12, "PLATINUM")));
        
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(13, Creator.createSmestajniObjekat(13, "SILVER")));
        
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 500, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(14, Creator.createSmestajniObjekat(14, "BRONZE")));
        
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 800, "09/09/2018", "09/04/2019", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(15, Creator.createSmestajniObjekat(15, "GOLD")));
        
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 800, "09/09/2017", "19/09/2017", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(15, Creator.createSmestajniObjekat(16, "GOLD")));
        
		
		// END: REZERVACIJE
		
		// KLIJENTI
		
		KrajnjiKorisnik k = Creator.createKrajnjiKorisnik(1, "NA", "10/04/2019");
        k.getRezervacije().add(rez);
        k.getRezervacije().add(rez2);
		
        KrajnjiKorisnik k2 = Creator.createKrajnjiKorisnik(2, "NA", "10/04/2018");
        k2.getRezervacije().add(rez2);
        k2.getRezervacije().add(rez2);
        
        KrajnjiKorisnik k3 = Creator.createKrajnjiKorisnik(3, "NA", "10/04/2018");
        k3.getRezervacije().add(rez2);
        
        KrajnjiKorisnik k4 = Creator.createKrajnjiKorisnik(4, "NA", "10/04/2018");
        k4.getRezervacije().add(rez4);
        
        KrajnjiKorisnik k5 = Creator.createKrajnjiKorisnik(5, "NA", "10/04/2019");
        k5.getRezervacije().add(rez3);
        
        KrajnjiKorisnik k6 = Creator.createKrajnjiKorisnik(6, "NA", "10/04/2019");
        k6.getRezervacije().add(rez3);
        k6.getRezervacije().add(rez2);
        k6.getRezervacije().add(rez3);
        k6.getRezervacije().add(rez2);
        
        KrajnjiKorisnik k7 = Creator.createKrajnjiKorisnik(7, "NA", "10/04/2019");
        k7.getRezervacije().add(rez11);
        k7.getRezervacije().add(rez12);
        k7.getRezervacije().add(rez13);
        k7.getRezervacije().add(rez13);
        k7.getRezervacije().add(rez13);
        k7.getRezervacije().add(rez12);
        k7.getRezervacije().add(rez12);
        k7.getRezervacije().add(rez11);
        k7.getRezervacije().add(rez11);
        k7.getRezervacije().add(rez11);
        
        // Deset rezervacija u SILVER objektima
        KrajnjiKorisnik k8 = Creator.createKrajnjiKorisnik(8, "NA", "10/04/2019");
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        
        // SILVER
        KrajnjiKorisnik k9 = Creator.createKrajnjiKorisnik(9, "NA", "10/04/2019");
        k9.getRezervacije().add(rez13);
        k9.getRezervacije().add(rez15);
        //k9.getRezervacije().add(rez15);
        k9.getRezervacije().add(rez14);
        k9.getRezervacije().add(rez14);
        
        // Stare rezervacije
        KrajnjiKorisnik k10 = Creator.createKrajnjiKorisnik(10, "NA", "10/04/2019");
        k10.getRezervacije().add(rez13);
        k10.getRezervacije().add(rez15);
        k10.getRezervacije().add(rez15);
        k10.getRezervacije().add(rez16);
        k10.getRezervacije().add(rez16);
        
        // PLATINUM klijent
        KrajnjiKorisnik k11 = Creator.createKrajnjiKorisnik(11, "NA", "10/04/2019");
        k11.getRezervacije().add(rez13);
        k11.getRezervacije().add(rez15);
        k11.getRezervacije().add(rez15);
        k11.getRezervacije().add(rez14);
        k11.getRezervacije().add(rez16);
        
        krepo.save(k);
        krepo.save(k2);
        krepo.save(k3);
        krepo.save(k4);
        krepo.save(k5);
        krepo.save(k6);
        
        rrepo.save(rez);
        rrepo.save(rez2);
        rrepo.save(rez3);
        rrepo.save(rez4);
        
	} 
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("rule-based-module", "rule-based-module-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(1_000);
		return kContainer;
	}
	
}
