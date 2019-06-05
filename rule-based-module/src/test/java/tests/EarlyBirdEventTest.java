package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;

import com.project.megatravel.events.EarlyBirdEvent;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.util.Creator;

public class EarlyBirdEventTest {

	KieSession kSession;
	
	@Test
	public void testEarlyBird() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
        kSession.getAgenda().getAgendaGroup("dogadjaji eb").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        
        int fired = kSession.fireAllRules();
        
        Boolean eb = (Boolean) kSession.getGlobal("ebEnabled");
        
        assertEquals(0, fired);
        assertEquals(true, eb);
	}
	
	@Test
	public void testEarlyBird2() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
        kSession.getAgenda().getAgendaGroup("dogadjaji eb").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        for (int i = 0; i < 3; i++) {
	        kSession.insert(new EarlyBirdEvent(l2));
	        kSession.insert(new EarlyBirdEvent(l));
	        kSession.insert(new EarlyBirdEvent(l2));
	        kSession.insert(new EarlyBirdEvent(l2));
	        kSession.insert(new EarlyBirdEvent(l2));
	        kSession.insert(new EarlyBirdEvent(l));
	        kSession.insert(new EarlyBirdEvent(l2));
        }
        
        int fired = kSession.fireAllRules();
        
        Boolean eb = (Boolean) kSession.getGlobal("ebEnabled");
        
        // Zabrana early bird
        assertEquals(1, fired);
        assertEquals(false, eb);
	}
	
	@Test
	public void testEarlyBird3() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
		kSession.getAgenda().getAgendaGroup("dogadjaji eb").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        
        clock.advanceTime(40, TimeUnit.SECONDS);
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        
        clock.advanceTime(60, TimeUnit.SECONDS);
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        
        int fired = kSession.fireAllRules();
        
        Boolean eb = (Boolean) kSession.getGlobal("ebEnabled");
        
        assertEquals(0, fired);
        assertEquals(true, eb);
	}
	
	@Test
	public void testEarlyBird4() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
		kSession.getAgenda().getAgendaGroup("dogadjaji eb").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        
        clock.advanceTime(30, TimeUnit.SECONDS);
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        
        clock.advanceTime(30, TimeUnit.SECONDS);
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        
        int fired = kSession.fireAllRules();
        
        Boolean eb = (Boolean) kSession.getGlobal("ebEnabled");
        
        // Zabrana
        assertEquals(1, fired);
        assertEquals(false, eb);
		
	}
	@Test
	public void testEarlyBird5() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
		kSession.getAgenda().getAgendaGroup("dogadjaji eb").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        
        clock.advanceTime(30, TimeUnit.SECONDS);
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        
        clock.advanceTime(30, TimeUnit.SECONDS);
        
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l2));
        kSession.insert(new EarlyBirdEvent(l));   
        
        // fali jedan
        int fired = kSession.fireAllRules();
        
        Boolean eb = (Boolean) kSession.getGlobal("ebEnabled");
        
        // Zabrana
        assertEquals(0, fired);
        assertEquals(true, eb);
		
	}
	
}


