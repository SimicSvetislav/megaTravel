package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.util.Creator;

public class OtkazivanjeTest {
	
	@Test
	public void testOtkazivanjeOsnovno() {
		
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
                
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j = Creator.createSmestajnaJedinica(1, so, Creator.createRejting(2, 9));
        
        so.getSmestajneJedinice().add(j);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "04/06/2019", "19/06/2019", "REZERVISANO", j);
        rez.setProcenatOtkazivanje(-1.0);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "14/06/2019", "27/06/2019", "REZERVISANO", j);
        rez2.setProcenatOtkazivanje(-1.0);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2017", "20/07/2019", "25/07/2019", "REZERVISANO", j);
        rez3.setProcenatOtkazivanje(-1.0);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "22/06/2019", "30/06/2019", "REZERVISANO", j);
        rez4.setProcenatOtkazivanje(-1.0);    
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        int fired = kSession.fireAllRules();
        
        // Manje od 3 dana
        assertEquals(100.0, rez.getProcenatOtkazivanje(), 0.001);
        
        // 15 do 3 dana pre pocetka rezervacije
        assertEquals(50.0, rez2.getProcenatOtkazivanje(), 0.001);
        
        // Vise od 30 dana pre pocetka rezervacije
        assertEquals(0.0, rez3.getProcenatOtkazivanje(), 0.001);
        
        // 30 do 15 dana pre pocetka rezervacije
        assertEquals(30.0, rez4.getProcenatOtkazivanje(), 0.001);
        
        // Cetiri procene otkazivanja i halt
        assertEquals(5, fired);
	
	}
	
	@Test
	public void testOtkazivanjeUgnjezdena() {
		
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
		
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j = Creator.createSmestajnaJedinica(1, so, Creator.createRejting(2, 9));
        
        so.getSmestajneJedinice().add(j);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(2, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(2, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j);
        
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2, so2, Creator.createRejting(2, 9));
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3, so2, Creator.createRejting(2, 9));
        
        so2.getSmestajneJedinice().add(j2);
        so2.getSmestajneJedinice().add(j3);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j2);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j2);
        RezervacijaKorisnika rez8 = Creator.createRezervacija(8, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j3);
        RezervacijaKorisnika rez9 = Creator.createRezervacija(9, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j3);
       
        
        kSession.insert(rez6);
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rez9);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4, so3, Creator.createRejting(2, 9));
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5, so3, Creator.createRejting(2, 9));
        
        so3.getSmestajneJedinice().add(j4);
        so3.getSmestajneJedinice().add(j5);
        
        RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, 0, "04/04/2019", "10/08/2019", "15/08/2019", "REZERVISANO", j4);
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j5);
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j4);
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j4);
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j5);
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 100, 0, "04/01/2014", "10/01/2014", "15/01/2014", "REALIZOVANO", j5);
        RezervacijaKorisnika rez17 = Creator.createRezervacija(17, 100, 0, "04/01/2011", "10/01/2011", "15/01/2011", "REALIZOVANO", j4);
        
        
        kSession.insert(rez11);
        kSession.insert(rez12);
        kSession.insert(rez13);
        kSession.insert(rez14);
        kSession.insert(rez15);
        kSession.insert(rez16);
        kSession.insert(rez17);
        
        int fired = kSession.fireAllRules();
        
        // Tri godine uspesnog poslovanja
        assertEquals(75.0, rez.getProcenatOtkazivanje(), 0.001);
        
        // Dve godine uspesnog poslovanja
        assertEquals(50.0, rez6.getProcenatOtkazivanje(), 0.001);
        
        // Pet godina uspesnog poslovanja
        assertEquals(100.0, rez11.getProcenatOtkazivanje(), 0.001);
        
        // Tri procene otkazivanja i halt
        assertEquals(4, fired);
	}
	
	@Test
	public void testOtkazivanjeGodinePoslovanja() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j = Creator.createSmestajnaJedinica(1, so, Creator.createRejting(2, 9));
        
        so.getSmestajneJedinice().add(j);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "01/01/2019", "05/08/2019", "10/08/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "01/08/2019", "04/08/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2019", "11/08/2019", "15/08/2019", "REALIZOVANO", j);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "05/08/2019", "15/06/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2019", "01/08/2019", "04/06/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 100, 0, "04/01/2019", "17/08/2019", "21/06/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 0, "04/01/2019", "10/08/2019", "15/01/2019", "REALIZOVANO", j);
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        kSession.insert(rez7);
        
        int fired = kSession.fireAllRules();
        
        // Ugnjezdena rezervacija
        assertEquals(55.0, rez.getProcenatOtkazivanje(), 0.001);
        
        // Skoro ugnjezdena rezervacija, placanje na osnovu jedne godine uspesnog poslovanja
        assertEquals(25.0, rez4.getProcenatOtkazivanje(), 0.001);
        
        
        // Dve procene otkazivanja i halt
        assertEquals(3, fired);
		
	}

}
