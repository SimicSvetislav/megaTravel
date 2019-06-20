package com.project.megatravel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.repository.ExtrasRepository;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;
import com.project.megatravel.util.Creator;

@SpringBootApplication
public class AgentBackendApplication {

	//private final static Logger logger = Logger.getLogger(AgentBackendApplication.class.getName());
	
	public static void main(String[] args) {
		
		//logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			//logger.info("Connection with database established");
		} catch (Exception e) {
			//logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(AgentBackendApplication.class, args);
		
		//testSoRepo();
		 //testSjRepo();
		 //setDodatneUsluge();
		// testDodatneUslugeRepo();

	}
	
	public static void testSoRepo() {
		
		SoRepository repo = new SoRepository();
		
		SmestajniObjekat so = new SmestajniObjekat();
		//so.setNaziv("Talija");
		so.setKategorija("GOLD");
		so.setLokacija(Creator.createLokacija(1L, "Novi Sad"));
		so.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so.setTipSmestaja(Creator.createTipSmetaja(1L, "hotel"));
		so.setZvezdice(4);
		
		so = repo.save(so);
		
		//so.setNaziv("Negresku");
		so.setKategorija("SILVER");
		so.setLokacija(Creator.createLokacija(2L, "Beograd"));
		so.setAgent(1L);
		//so.setRejting(Creator.createRejting(8, 32));
		so.setTipSmestaja(Creator.createTipSmetaja(2L, "aparthotel"));
		so.setZvezdice(3);
		so.setId(null);
		
		so = repo.save(so);

		
		//so.setNaziv("La Fiesta");
		so.setKategorija("bronze");
		so.setLokacija(Creator.createLokacija(2L, "Nis"));
		so.setAgent(1L);
		// so.setRejting(Creator.createRejting(8, 32));
		so.setTipSmestaja(Creator.createTipSmetaja(3L, "motel"));
		so.setZvezdice(3);
		so.setId(null);
		
		so = repo.save(so);
		
		
		
		Collection<SmestajniObjekat> sve = repo.getAll();
		
		System.out.print("ID-evi objekata sa brojem smestajnih jedinica: ");
		for (SmestajniObjekat s : sve) {
			System.out.print(s.getId() + "[" + s.getSmestajnaJedinica().size() + "] ");
		}
		
	}
	
	public static void testSjRepo() {
		
		SjRepository repo = new SjRepository();
		SoRepository r = new SoRepository();
		List<SmestajnaJedinica> lista = new ArrayList<SmestajnaJedinica>();
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		//sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);
		
		sj = repo.save(sj);
		lista.add(sj);
		
		SmestajnaJedinica sj2 = new SmestajnaJedinica();
		sj2.setBalkon(false);
		sj2.setBrojKreveta(3);
		//sj.setOpis("Kratak opis");
		sj2.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj2.setSObjekat(1L);
		sj2.setId(null);
		
		sj2 = repo.save(sj2);
		lista.add(sj2);
		
		SmestajnaJedinica sj3 = new SmestajnaJedinica();
		sj3.setBalkon(true);
		sj3.setBrojKreveta(1);
		//sj.setOpis("Kratak opis");
		sj3.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj3.setSObjekat(1L);
		sj3.setId(null);
		
		sj3 = repo.save(sj3);
		lista.add(sj3);
		
		SmestajniObjekat objekat = r.getOneById(1L);
		// objekat.setSmestajnaJedinica(lista); //dodat seter
		r.save(objekat);
		
		Collection<SmestajnaJedinica> sve = repo.getAll();
		
		for (SmestajnaJedinica s : sve) {
			System.out.print(s.getId() + " ");
		}
		
	}
	
	public static void testDodatneUslugeRepo() {
		ExtrasRepository repo = new ExtrasRepository();
		
		DodatnaUsluga usluga = Creator.createDodatnaUsluge(null, "wifi", 200D, "RSD");
		usluga = repo.save(usluga);
		
		DodatnaUsluga usluga1 = Creator.createDodatnaUsluge(null, "bazen", 200D, "eur");
		usluga1 = repo.save(usluga1);
		
		DodatnaUsluga usluga3 = Creator.createDodatnaUsluge(null, "room service", 200D, "RSD");
		usluga3 = repo.save(usluga3);

		
		DodatnaUsluga usluga4 = Creator.createDodatnaUsluge(null, "budjenje", 100D, "RSD");
		usluga4 = repo.save(usluga4);

		DodatnaUsluga usluga5 = Creator.createDodatnaUsluge(null, "pranje vesa", 200D, "chf");
		usluga5 = repo.save(usluga5);

		
	}
	
	public static void setDodatneUsluge() {
		SoRepository r = new SoRepository();
		List<DodatnaUsluga> lista = new ArrayList<DodatnaUsluga>();
		
		
		lista.add(Creator.createDodatnaUsluge(1L, "wifi", 200D, "RSD"));
		lista.add(Creator.createDodatnaUsluge(2L, "bazen", 200D, "eur"));
		lista.add(Creator.createDodatnaUsluge(3L, "room service", 200D, "RSD"));
		lista.add(Creator.createDodatnaUsluge(4L, "budjenje", 100D, "RSD"));

		
		SmestajniObjekat objekat = r.getOneById(1L);
		//objekat.setDodatnaUsluga(lista); //dodat seter
		r.save(objekat);
	}

}
