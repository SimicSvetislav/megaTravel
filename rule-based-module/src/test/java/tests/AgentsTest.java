package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;

import com.project.megatravel.model.accomodation.Cena;
import com.project.megatravel.model.accomodation.Cenovnik;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.rbm.ExistDB;
import com.project.megatravel.util.Creator;

public class AgentsTest {

	public static KieSession kSession;
	
	@BeforeClass
	public static void initDatabase() throws Exception {
		
		try {
			ExistDB.initDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        	
	}
	
	@Test
	public void produzenjeTest() {
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sjs = Creator.createSmestajnaJedinica(1, so2);		
		
		sj.setRejting(Creator.createRejting(10, 40));
		sjs.setRejting(Creator.createRejting(10, 38));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/08/2019", "20/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "12/08/2019", "16/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REZERVISANO", sj);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
		QueryResults resultsS = kSession.getQueryResults("Slobodni_smestaji", rez);
		QueryResults resultsZ = kSession.getQueryResults("Zauzeti_smestaji", rez);
		
		assertEquals(resultsS.size(), resultsZ.size());
		
	}
	
	@Test
	public void produzenjeTest2() {
		
		// Vise slobodnih preporuci snizenje
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sjs = Creator.createSmestajnaJedinica(1, so2);		
		
		sj.setRejting(Creator.createRejting(10, 40));
		sjs.setRejting(Creator.createRejting(10, 38));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/08/2019", "20/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "22/08/2019", "26/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2018", "10/09/2019", "15/09/2019", "REZERVISANO", sj);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
		QueryResults resultsS = kSession.getQueryResults("Slobodni_smestaji", rez);
		QueryResults resultsZ = kSession.getQueryResults("Zauzeti_smestaji", rez);
		
		assertTrue(resultsS.size() > resultsZ.size());
		
	}
	
	@Test
	public void produzenjeTest3() {
		
		// Vise zauzetih preporuci povecanje
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sjs = Creator.createSmestajnaJedinica(1, so2);		
		
		sj.setRejting(Creator.createRejting(10, 40));
		sjs.setRejting(Creator.createRejting(10, 38));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/08/2019", "20/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "12/08/2019", "19/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "15/08/2019", "25/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2018", "10/09/2019", "15/09/2019", "REZERVISANO", sj);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
		QueryResults resultsS = kSession.getQueryResults("Slobodni_smestaji", rez);
		QueryResults resultsZ = kSession.getQueryResults("Zauzeti_smestaji", rez);
		
		assertTrue(resultsS.size() < resultsZ.size());
		
	}
	
	@Test
	public void produzenjeTest5() {
		
		// Zauzeti samo smestaji u drugom mestu
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l2);
		
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(1, so2);		
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/08/2019", "20/08/2019", "U TOKU", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "15/08/2019", "25/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2018", "10/09/2019", "15/09/2019", "REZERVISANO", sj);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
		QueryResults resultsS = kSession.getQueryResults("Slobodni_smestaji", rez);
		QueryResults resultsZ = kSession.getQueryResults("Zauzeti_smestaji", rez);
		
		assertEquals(resultsS.size(), resultsZ.size());
		
	}
	
	@Test
	public void produzenjeTest6() {
		
		// Sve je zauzeto
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
			
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sjs = Creator.createSmestajnaJedinica(1, so2);		
		
		sj.setRejting(Creator.createRejting(10, 40));
		sjs.setRejting(Creator.createRejting(10, 38));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/08/2019", "20/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "12/08/2019", "19/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "15/08/2019", "25/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2018", "10/08/2019", "15/08/2019", "REZERVISANO", sj);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
		QueryResults resultsS = kSession.getQueryResults("Slobodni_smestaji", rez);
		QueryResults resultsZ = kSession.getQueryResults("Zauzeti_smestaji", rez);
		
		assertTrue(resultsS.size() < resultsZ.size());
		assertEquals(0, resultsS.size());
	}
	
	@Test
	public void produzenjeTest7() {
		
		// Zauzeti smestaji u drugom mestu, slobodni u ciljanom
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l2);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(1, so2);		
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "U TOKU", sj);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/08/2019", "20/08/2019", "U TOKU", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2018", "10/09/2019", "15/09/2019", "REZERVISANO", sj);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
		QueryResults resultsS = kSession.getQueryResults("Slobodni_smestaji", rez);
		QueryResults resultsZ = kSession.getQueryResults("Zauzeti_smestaji", rez);
		
		assertTrue(resultsS.size() > resultsZ.size());
		
	}
	
	@Test
	public void upozorenjeTest() {

		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, 0, "02/03/2019", "10/07/2019", "15/07/2019", "REZERVISANO", sj);

		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        //kSession.insert(rez11);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Primecena veca cena, treba obavestiti korisnika
		// Pravilo i HALT + preporuka za popust
		assertEquals(2, fired);
		
	}
	
	@Test
	public void upozorenjeTest2() {

		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, 0, "02/03/2019", "10/07/2019", "15/07/2019", "U TOKU", sj);
		
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/07/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/07/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/07/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/07/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(100);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez11);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nije primecena veca cena
		// HALT
		assertEquals(1, fired);
		
	}
	
	@Test
	public void upozorenjeTest3() {

		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, 0, "02/03/2019", "02/06/2019", "15/06/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "U TOKU", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez11);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Cena je veca, ali ima rezervacija
		// HALT
		assertEquals(2, fired);
		
	}
	
	@Test
	public void predlogPopustaTest() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "SILVER", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/07/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/07/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/07/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/07/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(100);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nema rezervacija, ali je cena vec dovoljno atraktivna
		// HALT + preporuka za popust
		assertEquals(1, fired);
		
	}
	
	@Test
	public void predlogPopustaTest2() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "SILVER", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		@SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(2, lista.size());
		
		// Nema rezervacija i cena je veca
		// HALT + preporuka za popust + upozorenje
		assertEquals(3, fired);
		
	}
	
	@Test
	public void predlogPopustaTest3() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
		
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nema rezervacija, veca cena, ali nema ni smestaja iste kategorije za poredjenje
		// HALT + veca cena
		assertEquals(2, fired);
		
	}
	
	@Test
	public void predlogPopustaTest4() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "SILVER", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
		
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nema rezervacija i cena je veca
		// HALT + preporuka za popust + upozorenje
		assertEquals(3, fired);
		
	}
	
	@Test
	public void predlogPopustaTest5() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
		
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nema rezervacija, veca cena, ali nema ni smestaja iste kategorije za poredjenje
		// HALT + veca cena
		assertEquals(2, fired);
		
	}
	
	@Test
	public void predlogPopustaTest6() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "SILVER", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "SILVER", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 43));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
		
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nema rezervacija, veca cena, ali nema ni smestaja iste kategorije za poredjenje
		// HALT + veca cena
		assertEquals(3, fired);
		
	}
	
	@Test
	public void predlogPopustaTest7() {
		
		kSession.getAgenda().getAgendaGroup("agent").setFocus();
		
		Lokacija l = Creator.createLokacija(1, "Subotica");
		Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
		
		SmestajniObjekat so = Creator.createSmestajniObjekat(1L, "GOLD", Creator.createRejting(10, 45), l);
		SmestajniObjekat so2 = Creator.createSmestajniObjekat(2L, "GOLD", Creator.createRejting(10, 43), l);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
		SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(3, so2);
		
		sj.setRejting(Creator.createRejting(10, 40));
		sj2.setRejting(Creator.createRejting(10, 38));
		sj3.setRejting(Creator.createRejting(10, 48));
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "02/03/2019", "10/03/2019", "15/03/2019", "U TOKU", sj);
		RezervacijaKorisnika rez11 = Creator.createRezervacija(1, 100, 0, "02/03/2019", "02/04/2019", "15/04/2019", "U TOKU", sj);
      
		RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/06/2019", "10/08/2019", "20/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/06/2019", "12/08/2019", "19/08/2019", "REZERVISANO", sj2);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "01/06/2019", "16/08/2019", "25/08/2019", "REZERVISANO", sj3);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "01/06/2019", "10/09/2019", "15/09/2019", "REZERVISANO", sj2);
        
        rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "SILVER", "01/01/2019"));
        rez2.setKorisnik(Creator.createKrajnjiKorisnik(2L, "SILVER", "01/01/2019"));
        rez3.setKorisnik(Creator.createKrajnjiKorisnik(3L, "SILVER", "01/01/2019"));
        rez4.setKorisnik(Creator.createKrajnjiKorisnik(4L, "SILVER", "01/01/2019"));
        rez5.setKorisnik(Creator.createKrajnjiKorisnik(5L, "SILVER", "01/01/2019"));;
        
        Cena cena = new Cena();
        cena.setIznos(1000);
        
        Cenovnik c = new Cenovnik();
        c.setCena(cena);
        
        sj.setPodrazumevaniCenovnik(c);
        
        Cena cena2 = new Cena();
        cena2.setIznos(100);
        
        Cenovnik c2 = new Cenovnik();
        c2.setCena(cena2);
        
        sj2.setPodrazumevaniCenovnik(c2);
        
        Cena cena3 = new Cena();
        cena3.setIznos(200);
        
        Cenovnik c3 = new Cenovnik();
        c3.setCena(cena3);
        
        sj3.setPodrazumevaniCenovnik(c3);
        
		kSession.setGlobal("lista", new ArrayList<>());
		kSession.setGlobal("lista2", new ArrayList<>());
		
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        kSession.insert(so);
        kSession.insert(so2);
        
        kSession.insert(sj);
        kSession.insert(sj2);
        kSession.insert(sj3);
        
		int fired = kSession.fireAllRules();
		
		// Nema rezervacija, veca cena, ali nema ni smestaja iste kategorije za poredjenje
		// HALT + veca cena
		// U obzir se ne uzimaju svi smestaji zbog ocena
		assertEquals(3, fired);
		
	}
	
}
