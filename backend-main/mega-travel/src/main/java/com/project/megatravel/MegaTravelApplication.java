package com.project.megatravel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.project.megatravel.model.accomodation.Cenovnik;
import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.SmestajniObjekat.Slike;
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
		
		//testDodatneUsluge();
		testSoRepo();
		testSjRepo();
		
		//testRezervacijaRepo();
		
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
        
        SmestajnaJedinica sj1 = new SmestajnaJedinica();
        sj1.setBalkon(true);
        sj1.setBrojKreveta(3);
        //sj.setOpis("Kratak opis");
        sj1.setOtkazivanje(new Otkazivanje());
        //sj.setPodrazumevaniCenovnik(new Cenovnik());
        //sj.setRejting(Creator.createRejting(7, 30));
        sj1.setSObjekat(1L);
        
        sj1 = repo.save(sj1);
        
        SmestajnaJedinica sj2 = new SmestajnaJedinica();
        sj2.setBalkon(false);
        sj2.setBrojKreveta(4);
        //sj.setOpis("Kratak opis");
        sj2.setOtkazivanje(new Otkazivanje());
        //sj.setPodrazumevaniCenovnik(new Cenovnik());
        //sj.setRejting(Creator.createRejting(7, 30));
        sj2.setSObjekat(1L);
        
        sj2 = repo.save(sj2);
        
        //SmestajnaJedinica sj2 = repo.getOneById(3L);
        
        //System.out.println("Fetched: " + sj2.getId());
        
        //SmestajnaJedinica sj3 = repo.deleteById(10L);
        
        //System.out.println("Deleted: " + sj3.getId());
        
        Collection<SmestajnaJedinica> sve = repo.getAll();
        
        for (SmestajnaJedinica s : sve) {
            System.out.print(s.getId() + " ");
        }
        
    }
	
	/*public static void testSjRepo() {
	
		SjRepository repo = new SjRepository();
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBalkon(true);
		sj.setBrojKreveta(1);
		//sj.setOpis("Kratak opis");
		sj.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj.setSObjekat(1L);
		
		SmestajnaJedinica sj1 = new SmestajnaJedinica();
		sj1.setBalkon(true);
		sj1.setBrojKreveta(3);
		//sj.setOpis("Kratak opis");
		sj1.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj1.setSObjekat(1L);
		
		sj1 = repo.save(sj1);
		
		SmestajnaJedinica sj2 = new SmestajnaJedinica();
		sj2.setBalkon(false);
		sj2.setBrojKreveta(4);
		//sj.setOpis("Kratak opis");
		sj2.setOtkazivanje(new Otkazivanje());
		//sj.setPodrazumevaniCenovnik(new Cenovnik());
		//sj.setRejting(Creator.createRejting(7, 30));
		sj2.setSObjekat(1L);
		
		sj2 = repo.save(sj2);
		
		//SmestajnaJedinica sj2 = repo.getOneById(3L);
		
		//System.out.println("Fetched: " + sj2.getId());
		
		//SmestajnaJedinica sj3 = repo.deleteById(10L);
		
		//System.out.println("Deleted: " + sj3.getId());
		
		Collection<SmestajnaJedinica> sve = repo.getAll();
		
		for (SmestajnaJedinica s : sve) {
			System.out.print(s.getId() + " ");
		}
		
	}*/
	
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
	
	@SuppressWarnings("deprecation")
	public static void testSoRepo() {
		
		SoRepository repo = new SoRepository();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String datPoc = "6/6/2019";
		String datKraj = "10/10/2019";
		
		//Cenovnik
		Cenovnik c = new Cenovnik();
		c.setCena(25l);

		try {
			c.setKraj(sdf.parse(datKraj));
			c.setPocetak(sdf.parse(datPoc));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setSmestaj(1l);
		
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setKategorija("GOLD");
		so.setLokacija(Creator.createLokacija(1L, "Novi Sad"));
		so.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so.setTipSmestaja(Creator.createTipSmestaja(1L, "Apartman"));
		so.setZvezdice(4);
		
		List<SmestajniObjekat.Slike> slike = new ArrayList<>();
		
		Slike sl = new Slike();
		Slike sl1 = new Slike();
		Slike sl2 = new Slike();
		
		sl.setPutanja("http://www.hotel-starikrovovi.com/img/galerija/moskva3.jpg");
		sl.setValue("1");
		slike.add(sl);
		
		sl1.setPutanja("http://www.hotel-starikrovovi.com/img/galerija/moskva1.jpg");
		sl1.setValue("2");
		slike.add(sl1);
		
		sl2.setPutanja("http://hotel-starikrovovi.com/img/restoran/restoran-novi-0.jpg");
		sl2.setValue("3");
		slike.add(sl2);
		
		//so.setSlike(slike);
		so.getSlike().addAll(slike);
		so.setNaziv("Hotel vila Moskva");
		so.setOpis(" Komforne sobe i apartmani učiniće Vaš boravak u hotelu Stari krovovi nezaboravnim. Bilo da Vam je potreban dnevni odmor za predah od puta, prenoćište, smeštaj za vikend ili na duži period u vreme održavanja brojnih manifestacija i događaja u Novom Sadu ( sajmova, festivala, itd.), na raspolaganju su Vam apartmani, jednokrevetne, dvokrevetne, kao i sobe sa tri i četiri udobna kreveta.\r\n" + 
				"		");
		so.setPodrazumevaniCenovnik(c);
		
		so = repo.save(so);
		
		
		//drugi objekat
		SmestajniObjekat so1 = new SmestajniObjekat();
		so1.setKategorija("SILVER");
		so1.setLokacija(Creator.createLokacija(2L, "Hanioti"));
		so1.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so1.setTipSmestaja(Creator.createTipSmestaja(1L, "Hotel"));
		so1.setZvezdice(4);
		
		List<SmestajniObjekat.Slike> slike2 = new ArrayList<>();
		
		Slike sl3 = new Slike();
		Slike sl4 = new Slike();
		Slike sl5 = new Slike();
		Slike sl6 = new Slike();
		Slike sl7 = new Slike();
		
		sl3.setPutanja("http://www.travelland.rs/content_pictures/resized/527723-279.jpg");
		sl3.setValue("1");
		slike2.add(sl3);
		
		sl4.setPutanja("http://www.travelland.rs/content_pictures/resized/531024-698.jpg");
		sl4.setValue("2");
		slike2.add(sl4);
		
		sl5.setPutanja("http://www.travelland.rs/content_pictures/resized/439309-183.jpg");
		sl5.setValue("3");
		slike2.add(sl5);
		
		sl6.setPutanja("http://www.travelland.rs/content_pictures/resized/261240-781.jpg");
		sl6.setValue("3");
		slike2.add(sl6);
		
		sl7.setPutanja("http://www.travelland.rs/content_pictures/resized/913161-970.jpg");
		sl7.setValue("3");
		slike2.add(sl7);
		
		
		
		so1.getSlike().addAll(slike2);
		so1.setNaziv("Kasandra hoteli");
		so1.setOpis(" Hotel je renoviran 2007.godine. Glavna hotelska zgrada ima dva krila u kojima su smeštene sobe, dok se suitovi i apartmani nalaze u ostale tri zgrade u okviru hotela.\r\n" + 
				"\r\n" + 
				"Smešten na samoj obali, u blizini gradskog parka. Grand Otel raspolaže sopstvenom plažom, bazenom, parking prostorom i nudi izuzetnu uslugu restorana, bara, snack-bara na plaži itd. Sobe su opremljene radiom, telefonom, frižiderom i klima-uređajem (tokom jula i avgusta upotreba je uračunata u cenu). U restoranu je obavezan dressing code (pravilo oblačenja).\r\n" + 
				"\r\n" + 
				"Wi-Fi je uz doplatu 1 € dnevno po uredjaju.");
		
		
		so1 = repo.save(so1);
		
		//treci objekat
		
		SmestajniObjekat so2 = new SmestajniObjekat();
		so2.setKategorija("NULL");
		so2.setLokacija(Creator.createLokacija(2L, "Jerisos"));
		so2.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so2.setTipSmestaja(Creator.createTipSmestaja(1L, "Hotel"));
		so2.setZvezdice(4);
		
		List<SmestajniObjekat.Slike> slike3 = new ArrayList<>();
		
		Slike sl34 = new Slike();
		Slike sl44 = new Slike();
		Slike sl54 = new Slike();
		Slike sl64 = new Slike();
		
		
		sl34.setPutanja("https://www.barcino.travel/images/albums/1516276483-Letovanje_grcka_apartmani_Jerisos_(2).jpg");
		sl34.setValue("1");
		slike3.add(sl34);
		
		sl44.setPutanja("https://www.barcino.travel/images/albums/1516276478-Letovanje_grcka_apartmani_Jerisos_(20).jpg");
		sl44.setValue("2");
		slike3.add(sl44);
		
		sl54.setPutanja("https://www.barcino.travel/images/albums/1516276562-Letovanje_grcka_apartmani_Jerisos_(56).jpg");
		sl54.setValue("3");
		slike3.add(sl54);
		
		sl64.setPutanja("https://www.barcino.travel/images/albums/1516276561-Letovanje_grcka_apartmani_Jerisos_(55).jpg");
		sl64.setValue("3");
		slike3.add(sl64);
		
		
		
		
		so2.getSlike().addAll(slike3);
		so2.setNaziv("Vila Maria");
		so2.setOpis("Vila Maria se nalazi na samo 80 metara od plaže, od centralnog dela Jerisosa. Smeštajne jedinice se nalaze na prvom i drugom spratu vile. Posedujemo dvokrevetne i dvokrevetne sa pomoćnim krevetom apartmane, čevorokrevetne apartmane i četvorokrevetne duplekse. Svi su opremljeni kuhinjom (mini električni šporet, frižider, posuđe) koja je izdvojena od spavaćeg dela, klima uređajem koji je uračunat u cenu najma, TV-om, WiFi-jem (slabog protoka), kupatilom. Deo smeštajnih jedinica gleda na dvorište, dok deo gleda na ulicu i svi poseduju terase zaštićene tendama. Vila poseduje peškire. Promena poseteljine i peškira , kao i čišćenje je na dan smene. Gosti sami održavaju higijenu apartmana. U dvorištu vile gosti mogu svakodnevno odmarati i na raspolaganju im je roštilj.");
		
		
		so2 = repo.save(so2);
		//cetvrti objekat
		
		SmestajniObjekat so3 = new SmestajniObjekat();
		so3.setKategorija("NULL");
		so3.setLokacija(Creator.createLokacija(2L, "Jerisos"));
		so3.setAgent(1L);
		//so.setRejting(Creator.createRejting(20, 89));
		so3.setTipSmestaja(Creator.createTipSmestaja(1L, "Hotel"));
		so3.setZvezdice(4);
		
		List<SmestajniObjekat.Slike> slike33 = new ArrayList<>();
		
		Slike sl343 = new Slike();
		Slike sl443 = new Slike();

		
		
		sl343.setPutanja("https://www.barcino.travel/images/albums/1516276483-Letovanje_grcka_apartmani_Jerisos_(2).jpg");
		sl343.setValue("1");
		slike33.add(sl343);
		
		sl443.setPutanja("https://www.barcino.travel/images/albums/1516276478-Letovanje_grcka_apartmani_Jerisos_(20).jpg");
		sl44.setValue("2");
		slike33.add(sl443);
		
		
		
		
		
		
		so3.getSlike().addAll(slike33);
		so3.setNaziv("Vila Maria");
		so3.setOpis("Vila Maria se nalazi na samo 80 metara od plaže, od centralnog dela Jerisosa. Smeštajne jedinice se nalaze na prvom i drugom spratu vile. Posedujemo dvokrevetne i dvokrevetne sa pomoćnim krevetom apartmane, čevorokrevetne apartmane i četvorokrevetne duplekse. Svi su opremljeni kuhinjom (mini električni šporet, frižider, posuđe) koja je izdvojena od spavaćeg dela, klima uređajem koji je uračunat u cenu najma, TV-om, WiFi-jem (slabog protoka), kupatilom. Deo smeštajnih jedinica gleda na dvorište, dok deo gleda na ulicu i svi poseduju terase zaštićene tendama. Vila poseduje peškire. Promena poseteljine i peškira , kao i čišćenje je na dan smene. Gosti sami održavaju higijenu apartmana. U dvorištu vile gosti mogu svakodnevno odmarati i na raspolaganju im je roštilj.");
		
		
		so3 = repo.save(so3);
		
		
		
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
		
		/*SmestajniObjekat sj2 = repo.getOneById(3L);
		
		System.out.println("Fetched: " + sj2.getId());
		
		SmestajniObjekat sj3 = repo.deleteById(11L);
		
		System.out.println("Deleted: " + sj3.getId());*/
		
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
		
		/*DodatnaUsluga du3 = repo.deleteById(11L);
		
		System.out.println("Deleted: " + du3.getId());*/
		
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
