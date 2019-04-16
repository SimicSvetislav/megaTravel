package faza1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.Kupon;
import com.project.megatravel.util.Creator;

class KlijentiTest {

	KieSession kSession;
	
	@BeforeEach
	void setUp() throws Exception {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("klijent").setFocus();
        
        kSession.setGlobal("kuponi", new ArrayList<>());
		
	}

	@Test	
	void testClients() {
		
		// REZERVACIJE
		
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
        
		// END : KLIJENTI
		
        kSession.insert(k);
        kSession.insert(k2);
        kSession.insert(k3);
        kSession.insert(k4);
        kSession.insert(k5);
        kSession.insert(k6);
        kSession.insert(k7);
        kSession.insert(k8);
        kSession.insert(k9);
        kSession.insert(k10);
        kSession.insert(k11);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("unchecked")
		List<Kupon> kuponi = (List<Kupon>)kSession.getGlobal("kuponi");
        
        // Ukupna cena rezervacija prelazi 300
        assertEquals("SILVER", k.getKategorija());
        
        // Ukupna cena rezervacija ne prelazi 300
        assertEquals("BRONZE", k2.getKategorija());
        
        // Ukupna cena rezervacija ne prelazi 300
        assertEquals("BRONZE", k3.getKategorija());
        
        // Ukupna cena rezervacija prelazi 300 i registrovan vise od 6 meseci
        assertEquals("SILVER", k4.getKategorija());
        
        // Nema dve rezervacije i nije regostrovan 6 meseci
        assertEquals("BRONZE", k5.getKategorija());
        
        // 4 rezervacije cija cena prelazi 300, nije registrovan 6 meseci
        assertEquals("SILVER", k6.getKategorija());
        
        // Ispunjeni uslovi za GOLD
        assertEquals("GOLD", k7.getKategorija());
        
        // Ima 10 rezervacija, ali nije boravio u GOLD ili PLATINUM smestaju
        assertEquals("SILVER", k8.getKategorija());
        
        // Malo mu fali da cena rezervacija u poslednjih 12 meseci prebaci 2000 i udje u PLATINUM
        assertEquals("SILVER", k9.getKategorija());
        
        // Rezervacije su suvise stare da bi usao u PLATINUM
        assertEquals("SILVER", k10.getKategorija());
        
        // Ispunjava uslove za PLATINUM
        assertEquals("PLATINUM", k11.getKategorija());
        
        // Jedanaest klasifikovanja korisnika i jedan halt
        assertEquals(12, fired);
        assertEquals(2, kuponi.size());
        
	}

}
