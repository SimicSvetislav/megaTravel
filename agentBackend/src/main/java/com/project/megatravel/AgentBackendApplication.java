package com.project.megatravel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.repository.CategoriesRepository;
import com.project.megatravel.repository.ExtrasRepository;
import com.project.megatravel.repository.RezervacijeRepository;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;
import com.project.megatravel.repository.TypesRepository;
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
		
//		testSoRepo();
//		
//		testSjRepo(1L);
//		setDodatneUsluge(1L);
//		
//		testSjRepo(2L);
//		setDodatneUsluge(2L);
		
		//testRezervacijaRepo();
		// testDodatneUslugeRepo();
		//testKategorijeRepo();
		// testTipSmestajaRepo();
		

		
//		Agent agent = Creator.createAgent("Luka", "Jovanovic", "lukajvnv@gmail.com", "lukajvnv@gmail.com", "Drage Spasic7", "064/449-86-28", 1556l,
//				"$2a$10$n1JcNevkDMmKCW2OVE5e4uDSqHNu9U4jeWBqclSvbbdMj72pr2WLm");
//		AgentRepository agentRepo = new AgentRepository(); 
//		agentRepo.save(agent);
	}
	
	public static void testSoRepo() {
		
		SoRepository repo = new SoRepository();
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setNaziv("Talija");
		so.setOpis("Mali udoban porodicni smestaj za bilo koje doba godine. Nasi gosti cine ljudi koji nam se vracaju svake godine iznova i iznova");
		so.setKategorijaSm(Creator.createKategorijaSm(1l, 0));
		so.setPodrazumevaniCenovnik(Creator.createCenovnik(1554382800000l, 1556974800000l, 200l, 1L));   //04.04 - 04.05
		so.getCenovnici().add(Creator.createCenovnik(1554382800000l, 1556974800000l, 200l, 1L));         //04.04 - 04.05
		so.getCenovnici().add(Creator.createCenovnik(1559826000000l, 1562418000000l, 200l, 1L));         //06.06 - 06.06
		so.setKategorija("GOLD");
		so.setLokacija(Creator.createLokacija(1L, "Novi Sad"));
		so.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so.setTipSmestaja(Creator.createTipSmetaja(1L, "hotel"));
		so.setZvezdice(4);
		
		so = repo.save(so);
		
		SmestajniObjekat so1 = new SmestajniObjekat();
		so1.setNaziv("Negresku");
		so1.setOpis("Spektakularni smestaj najveci u Jugoistocnoj Evropi. Kompleks cine pet hotela 3 otvorena i 2 zatvorena bazena, spa centar, sportksi centar");
		so1.setKategorijaSm(Creator.createKategorijaSm(2l, 5));
		so1.setPodrazumevaniCenovnik(Creator.createCenovnik(1554382800000l, 1556974800000l, 200l, 1L));   //04.04 - 04.05
		so1.getCenovnici().add(Creator.createCenovnik(1554382800000l, 1556974800000l, 200l, 1L));         //04.04 - 04.05
		so1.getCenovnici().add(Creator.createCenovnik(1559826000000l, 1562418000000l, 200l, 1L));         //06.06 - 06.06
		so1.setKategorija("SILVER");
		so1.setLokacija(Creator.createLokacija(2L, "Beograd"));
		so1.setAgent(1L);
		//so.setRejting(Creator.createRejting(8, 32));
		so1.setTipSmestaja(Creator.createTipSmetaja(2L, "aparthotel"));
		so1.setZvezdice(3);
		so1.setId(null);
		
		so1 = repo.save(so1);

		
		SmestajniObjekat so2 = new SmestajniObjekat();
		so2.setNaziv("La fiesta");
		so2.setOpis("Vila La fiesta predstavlja idealni smestaj za konferencijski turizam, razne seminare i sajmove");
		so2.setKategorijaSm(Creator.createKategorijaSm(1l, 0));
		so2.setPodrazumevaniCenovnik(Creator.createCenovnik(1554382800000l, 1556974800000l, 200l, 1L));   //04.04 - 04.05
		so2.getCenovnici().add(Creator.createCenovnik(1554382800000l, 1556974800000l, 200l, 1L));         //04.04 - 04.05
		so2.getCenovnici().add(Creator.createCenovnik(1559826000000l, 1562418000000l, 200l, 1L));         //06.06 - 06.06
		so2.setKategorija("bronze");
		so2.setLokacija(Creator.createLokacija(2L, "Nis"));
		so2.setAgent(1L);
		//2 so.setRejting(Creator.createRejting(8, 32));
		so2.setTipSmestaja(Creator.createTipSmetaja(3L, "motel"));
		so2.setZvezdice(3);
		so2.setId(null);
		  
		so2 = repo.save(so2);
		
		
		
		Collection<SmestajniObjekat> sve = repo.getAll();
		
		System.out.print("ID-evi objekata sa brojem smestajnih jedinica: ");
		for (SmestajniObjekat s : sve) {
			System.out.print(s.getId() + "[" + s.getSmestajnaJedinica().size() + "] ");
		}
		
	}
	
	public static void testSjRepo(Long objectId) {
		
		SjRepository repo = new SjRepository();
		SoRepository r = new SoRepository();
		//List<SmestajnaJedinica> lista = new ArrayList<SmestajnaJedinica>();
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		sj.setOpis("Soba namenjena za one koji zele da uzivaju u miru, a ipak imaju dovoljno prostora samo za sebe");
		sj.setOznaka("K1");
		sj.setOtkazivanje(Creator.createOtkazivanje(new BigInteger("20"), true));
		sj.setSObjekat(objectId);
		
		sj = repo.save(sj);
		//lista.add(sj);
		
		SmestajnaJedinica sj2 = new SmestajnaJedinica();
		sj2.setBalkon(false);
		sj2.setBrojKreveta(3);
		sj2.setOpis("Idealna za bracne parove sa decom, bracni krevet dovoljno velik i za malo dete");
		sj2.setOznaka("K2");
		sj2.setOtkazivanje(Creator.createOtkazivanje(new BigInteger("0"), false));
		sj2.setSObjekat(objectId);
		sj2.setId(null);
		
		sj2 = repo.save(sj2);
		//lista.add(sj2);
		
		SmestajnaJedinica sj3 = new SmestajnaJedinica();
		sj3.setBalkon(true);
		sj3.setBrojKreveta(5);
		sj3.setOpis("Apartman koji poseduje dve sobe sa krevetima");
		sj3.setOznaka("K3");
		sj3.setOtkazivanje(Creator.createOtkazivanje(new BigInteger("5"), true));
		sj3.setSObjekat(objectId);
		sj3.setId(null);
		
		sj3 = repo.save(sj3);
		//lista.add(sj3);
		
		SmestajniObjekat objekat = r.getOneById(objectId);
		objekat.getSmestajnaJedinica().add(sj);
		objekat.getSmestajnaJedinica().add(sj2);
		objekat.getSmestajnaJedinica().add(sj3);
		// objekat.setSmestajnaJedinica(lista); //dodat seter
		r.save(objekat);
		
		Collection<SmestajnaJedinica> sve = repo.getAll();
		
		for (SmestajnaJedinica s : sve) {
			System.out.print(s.getId() + " ");
		}
		
	}
	
	public static void testRezervacijaRepo() {
		
		RezervacijeRepository repo = new RezervacijeRepository();
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1554382800000l, 1559826000000l, 1561053091417l, 10.0, 30.0, 1L, "REZERVISANO", 1L);
		rez = repo.save(rez);
		
		RezervacijaKorisnika rez2 = Creator.createRezervacija(1554382800000l, 1559826000000l, 1561053091417l, 0.0, 130.0, 1L, "REZERVISANO", 2L);
		rez2 = repo.save(rez2);
		
		RezervacijaKorisnika rez3 = Creator.createRezervacija(1554382800000l, 1559826000000l, 1561053091417l, 20.0, 230.0, 1L, "OTKAZANO", 1L);
		rez3 = repo.save(rez3);
		
		RezervacijaKorisnika rez4 = Creator.createRezervacija(1554382800000l, 1559826000000l, 1561053091417l, 15.0, 300.0, 1L, "REZERVISANO", 3L);
		rez4 = repo.save(rez4);
		
		RezervacijaKorisnika rez5 = Creator.createRezervacija(1554382800000l, 1559826000000l, 1561053091417l, 30.0, 430.0, 1L, "POTVRDJENO", 1L);
		rez5 = repo.save(rez5);

		

		
		
		
		
		System.out.print("ID-evi rezervacija: ");
		for (RezervacijaKorisnika r : repo.getAll()) {
			System.out.print(r.getId() + " ");
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
	
	public static void setDodatneUsluge(Long objectId) {
		SoRepository r = new SoRepository();
		List<DodatnaUsluga> lista = new ArrayList<DodatnaUsluga>();
		
		
		lista.add(Creator.createDodatnaUsluge(1L, "wifi", 200D, "RSD"));
		lista.add(Creator.createDodatnaUsluge(2L, "bazen", 200D, "eur"));
		lista.add(Creator.createDodatnaUsluge(3L, "room service", 200D, "RSD"));
		lista.add(Creator.createDodatnaUsluge(4L, "budjenje", 100D, "RSD"));

		
		SmestajniObjekat objekat = r.getOneById(objectId);
		objekat.getDodatnaUsluga().addAll(lista);
		//objekat.setDodatnaUsluga(lista); //dodat seter
		r.save(objekat);
	}
	
	public static void testKategorijeRepo() {
		TypesRepository repo = new TypesRepository();
		
		TipSmestaja tip1 = Creator.createTipSmetaja(1l, "hotel");
		tip1 = repo.save(tip1);
		
		TipSmestaja tip2 = Creator.createTipSmetaja(2l, "hotel and breakfast");
		tip2 = repo.save(tip2);
		
		TipSmestaja tip3 = Creator.createTipSmetaja(3l, "hostel");
		tip3 = repo.save(tip3);
		
		TipSmestaja tip4 = Creator.createTipSmetaja(4l, "motel");
		tip4 = repo.save(tip4);
		
		TipSmestaja tip5 = Creator.createTipSmetaja(5l, "apartman");
		tip5 = repo.save(tip5);
		
	}
	
	public static void testTipSmestajaRepo() {
		CategoriesRepository repo = new CategoriesRepository();
		
		KategorijaSm kategorija1 = Creator.createKategorijaSm(1l, 0);
		kategorija1 = repo.save(kategorija1);
		
		KategorijaSm kategorija2 = Creator.createKategorijaSm(2l, 1);
		kategorija2 = repo.save(kategorija2);
		
		KategorijaSm kategorija3 = Creator.createKategorijaSm(3l, 2);
		kategorija3 = repo.save(kategorija3);
		
		KategorijaSm kategorija4 = Creator.createKategorijaSm(4l, 3);
		kategorija4 = repo.save(kategorija4);
		
		KategorijaSm kategorija5 = Creator.createKategorijaSm(5l, 4);
		kategorija5 = repo.save(kategorija5);
		
	}

}
