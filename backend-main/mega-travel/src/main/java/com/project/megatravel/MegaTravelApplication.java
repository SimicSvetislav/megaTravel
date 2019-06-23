package com.project.megatravel;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.repository.AdminRepository;
import com.project.megatravel.repository.AgentRepository;
import com.project.megatravel.repository.ExtrasRepository;
import com.project.megatravel.repository.KorisnikRepository;
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
		//testKorisnici();
		//testAgenti();
		//testAdmini();
		
		//testSjModifyTrigger();
		//testSjDeleteTrigger();
		
	}
	
	public static void testSjRepo() {
	
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
		
		//SmestajnaJedinica sj2 = repo.getOneById(3L);
		
		//System.out.println("Fetched: " + sj2.getId());
		
		//SmestajnaJedinica sj3 = repo.deleteById(10L);
		
		//System.out.println("Deleted: " + sj3.getId());
		
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
	
	public static void testSjDeleteTrigger() {
		
		//SoRepository repo = new SoRepository();
		//SmestajniObjekat so = repo.getOneById(1L);
		
		SjRepository repo2 = new SjRepository();
		SmestajnaJedinica sj = repo2.deleteById(2L);
		
		/*so.getSmestajnaJedinica().add(sj);
		
		so = repo.save(so);
		
		System.out.println(so.getSmestajnaJedinica().size());*/
		
		sj = repo2.save(sj);
		
	}
	
	public static void testSoRepo() {
		
		SoRepository repo = new SoRepository();
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setKategorija("GOLD");
		so.setLokacija(Creator.createLokacija(1L, "Novi Sad"));
		so.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so.setTipSmestaja(Creator.createTipSmestaja(1L, "Apartman"));
		so.setZvezdice(4);
		
		so = repo.save(so);
		
		so.setKategorija("SILVER");
		so.setLokacija(Creator.createLokacija(2L, "Beograd"));
		so.setAgent(1L);
		//so.setRejting(Creator.createRejting(8, 32));
		so.setTipSmestaja(Creator.createTipSmestaja(2L, "Hotel"));
		so.setZvezdice(3);
		so.setId(null);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		//sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);
		
		so.getSmestajnaJedinica().add(sj);
		
		sj.setBalkon(false);
		sj.setBrojKreveta(3);
		//sj.setOpis("Kratak koji se prostire u \n dva reda");
		sj.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(5, 22));
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
		//Cena cena = new Cena();
		//cena.setIznos(10.0);
		//cena.setValuta("EURO");
		du.setCena(10.0);
		du.setIme("Bazen");
		du.setJedinicaPlacanja("PO_OSOBI");
		
		du = repo.save(du);
		System.out.println("Saved: " + du.getId());
		
		du.setId(null);
		
		//du = repo.save(du);
		//System.out.println("Saved: " + du.getId());
		
		//cena.setIznos(2000.0);
		//cena.setValuta("DINAR");
		du.setCena(2000.0);
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
		
		KorisnikRepository repo = new KorisnikRepository();
		
		KrajnjiKorisnik kk = new KrajnjiKorisnik();
		kk.setAdresa("Bulevar Oslobodjenja 20");
		kk.setDatumRegistracije(new Date());
		kk.setEmail("pera@gmail.com");
		kk.setIme("Pera");
		//kk.setKategorija("BRONZE");
		kk.setKorisnickoIme("ppera");
		Lokacija l = new Lokacija();
		l.setNaziv("Novi Sad");
		kk.setLokacija(l);
		kk.setPrezime("Peric");
		kk.setSifra("ppera");
		kk.setStanje("AKTIVAN");
		kk.setTelefon("");
		
		kk = repo.save(kk);
		
		System.out.println("Saved: " + kk.getId());
		
		kk.setId(null);
		
		//kk = repo.save(kk);	
		//System.out.println("Saved: " + kk.getId());
		
		KrajnjiKorisnik kk2 = repo.getOneById(1L);
		
		System.out.println("Fetched: " + kk2.getId());
		
		KrajnjiKorisnik kk3 = repo.deleteById(7L);
		
		System.out.println("Deleted: " + kk3.getId());
		
		Collection<KrajnjiKorisnik> sve = repo.getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (KrajnjiKorisnik k : sve) {
			System.out.print(k.getId() + " ");
		}
		
		
		
	}
	
	public static void testAgenti() {
		
		AgentRepository repo = new AgentRepository();
		
		Agent ag = new Agent();
		ag.setAdresa("Bulevar Oslobodjenja 20");
		ag.setDatumRegistracije(new Date());
		ag.setEmail("pera@gmail.com");
		ag.setPoslovniMaticniBroj(1234567899L);
		ag.setKorisnickoIme("ppera");
		Lokacija l = new Lokacija();
		l.setNaziv("Novi Sad");
		ag.setSifra("ppera");
		//ag.setStanje("AKTIVAN");
		ag.setTelefon("060/1234-56-78");
		
		ag = repo.save(ag);
		
		System.out.println("Saved: " + ag.getId());
		
		ag.setId(null);
		
		//ag = repo.save(ag);	
		//System.out.println("Saved: " + ag.getId());
		
		Agent ag2 = repo.getOneById(1L);
		
		System.out.println("Fetched: " + ag2.getId());
		
		Agent ag3 = repo.deleteById(8L);
		
		System.out.println("Deleted: " + ag3.getId());
		
		Collection<Agent> sve = repo.getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (Agent a : sve) {
			System.out.print(a.getId() + " ");
		}
		
	}
	
	public static void testAdmini() {
		
		AdminRepository repo = new AdminRepository();
		
		Administrator ad = new Administrator();
		ad.setAdresa("Bulevar Oslobodjenja 20");
		ad.setDatumRegistracije(new Date());
		ad.setEmail("pera@gmail.com");
		//ag.setPoslovniMaticniBroj(value);
		ad.setKorisnickoIme("ppera");
		Lokacija l = new Lokacija();
		l.setNaziv("Novi Sad");
		ad.setSifra("ppera");
		//ad.setStanje("AKTIVAN");
		ad.setTelefon("");
		ad.setIme("Pera");
		ad.setPrezime("Peric");
		
		ad = repo.save(ad);
		
		System.out.println("Saved: " + ad.getId());
		
		ad.setId(null);
		
		//ad = repo.save(ad);	
		//System.out.println("Saved: " + ad.getId());
		
		Administrator ad2 = repo.getOneById(1L);
		
		System.out.println("Fetched: " + ad2.getId());
		
		Administrator ad3 = repo.deleteById(9L);
		
		System.out.println("Deleted: " + ad3.getId());
		
		Collection<Administrator> sve = repo.getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (Administrator a : sve) {
			System.out.print(a.getId() + " ");
		}
		
		
		
	}
	
}
