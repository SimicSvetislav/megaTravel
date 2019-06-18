package com.project.megatravel;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.repository.KorisnikRepository;
import com.project.megatravel.repository.SjRepository;

@SpringBootApplication
public class AgentBackendApplication {

	private final static Logger logger = Logger.getLogger(AgentBackendApplication.class.getName());
	
	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(AgentBackendApplication.class, args);
		
		logger.info("Agent application successfully started now");
		
		java.util.Collection<KrajnjiKorisnik> sve = new KorisnikRepository().getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (KrajnjiKorisnik k : sve) {
			System.out.print(k.getId() + " ");
		}
		
		SjRepository repo = new SjRepository();
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		//sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);
		
		sj = repo.save(sj);
		
		SmestajnaJedinica sj2 = repo.getOneById(3L);
		
		System.out.println("Fetched: " + sj2.getId());
		
		SmestajnaJedinica sj3 = repo.deleteById(10L);
		
		System.out.println("Deleted: " + sj3.getId());
		
		Collection<SmestajnaJedinica> f = repo.getAll();
		
		for (SmestajnaJedinica s : f) {
			System.out.print(s.getId() + " ");
		}
		
		
	}

}
