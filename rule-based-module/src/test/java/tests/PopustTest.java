package tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.util.Creator;

public class PopustTest {
	
	@Test
	public void testPopust() {
		
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("popusti").setFocus();
        
        kSession.setGlobal("finalizing", false);
		
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        rez.setPopust(0.0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
			rez.setDatumRezervacije(sdf.parse("24/01/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        rez.setId(1L);
        
        RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
        rez2.setPopust(0.0);
        
        try {
			rez2.setDatumRezervacije(sdf.parse("16/11/2018"));
			rez2.setDatumPocetka(sdf.parse("18/11/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        rez2.setId(2L);
        
        RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
        
        rez3.setDatumRezervacije(new Date());
        rez3.setDatumPocetka(new Date());
        rez3.setPopust(0.0);
        
        rez3.setId(3L);
           
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        int fired = kSession.fireAllRules();
        
        assertEquals(0, rez.getPopust(), 0.001);
        
        // Early bird
        assertEquals(25.0, rez2.getPopust(), 0.001);
        
        // Last minute
        assertEquals(50.0, rez3.getPopust(), 0.001);
        
        // 2 popusta i halt
        assertEquals(3, fired);

	}
	
	@Test
	public void testPopustDodatno() {
		
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("popusti").setFocus();
        
        kSession.setGlobal("finalizing", false);
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        
        Rejting r = Creator.createRejting(2, 9);
        Rejting r0 = Creator.createRejting(0, 0);
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "BRONZE", r, l);
        
        SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "SILVER", r, l);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "GOLD", r, l);
        
        SmestajniObjekat so4 = Creator.createSmestajniObjekat(4, "SILVER", r, l);
        
        SmestajniObjekat so5 = Creator.createSmestajniObjekat(5, "SILVER", r, l);
        
        SmestajniObjekat so6 = Creator.createSmestajniObjekat(6, "BRONZE", r0, l);
        
        SmestajniObjekat so7 = Creator.createSmestajniObjekat(7, "GOLD", r0, l);
        
        Agent a1 = new Agent();
        a1.setId(1L);
        
        Agent a2 = new Agent();
        a2.setId(2L);
        
        Agent a3 = new Agent();
        a3.setId(3L);
        
        so.setAgent(a1);
        so2.setAgent(a2);
        so3.setAgent(a3);
        so4.setAgent(a1);
        so5.setAgent(a2);
        so6.setAgent(a3);
        
        
        SmestajnaJedinica j1 = Creator.createSmestajnaJedinica(1L, so);
        so.getSmestajneJedinice().add(j1);
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2L, so2);
        so2.getSmestajneJedinice().add(j2);
        
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3L, so3);
        so3.getSmestajneJedinice().add(j3);
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4L, so);
        so.getSmestajneJedinice().add(j4);
        
        KrajnjiKorisnik k = Creator.createKrajnjiKorisnik(1, "SILVER", "10/10/2018");
        
        RezervacijaKorisnika rez1 = Creator.createRezervacija(11, 100, 3, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j1);
        rez1.setPopust(0.0);
        rez1.setKorisnik(k);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(12, 100, 2, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j2);
        rez2.setPopust(0.0);
        rez2.setKorisnik(k);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(13, 100, 4, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j3);
        rez3.setPopust(0.0);
        rez3.setKorisnik(k);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(14, 500, 5, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j4);
        rez4.setPopust(0.0);
        rez4.setKorisnik(k);
        
        RezervacijaKorisnika rez5 = Creator.createRezervacija(15, 800, 3, "09/09/2018", "09/04/2019", "09/04/2019", "REALIZOVANO", 
        		j1);
        rez5.setPopust(0.0);
        rez5.setKorisnik(k);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(16, 800, 4, "06/04/2019", "09/04/2019", "19/09/2017", "REALIZOVANO", 
        		j2);
        rez6.setPopust(0.0);
        rez6.setKorisnik(k);
        
        RezervacijaKorisnika rezNew = Creator.createRezervacija(20, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REZERVISANO", 
        		j2);
        rezNew.setPopust(0.0);
        rezNew.setKorisnik(k);
        
        k.getRezervacije().add(rez1);
        k.getRezervacije().add(rez2);
        k.getRezervacije().add(rez3);
        k.getRezervacije().add(rez4);
        k.getRezervacije().add(rez5);
        k.getRezervacije().add(rez6);
        
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        kSession.insert(so6);
        kSession.insert(so7);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        kSession.insert(rezNew);
        
        kSession.insert(l);
        
        int fired = kSession.fireAllRules();
        
        assertEquals(60, rezNew.getPopust(), 0.001);
        
        // Preporuka i halt
        assertEquals(2, fired);
		
	}
	
	@Test
	public void testPopustDodatno2() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("popusti2").setFocus();
        
        kSession.setGlobal("finalizing", false);
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
        Lokacija l3 = Creator.createLokacija(3, "Novi Sad");
        Lokacija l4 = Creator.createLokacija(4, "Novi Sad");
        Lokacija l5 = Creator.createLokacija(5, "Novi Sad");
        Lokacija l6 = Creator.createLokacija(6, "Novi Sad");
        Lokacija l7 = Creator.createLokacija(7, "Novi Sad");
        Lokacija l8 = Creator.createLokacija(8, "Novi Sad");
        
        Rejting r = Creator.createRejting(2, 9);
        Rejting r0 = Creator.createRejting(0, 0);
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "BRONZE", r, l);
        
        SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "SILVER", r, l2);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "GOLD", r, l3);
        
        SmestajniObjekat so4 = Creator.createSmestajniObjekat(4, "SILVER", r, l4);
        
        SmestajniObjekat so5 = Creator.createSmestajniObjekat(5, "SILVER", r, l5);
        
        SmestajniObjekat so6 = Creator.createSmestajniObjekat(6, "BRONZE", r0, l6);
        
        SmestajniObjekat so7 = Creator.createSmestajniObjekat(7, "GOLD", r0, l7);
        
        SmestajniObjekat so8 = Creator.createSmestajniObjekat(8, "GOLD", r0, l8);
        
        Agent a1 = new Agent();
        a1.setId(1L);
        
        Agent a2 = new Agent();
        a2.setId(2L);
        
        Agent a3 = new Agent();
        a3.setId(3L);
        
        so.setAgent(a1);
        so2.setAgent(a2);
        so3.setAgent(a3);
        so4.setAgent(a1);
        so5.setAgent(a2);
        so6.setAgent(a3);
        
        
        SmestajnaJedinica j1 = Creator.createSmestajnaJedinica(1L, so);
        so.getSmestajneJedinice().add(j1);
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2L, so2);
        so2.getSmestajneJedinice().add(j2);
        
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3L, so3);
        so3.getSmestajneJedinice().add(j3);
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4L, so4);
        so4.getSmestajneJedinice().add(j4);
        
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5L, so5);
        so5.getSmestajneJedinice().add(j5);
        
        SmestajnaJedinica j6 = Creator.createSmestajnaJedinica(6L, so6);
        so6.getSmestajneJedinice().add(j6);
        
        SmestajnaJedinica j7 = Creator.createSmestajnaJedinica(7L, so7);
        so7.getSmestajneJedinice().add(j7);
        
        SmestajnaJedinica j8 = Creator.createSmestajnaJedinica(8L, so8);
        so8.getSmestajneJedinice().add(j8);
        
        KrajnjiKorisnik k = Creator.createKrajnjiKorisnik(1, "SILVER", "10/10/2018");
        
        RezervacijaKorisnika rez1 = Creator.createRezervacija(11, 100, 3, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j1);
        rez1.setPopust(0.0);
        rez1.setKorisnik(k);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(12, 100, 2, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j2);
        rez2.setPopust(0.0);
        rez2.setKorisnik(k);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(13, 100, 4, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j3);
        rez3.setPopust(0.0);
        rez3.setKorisnik(k);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(14, 500, 5, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j4);
        rez4.setPopust(0.0);
        rez4.setKorisnik(k);
        
        RezervacijaKorisnika rez5 = Creator.createRezervacija(15, 800, 3, "09/09/2018", "09/04/2019", "09/04/2019", "REALIZOVANO", 
        		j5);
        rez5.setPopust(0.0);
        rez5.setKorisnik(k);
        
        // Nije u poslednjih godinu dana
        RezervacijaKorisnika rez6 = Creator.createRezervacija(16, 800, 4, "06/04/2019", "09/04/2019", "19/09/2017", "REALIZOVANO", 
        		j6);
        rez6.setPopust(0.0);
        rez6.setKorisnik(k);
        
        RezervacijaKorisnika rezNew = Creator.createRezervacija(20, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REALIZOVANO", 
        		j2);
        rezNew.setPopust(0.0);
        rezNew.setKorisnik(k);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(17, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REALIZOVANO", 
        		j7);
        rez7.setPopust(0.0);
        rez7.setKorisnik(k);
        
        RezervacijaKorisnika rez8 = Creator.createRezervacija(18, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REZERVISANO", 
        		j8);
        rez8.setPopust(0.0);
        rez8.setKorisnik(k);
        
        k.getRezervacije().add(rez1);
        k.getRezervacije().add(rez2);
        k.getRezervacije().add(rez3);
        k.getRezervacije().add(rez4);
        k.getRezervacije().add(rez5);
        k.getRezervacije().add(rez6);
        k.getRezervacije().add(rez7);
        k.getRezervacije().add(rez8);
        
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        kSession.insert(so6);
        kSession.insert(so7);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rezNew);
        
        kSession.insert(l);
        
        int fired = kSession.fireAllRules();
        
        assertEquals(80, rez8.getPopust(), 0.001);
        
        // Popust i halt
        assertEquals(2, fired);
	}

}
