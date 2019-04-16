package faza1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.util.Creator;

public class PreporukaTest {

	KieSession kSession;
	
	@BeforeEach
	void setUp() throws Exception {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
	}

	@Test	
	void testRecommendationPlatinum() {
	
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("PLATINUM");
        
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(1, lista.size());
        assertEquals(1, fired);

	}
	
	@Test	
	void testRecommendationGold() {
	
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("GOLD");
        
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(2, lista.size());
        assertEquals(1, fired);

	}
	
	@Test	
	void testRecommendationSilver() {
	
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("SILVER");
        
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(3, lista.size());
        assertEquals(1, fired);

	}
	
	
	@Test	
	void testRecommendationBronze() {
	
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("BRONZE");
        
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(4, lista.size());
        assertEquals(1, fired);

	}
	
	@Test	
	void testRecommendationNA() {
	
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(5, lista.size());
        assertEquals(1, fired);

	}
	
}
