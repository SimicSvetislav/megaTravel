package tests;


import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.events.LastMinuteEvent;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.rbm.ExistDB;
import com.project.megatravel.util.Creator;

public class LastMinuteEventTest {

	KieSession kSession;
	
	@BeforeClass
	public static void initDatabase() throws Exception {
		
		try {
			ExistDB.initDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLastMinute() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
        kSession.getAgenda().getAgendaGroup("dogadjaji lm").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l));
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        
        int fired = kSession.fireAllRules();
        
        Boolean lm = (Boolean) kSession.getGlobal("lmEnabled");
        
        assertEquals(0, fired);
        assertEquals(true, lm);
	}
	
	@Test
	public void testLastMinute3() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
        kSession.getAgenda().getAgendaGroup("dogadjaji lm").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l));
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
		
        kSession.insert(new LastMinuteEvent(l2));
        
        int fired = kSession.fireAllRules();
        
        Boolean lm = (Boolean) kSession.getGlobal("lmEnabled");
        
        // Zabrana last-minute
        assertEquals(1, fired);
        assertEquals(false, lm);
	}
	
	@Test
	public void testLastMinute2() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
		kSession.getAgenda().getAgendaGroup("dogadjaji lm").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l));
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        
        clock.advanceTime(1, TimeUnit.HOURS);
        
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        
        int fired = kSession.fireAllRules();
        
        Boolean lm = (Boolean) kSession.getGlobal("lmEnabled");
        
        assertEquals(0, fired);
        assertEquals(true, lm);
	}
	
	@Test
	public void testLastMinute4() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        
        kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
        kSession.setGlobal("lmEnabled", true);
        kSession.setGlobal("ebEnabled", true);
		
		kSession.getAgenda().getAgendaGroup("dogadjaji lm").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(1, "Beograd");
        
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l));
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        
        clock.advanceTime(20, TimeUnit.MINUTES);
        
        kSession.insert(new LastMinuteEvent(l2));
        kSession.insert(new LastMinuteEvent(l2));
        
        int fired = kSession.fireAllRules();
        
        Boolean lm = (Boolean) kSession.getGlobal("lmEnabled");
        
        assertEquals(1, fired);
        assertEquals(false, lm);
		
	}
	
}
