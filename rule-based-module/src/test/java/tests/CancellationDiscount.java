package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.events.CancellationEvent;
import com.project.megatravel.events.ReservationEvent;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.util.Creator;

public class CancellationDiscount {

	private static SimpleDateFormat sdf;
	private KieSession kSession;
	
	@BeforeClass
	public static void before() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	@Test
	public void testCancellationDiscount() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    kSession =  kContainer.newKieSession("ksession-rules");
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
	    
	    kSession.getAgenda().getAgendaGroup("dogadjaji otk").setFocus();
	    
	    Lokacija l = Creator.createLokacija(1, "Novi Sad");
	    
        Date d = new Date();
        
    	LocalDateTime lDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    
    	lDate.minus(7, ChronoUnit.MINUTES);
    	
	    RezervacijaKorisnika rez =  Creator.createRezervacija(1L, 100.0, 0, "01/01/2019", "01/01/2019", "01/01/2019", "REZERVISANO", null);
	    
	    rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "NA", "01/01/2019"));
	    
	    rez.setDatumRezervacije(java.sql.Timestamp.valueOf(lDate));
	    
	    ReservationEvent re = new ReservationEvent(l, rez);
	    
	    CancellationEvent ce = new CancellationEvent(rez);
	    
	    kSession.insert(re);
	    kSession.insert(ce);
	    
	    int fired = kSession.fireAllRules();
	    
	    Boolean an = (Boolean) kSession.getGlobal("cancelDiscount");
        
        assertEquals(1, fired);
        assertEquals(true, an);
        assertEquals(40, rez.getPopust(), 0.001);
		assertNotNull(rez.getKorisnik().getPonudjenPopustNakonOtkazivanja());
	}
	
	@Test
	public void testCancellationDiscount2() {

		// Otkazivanje nakon vise od 10 minuta
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    
	    kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
	    
	    kSession.getAgenda().getAgendaGroup("dogadjaji otk").setFocus();
	    
	    Lokacija l = Creator.createLokacija(1, "Novi Sad");
	    
        Date d = new Date();
        
    	LocalDateTime lDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    
    	lDate.minus(17, ChronoUnit.MINUTES);
    	
	    RezervacijaKorisnika rez =  Creator.createRezervacija(1L, 100.0, 0, "01/01/2019", "01/01/2019", "01/01/2019", "REZERVISANO", null);
	    
	    rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "NA", "01/01/2019"));
	    
	    rez.setDatumRezervacije(java.sql.Timestamp.valueOf(lDate));
	    
	    ReservationEvent re = new ReservationEvent(l, rez);
	    
	    CancellationEvent ce = new CancellationEvent(rez);
	    
	    kSession.insert(re);
	    
	    clock.advanceTime(17, TimeUnit.MINUTES);
	    
	    kSession.insert(ce);
	    
	    int fired = kSession.fireAllRules();
	    
	    Boolean an = (Boolean) kSession.getGlobal("cancelDiscount");
        
	    // Otkazivanje nakon vise od 10 minuta
        assertEquals(0, fired);
        assertEquals(false, an);
		assertNull(rez.getKorisnik().getPonudjenPopustNakonOtkazivanja());
	}
	
	@Test
	public void testCancellationDiscount3() {
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    kSession = kContainer.newKieSession("cepConfigKsessionPseudoClock");
        
        SessionPseudoClock clock = kSession.getSessionClock();
        
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
	    
	    kSession.getAgenda().getAgendaGroup("dogadjaji otk").setFocus();
	    
	    Lokacija l = Creator.createLokacija(1, "Novi Sad");
	    
        Date d = new Date();
        
    	LocalDateTime lDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    
    	lDate.minus(7, ChronoUnit.MINUTES);
    	
	    RezervacijaKorisnika rez =  Creator.createRezervacija(1L, 100.0, 0, "01/01/2019", "01/01/2019", "01/01/2019", "REZERVISANO", null);
	    
	    rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "NA", "01/01/2019"));
	    
	    rez.setDatumRezervacije(java.sql.Timestamp.valueOf(lDate));
	    
	    ReservationEvent re = new ReservationEvent(l, rez);
	    
	    CancellationEvent ce = new CancellationEvent(rez);
	    
	    kSession.insert(re);
	    clock.advanceTime(7, TimeUnit.MINUTES);
	    kSession.insert(ce);
	    
	    int fired = kSession.fireAllRules();
	    
	    Boolean an = (Boolean) kSession.getGlobal("cancelDiscount");
        
	    // Razlika od 7 minuta izmedju rezervacije i otkazivanja
        assertEquals(1, fired);
        assertEquals(true, an);
        assertEquals(40, rez.getPopust(), 0.001);
		assertNotNull(rez.getKorisnik().getPonudjenPopustNakonOtkazivanja());
	}
	
	@Test
	public void testCancellationDiscount4() {
		
		// Korisniku vec bio ponudjen popust
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

	    KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    kSession =  kContainer.newKieSession("ksession-rules");
	    
	    kSession.setGlobal("lmEnabled", true);
	    kSession.setGlobal("ebEnabled", true);
	    kSession.setGlobal("agentNot", false);
	    kSession.setGlobal("cancelDiscount", false);
	    
	    kSession.getAgenda().getAgendaGroup("dogadjaji otk").setFocus();
	    
	    Lokacija l = Creator.createLokacija(1, "Novi Sad");
	    
        Date d = new Date();
        
    	LocalDateTime lDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    
    	lDate.minus(7, ChronoUnit.MINUTES);
    	
	    RezervacijaKorisnika rez =  Creator.createRezervacija(1L, 100.0, 0, "01/01/2019", "01/01/2019", "01/01/2019", "REZERVISANO", null);
	    
	    rez.setKorisnik(Creator.createKrajnjiKorisnik(1L, "NA", "01/01/2019"));
	    
	    rez.setDatumRezervacije(java.sql.Timestamp.valueOf(lDate));
	    rez.getKorisnik().setPonudjenPopustNakonOtkazivanja(new Date());
	    
	    ReservationEvent re = new ReservationEvent(l, rez);
	    
	    CancellationEvent ce = new CancellationEvent(rez);
	    
	    kSession.insert(re);
	    kSession.insert(ce);
	    
	    int fired = kSession.fireAllRules();
	    
	    Boolean an = (Boolean) kSession.getGlobal("cancelDiscount");
        
        assertEquals(0, fired);
        assertEquals(false, an);
	}
	
}
