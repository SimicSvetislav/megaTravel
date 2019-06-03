package tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.events.SearchEvent;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.util.Creator;

public class SearchEventTest {
	
	private KieSession kSession;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	public void searchEventTest() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
		
        kSession.getAgenda().getAgendaGroup("dogadjaji pretraga").setFocus();
		
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        for (int i = 0; i < 15; i++) {
        	SearchEvent se = new SearchEvent();
            se.setLokacija(l);
        	
            try {
            	se.setPocetak(sdf.parse("01/07/2019"));
    			se.setKraj(sdf.parse("10/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            SearchEvent se2 = new SearchEvent();
            se2.setLokacija(l);
            try {
            	se2.setPocetak(sdf.parse("05/07/2019"));
    			se2.setKraj(sdf.parse("15/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            kSession.insert(se);
            kSession.insert(se2);
            
        }
        
        // Ponuditi popust, ispunjeni svi uslovi
        int fired = kSession.fireAllRules();
        
        assertEquals(1, fired);
        
	}
	
	@Test
	public void searchEventTest2() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
		
        kSession.getAgenda().getAgendaGroup("dogadjaji pretraga").setFocus();
		
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        for (int i = 0; i < 15; i++) {
        	SearchEvent se = new SearchEvent();
        	se.setLokacija(l);
            try {
            	se.setPocetak(sdf.parse("01/07/2019"));
    			se.setKraj(sdf.parse("10/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            SearchEvent se2 = new SearchEvent();
            se2.setLokacija(l);
            try {
            	se2.setPocetak(sdf.parse("05/07/2019"));
    			se2.setKraj(sdf.parse("15/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
  
            clock.advanceTime(10, TimeUnit.SECONDS);
            
            kSession.insert(se);
            kSession.insert(se2);
            
        }
        
        
        int fired = kSession.fireAllRules();
        
        // Proslo manje od 3 minuta. ispusnjeni svi uslovi
        assertEquals(1, fired);
        
	}
	
	@Test
	public void searchEventTest3() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
		
        kSession.getAgenda().getAgendaGroup("dogadjaji pretraga").setFocus();
		
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        for (int i = 0; i < 15; i++) {
        	SearchEvent se = new SearchEvent();
        	se.setLokacija(l);
            try {
            	se.setPocetak(sdf.parse("01/07/2019"));
    			se.setKraj(sdf.parse("10/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            SearchEvent se2 = new SearchEvent();
            se2.setLokacija(l);
            try {
            	se2.setPocetak(sdf.parse("05/07/2019"));
    			se2.setKraj(sdf.parse("15/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
  
            clock.advanceTime(15, TimeUnit.SECONDS);
            
            kSession.insert(se);
            kSession.insert(se2);
            
        }
        
        
        int fired = kSession.fireAllRules();
        
        // Proslo vise od 3 minuta
        assertEquals(0, fired);
        
	}
	
	@Test
	public void searchEventTest4() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
		
        kSession.getAgenda().getAgendaGroup("dogadjaji pretraga").setFocus();
		
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        for (int i = 0; i < 15; i++) {
        	SearchEvent se = new SearchEvent();
        	se.setLokacija(l);
        	
            try {
            	se.setPocetak(sdf.parse("01/07/2019"));
    			se.setKraj(sdf.parse("10/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            SearchEvent se2 = new SearchEvent();
            se2.setLokacija(l2);
            
            try {
            	se2.setPocetak(sdf.parse("05/07/2019"));
    			se2.setKraj(sdf.parse("15/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
  
            clock.advanceTime(10, TimeUnit.SECONDS);
            
            kSession.insert(se);
            kSession.insert(se2);
            
        }
        
        
        int fired = kSession.fireAllRules();
        
        // Nisu sve pretrage za zeljeno mesto
        assertEquals(0, fired);
        
	}
	
	@Test
	public void searchEventTest5() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
		
        kSession.getAgenda().getAgendaGroup("dogadjaji pretraga").setFocus();
		
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        for (int i = 0; i < 15; i++) {
        	SearchEvent se = new SearchEvent();
        	se.setLokacija(l);
        	
            try {
            	se.setPocetak(sdf.parse("01/07/2019"));
    			se.setKraj(sdf.parse("10/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            SearchEvent se2 = new SearchEvent();
            se2.setLokacija(l);
            
            try {
            	se2.setPocetak(sdf.parse("05/07/2019"));
    			se2.setKraj(sdf.parse("15/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
  
            clock.advanceTime(10, TimeUnit.SECONDS);
            
            kSession.insert(se);
            kSession.insert(se2);
            
        }
        
        RezervacijaKorisnika r = new RezervacijaKorisnika();
        r.setSmestaj(Creator.createSmestajnaJedinica(1L, Creator.createSmestajniObjekat(1L, "SILVER", null, l)));
        try {
			r.setDatumRezervacije(sdf.parse("02/06/2019"));
			r.setDatumPocetka(sdf.parse("20/07/2019"));
			r.setDatumZavrsetka(sdf.parse("22/07/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        kSession.insert(r);
        
        int fired = kSession.fireAllRules();
        
        // Rezervacija u prethodna 4 dana
        assertEquals(0, fired);
        
	}
	
	@Test
	public void searchEventTest6() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
		
        kSession.getAgenda().getAgendaGroup("dogadjaji pretraga").setFocus();
		
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        for (int i = 0; i < 15; i++) {
        	SearchEvent se = new SearchEvent();
        	se.setLokacija(l);
        	
            try {
            	se.setPocetak(sdf.parse("01/07/2019"));
    			se.setKraj(sdf.parse("10/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
            
            SearchEvent se2 = new SearchEvent();
            se2.setLokacija(l2);
            
            try {
            	se2.setPocetak(sdf.parse("05/07/2019"));
    			se2.setKraj(sdf.parse("15/07/2019"));
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
  
            clock.advanceTime(10, TimeUnit.SECONDS);
            
            kSession.insert(se);
            kSession.insert(se2);
            
        }
        
        RezervacijaKorisnika r = new RezervacijaKorisnika();
        r.setSmestaj(Creator.createSmestajnaJedinica(1L, Creator.createSmestajniObjekat(1L, "SILVER", null, l)));
        try {
			r.setDatumRezervacije(sdf.parse("02/05/2019"));
			r.setDatumPocetka(sdf.parse("20/07/2019"));
			r.setDatumZavrsetka(sdf.parse("22/07/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        kSession.insert(r);
        
        int fired = kSession.fireAllRules();
        
        // Druga lokacija
        assertEquals(0, fired);
        
	}

}
