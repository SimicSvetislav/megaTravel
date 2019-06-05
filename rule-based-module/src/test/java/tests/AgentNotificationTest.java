package tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.events.ReservationEvent;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.util.Creator;

public class AgentNotificationTest {
	
	private static SimpleDateFormat sdf;
	private KieSession kSession;
	
	@BeforeClass
	public static void before() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	@Test
	public void testAgentNotification() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    kSession =  kContainer.newKieSession("ksession-rules");
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
	
	    kSession.getAgenda().getAgendaGroup("dogadjaji a1").setFocus();
		
	    Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
	    
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        Date d6 = null;
        Date d7 = null;
        Date d8 = null;
        Date d9 = null;
        Date d10 = null;
		try {
			d1 = sdf.parse("06/01/2019");
			d2 = sdf.parse("10/01/2019");
			
			d3 = sdf.parse("09/01/2019");
			d4 = sdf.parse("13/01/2019");
			
			d5 = sdf.parse("08/01/2019");
			d6 = sdf.parse("13/01/2019");
			
			d7 = sdf.parse("09/01/2019");
			d8 = sdf.parse("13/01/2019");
			
			d9 = sdf.parse("08/01/2019");
			d10 = sdf.parse("13/01/2019");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		for (int i = 0; i < 4; i++) {
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d1, d2)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d3, d4)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d5, d6)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d7, d8)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d9, d10)));
		}	
        
	    int fired = kSession.fireAllRules();
        
        Boolean an = (Boolean) kSession.getGlobal("agentNot");
        
        assertEquals(1, fired);
        assertEquals(true, an);
	    
	}
	
	@Test
	public void testAgentNotification1a() {
		
		// Vise od 3 sata
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    
	    KieSession kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
		
	    kSession.getAgenda().getAgendaGroup("dogadjaji a1").setFocus();    
	    
	    //Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
	    
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        Date d6 = null;
        Date d7 = null;
        Date d8 = null;
        Date d9 = null;
        Date d10 = null;
		try {
			d1 = sdf.parse("06/01/2019");
			d2 = sdf.parse("10/01/2019");
			
			d3 = sdf.parse("09/01/2019");
			d4 = sdf.parse("13/01/2019");
			
			d5 = sdf.parse("08/01/2019");
			d6 = sdf.parse("13/01/2019");
			
			d7 = sdf.parse("09/01/2019");
			d8 = sdf.parse("13/01/2019");
			
			d9 = sdf.parse("08/01/2019");
			d10 = sdf.parse("13/01/2019");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		for (int i = 0; i < 4; i++) {
			clock.advanceTime(30, TimeUnit.MINUTES);
			kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d1, d2)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d3, d4)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d5, d6)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d7, d8)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d9, d10)));
		
		}	
	    
	    int fired = kSession.fireAllRules();
        
        Boolean an = (Boolean) kSession.getGlobal("agentNot");
        
        // Proslo 2 sata
        assertEquals(1, fired);
        assertEquals(true, an);
	    
	}
	
	@Test
	public void testAgentNotification2() {
		
		// Vise od 3 sata
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    
	    KieSession kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
		
	    kSession.getAgenda().getAgendaGroup("dogadjaji a1").setFocus();
	    
	    //Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
	    
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        Date d6 = null;
        Date d7 = null;
        Date d8 = null;
        Date d9 = null;
        Date d10 = null;
		try {
			d1 = sdf.parse("06/01/2019");
			d2 = sdf.parse("10/01/2019");
			
			d3 = sdf.parse("09/01/2019");
			d4 = sdf.parse("13/01/2019");
			
			d5 = sdf.parse("08/01/2019");
			d6 = sdf.parse("13/01/2019");
			
			d7 = sdf.parse("09/01/2019");
			d8 = sdf.parse("13/01/2019");
			
			d9 = sdf.parse("08/01/2019");
			d10 = sdf.parse("13/01/2019");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		for (int i = 0; i < 4; i++) {
			clock.advanceTime(1, TimeUnit.HOURS);
			kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d1, d2)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d3, d4)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d5, d6)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d7, d8)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d9, d10)));
		
		}	
	    
	    int fired = kSession.fireAllRules();
        
        Boolean an = (Boolean) kSession.getGlobal("agentNot");
        
        // Proslo vise od 3 sata
        assertEquals(0, fired);
        assertEquals(false, an);
	    
	}
	
	@Test
	public void testAgentNotification3() {
		
		// Nedovoljno rezervacija
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    KieSession kSession =  kContainer.newKieSession("ksession-rules");
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
		
	    kSession.getAgenda().getAgendaGroup("dogadjaji a1").setFocus();
	    
	    //Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
	    
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        Date d6 = null;
        Date d7 = null;
        Date d8 = null;
        Date d9 = null;
        Date d10 = null;
		try {
			d1 = sdf.parse("06/01/2019");
			d2 = sdf.parse("10/01/2019");
			
			d3 = sdf.parse("09/01/2019");
			d4 = sdf.parse("13/01/2019");
			
			d5 = sdf.parse("08/01/2019");
			d6 = sdf.parse("13/01/2019");
			
			d7 = sdf.parse("09/01/2019");
			d8 = sdf.parse("13/01/2019");
			
			d9 = sdf.parse("08/01/2019");
			d10 = sdf.parse("13/01/2019");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		for (int i = 0; i < 3; i++) {
			kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d1, d2)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d3, d4)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d5, d6)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d7, d8)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d9, d10)));
		
		}
	    
	    int fired = kSession.fireAllRules();
        
        Boolean an = (Boolean) kSession.getGlobal("agentNot");
        
        assertEquals(0, fired);
        assertEquals(false, an);
	    
	}
	
	@Test
	public void testAgentNotification4() {
		
		// Drugi vremenski period
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    KieSession kSession =  kContainer.newKieSession("ksession-rules");
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
		
	    kSession.getAgenda().getAgendaGroup("dogadjaji a1").setFocus();
	    
	    //Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
	    
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        Date d6 = null;
        Date d7 = null;
        Date d8 = null;
        Date d9 = null;
        Date d10 = null;
		try {
			d1 = sdf.parse("06/01/2019");
			d2 = sdf.parse("10/01/2019");
			
			d3 = sdf.parse("09/01/2019");
			d4 = sdf.parse("13/01/2019");
			
			d5 = sdf.parse("08/01/2019");
			d6 = sdf.parse("13/01/2019");
			
			d7 = sdf.parse("09/01/2019");
			d8 = sdf.parse("13/01/2019");
			
			d9 = sdf.parse("08/02/2019");
			d10 = sdf.parse("13/02/2019");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		for (int i = 0; i < 4; i++) {
			kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d1, d2)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d3, d4)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d5, d6)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d7, d8)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d9, d10)));
		
		}
	    
	    int fired = kSession.fireAllRules();
        
        Boolean an = (Boolean) kSession.getGlobal("agentNot");
        
        assertEquals(0, fired);
        assertEquals(false, an);
	    
	}
	
	@Test
	public void testAgentNotification5() {
		
		// Druga lokacija
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    KieSession kSession =  kContainer.newKieSession("ksession-rules");
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
		
	    kSession.getAgenda().getAgendaGroup("dogadjaji a1").setFocus();
	    
	    Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
	    
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        Date d6 = null;
        Date d7 = null;
        Date d8 = null;
        Date d9 = null;
        Date d10 = null;
		try {
			d1 = sdf.parse("06/01/2019");
			d2 = sdf.parse("10/01/2019");
			
			d3 = sdf.parse("09/01/2019");
			d4 = sdf.parse("13/01/2019");
			
			d5 = sdf.parse("08/01/2019");
			d6 = sdf.parse("13/01/2019");
			
			d7 = sdf.parse("09/01/2019");
			d8 = sdf.parse("13/01/2019");
			
			d9 = sdf.parse("08/01/2019");
			d10 = sdf.parse("13/01/2019");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		for (int i = 0; i < 5; i++) {
			kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d1, d2)));
	        kSession.insert(new ReservationEvent(l, Creator.createRezervacija(d3, d4)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d5, d6)));
	        kSession.insert(new ReservationEvent(l, Creator.createRezervacija(d7, d8)));
	        kSession.insert(new ReservationEvent(l2, Creator.createRezervacija(d9, d10)));
		
		}
	    
	    int fired = kSession.fireAllRules();
        
        Boolean an = (Boolean) kSession.getGlobal("agentNot");
        
        assertEquals(0, fired);
        assertEquals(false, an);
	    
	}

}
