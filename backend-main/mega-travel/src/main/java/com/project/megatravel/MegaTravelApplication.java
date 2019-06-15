package com.project.megatravel;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.project.megatravel.model.accomodation.Cena;
import com.project.megatravel.model.accomodation.Cenovnik;
import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.repository.ExtrasRepository;
import com.project.megatravel.repository.RezervacijeRepository;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;
import com.project.megatravel.util.Creator;

@EnableDiscoveryClient
@SpringBootApplication
public class MegaTravelApplication {

	private final static Logger logger = Logger.getLogger(MegaTravelApplication.class.getName());
	
	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(MegaTravelApplication.class, args);
		
		logger.info("Main backend application successfully started");
		
		//testSjRepo();
		//testSoRepo();
		//testRezervacijaRepo();
		//testDodatneUsluge();
		testKorisnici();
		
		testSjModifyTrigger();
		
	}
	
	public static void testSjRepo() {
	
		SjRepository repo = new SjRepository();
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		sj.setPodrazumevaniCenovnik(new Cenovnik());
		sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);
		
		sj = repo.save(sj);
		
		SmestajnaJedinica sj2 = repo.getOneById(3L);
		
		System.out.println("Fetched: " + sj2.getId());
		
		SmestajnaJedinica sj3 = repo.deleteById(10L);
		
		System.out.println("Deleted: " + sj3.getId());
		
		Collection<SmestajnaJedinica> sve = repo.getAll();
		
		for (SmestajnaJedinica s : sve) {
			System.out.print(s.getId() + " ");
		}
		
	}
	
	public static void testSjModifyTrigger() {
		
		//SoRepository repo = new SoRepository();
		//SmestajniObjekat so = repo.getOneById(1L);
		
		SjRepository repo2 = new SjRepository();
		SmestajnaJedinica sj = repo2.getOneById(2L);
		
		/*so.getSmestajnaJedinica().add(sj);
		
		so = repo.save(so);
		
		System.out.println(so.getSmestajnaJedinica().size());*/
		
		sj.setBalkon(true);
		
		sj = repo2.save(sj);
		
	}
	
	public static void testSoRepo() {
		
		SoRepository repo = new SoRepository();
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setKategorija("GOLD");
		so.setLokacija(Creator.createLokacija(1L, "Novi Sad"));
		so.setAgent(new Agent());
		so.setRejting(Creator.createRejting(20, 89));
		so.setTipSmestaja("HOTEL");
		so.setZvezdice(4);
		
		so = repo.save(so);
		
		so.setKategorija("SILVER");
		so.setLokacija(Creator.createLokacija(2L, "Beograd"));
		so.setAgent(new Agent());
		so.setRejting(Creator.createRejting(8, 32));
		so.setTipSmestaja("APARTMAN");
		so.setZvezdice(3);
		so.setId(null);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		sj.setPodrazumevaniCenovnik(new Cenovnik());
		sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);
		
		so.getSmestajnaJedinica().add(sj);
		
		sj.setBalkon(false);
		sj.setBrojKreveta(3);
		sj.setOpis("Kratak koji se prostire u \n dva reda");
		sj.setOtkazivanje(new Otkazivanje());
		sj.setPodrazumevaniCenovnik(new Cenovnik());
		sj.setRejting(Creator.createRejting(5, 22));
		sj.setSObjekat(2L);
		
		so.getSmestajnaJedinica().add(sj);
		
		//so = repo.save(so);
		
		System.out.println(so.getId());
		
		SmestajniObjekat sj2 = repo.getOneById(3L);
		
		System.out.println("Fetched: " + sj2.getId());
		
		SmestajniObjekat sj3 = repo.deleteById(11L);
		
		System.out.println("Deleted: " + sj3.getId());
		
		Collection<SmestajniObjekat> sve = repo.getAll();
		
		System.out.print("ID-evi objekata sa brojem smestajnih jedinica: ");
		for (SmestajniObjekat s : sve) {
			System.out.print(s.getId() + "[" + s.getSmestajnaJedinica().size() + "] ");
		}
		
	}
	
	public static void testRezervacijaRepo() {
		
		RezervacijeRepository repo = new RezervacijeRepository();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setDatumPocetka(new Date());
		rez.setDatumZavrsetka(new Date());
		rez.setPopust(10.0);
		rez.setCenaSmestaja(30.0);
		rez.setKorisnik(1L);
		rez.setStanje("REZERVISANO");
		
		/*SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		sj.setPodrazumevaniCenovnik(new Cenovnik());
		sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);*/
		
		rez.setSmestajnaJedinica(1L);
		
		rez = repo.save(rez);
		
		System.out.println(rez.getId());
		
		RezervacijaKorisnika rez2 = repo.getOneById(3L);
		
		System.out.println("Fetched: " + rez2.getId());
		
		RezervacijaKorisnika rez3 = repo.deleteById(7L);
		
		System.out.println("Deleted: " + rez3.getId());
		
		Collection<RezervacijaKorisnika> sve = repo.getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (RezervacijaKorisnika r : sve) {
			System.out.print(r.getId() + " ");
		}
		
	}
	
	public static void testDodatneUsluge() {
		
		ExtrasRepository repo = new ExtrasRepository();
		
		DodatnaUsluga du = new DodatnaUsluga();
		Cena cena = new Cena();
		cena.setIznos(10.0);
		cena.setValuta("EURO");
		du.setCena(cena);
		du.setIme("Bazen");
		du.setJedinicaPlacanja("PO_OSOBI");
		
		du = repo.save(du);
		System.out.println("Saved: " + du.getId());
		
		du.setId(null);
		
		//du = repo.save(du);
		//System.out.println("Saved: " + du.getId());
		
		cena.setIznos(2000.0);
		cena.setValuta("DINAR");
		du.setCena(cena);
		du.setIme("Wi-fi");
		du.setJedinicaPlacanja("PO_DANU");
		du.setId(null);
		
		//du = repo.save(du);
		//System.out.println("Saved: " + du.getId());
		
		du.setId(null);
		
		//du = repo.save(du);	
		//System.out.println("Saved: " + du.getId());
		
		DodatnaUsluga du2 = repo.getOneById(1L);
		
		System.out.println("Fetched: " + du2.getId());
		
		DodatnaUsluga du3 = repo.deleteById(11L);
		
		System.out.println("Deleted: " + du3.getId());
		
		Collection<DodatnaUsluga> sve = repo.getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (DodatnaUsluga d : sve) {
			System.out.print(d.getId() + " ");
		}
		
	}
	
	public static void testKorisnici() {
		
	}
	
}
