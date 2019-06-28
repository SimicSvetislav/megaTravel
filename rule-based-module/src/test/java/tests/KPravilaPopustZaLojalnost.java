package tests;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.ExistDB;
import com.project.megatravel.util.Creator;

public class KPravilaPopustZaLojalnost {

	private KieSession kSession;
	private Integer counter = 0;
	
	@BeforeClass
	public static void initDatabase() throws Exception {
		
		try {
			ExistDB.initDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Before
    public void prepare(){
        
        InputStream template = KPravilaZabranaPorukaTest.class.getResourceAsStream("/templates/lojalnost.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"3", "10", "1", (counter++).toString()},
            new String[]{"5", "25", "1", (counter++).toString()}
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        
        kSession = createKieSessionFromDRL(drl);
		
		/*KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
	    kSession =  kContainer.newKieSession("ksession-rules");
	   
		*/
	    kSession.getAgenda().getAgendaGroup("lojalnost_1" ).setFocus();
	    
	    kSession.setGlobal("lojalni", new HashSet<>());
        
    }
	
	@Test
	public void lojalnostTest() {
		
		KrajnjiKorisnik k1 = Creator.createKrajnjiKorisnik(1, "BRONZE", "01/01/2019");
		KrajnjiKorisnik k2 = Creator.createKrajnjiKorisnik(1, "SILVER", "01/01/2019");
		
		Agent a = new Agent();
		a.setId(1L);
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setAgent(a);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(11, so);
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setSmestaj(sj);
		rez.setKorisnik(k1);
		rez.setPopust(0.0);
		
		RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
		rez2.setSmestaj(sj);
		rez2.setKorisnik(k1);
		rez2.setPopust(0.0);
		
		RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
		rez3.setSmestaj(sj);
		rez3.setKorisnik(k1);
		rez3.setPopust(0.0);
		
		kSession.insert(rez);
		kSession.insert(rez2);
		kSession.insert(rez3);
		
		int fired = kSession.fireAllRules();
        
		@SuppressWarnings("unchecked")
		Set<KrajnjiKorisnik> lojalni = (Set<KrajnjiKorisnik>)kSession.getGlobal("lojalni");
		
        assertEquals(3, fired);
        assertEquals(10.0, rez.getPopust(), 0.0001);
        assertEquals(1, lojalni.size());
	}
	
	@Test
	public void lojalnostTest2() {
		
		KrajnjiKorisnik k1 = Creator.createKrajnjiKorisnik(1, "BRONZE", "01/01/2019");
		KrajnjiKorisnik k2 = Creator.createKrajnjiKorisnik(1, "SILVER", "01/01/2019");
		
		Agent a = new Agent();
		a.setId(1L);
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setAgent(a);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(11, so);
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setSmestaj(sj);
		rez.setKorisnik(k1);
		rez.setPopust(0.0);
		
		RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
		rez2.setSmestaj(sj);
		rez2.setKorisnik(k1);
		rez2.setPopust(0.0);
		
		RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
		rez3.setSmestaj(sj);
		rez3.setKorisnik(k2);
		rez3.setPopust(0.0);
		
		kSession.insert(rez);
		kSession.insert(rez2);
		kSession.insert(rez3);
		
		int fired = kSession.fireAllRules();
        
		@SuppressWarnings("unchecked")
		Set<KrajnjiKorisnik> lojalni = (Set<KrajnjiKorisnik>)kSession.getGlobal("lojalni");
		
        assertEquals(0, fired);
        assertEquals(0.0, rez.getPopust(), 0.0001);
        assertEquals(0, lojalni.size());
	}
	
	@Test
	public void lojalnostTest3() {
		
		KrajnjiKorisnik k1 = Creator.createKrajnjiKorisnik(1, "BRONZE", "01/01/2019");
		KrajnjiKorisnik k2 = Creator.createKrajnjiKorisnik(1, "SILVER", "01/01/2019");
		
		Agent a = new Agent();
		a.setId(1L);
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setAgent(a);
		
		SmestajniObjekat so2 = new SmestajniObjekat();
		so2.setAgent(a);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(11, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(12, so2);
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setSmestaj(sj);
		rez.setKorisnik(k1);
		rez.setPopust(0.0);
		
		RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
		rez2.setSmestaj(sj2);
		rez2.setKorisnik(k1);
		rez2.setPopust(0.0);
		
		RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
		rez3.setSmestaj(sj);
		rez3.setKorisnik(k1);
		rez3.setPopust(0.0);
		
		RezervacijaKorisnika rez4 = new RezervacijaKorisnika();
		rez4.setSmestaj(sj2);
		rez4.setKorisnik(k2);
		rez4.setPopust(0.0);
		
		RezervacijaKorisnika rez5 = new RezervacijaKorisnika();
		rez5.setSmestaj(sj);
		rez5.setKorisnik(k2);
		rez5.setPopust(0.0);
		
		RezervacijaKorisnika rez6 = new RezervacijaKorisnika();
		rez6.setSmestaj(sj2);
		rez6.setKorisnik(k2);
		rez6.setPopust(0.0);
		
		kSession.insert(rez);
		kSession.insert(rez2);
		kSession.insert(rez3);
		kSession.insert(rez4);
		kSession.insert(rez5);
		kSession.insert(rez6);
		
		int fired = kSession.fireAllRules();
        
		@SuppressWarnings("unchecked")
		Set<KrajnjiKorisnik> lojalni = (Set<KrajnjiKorisnik>)kSession.getGlobal("lojalni");
		
        assertEquals(6, fired);
        assertEquals(10.0, rez.getPopust(), 0.0001);
        assertEquals(10.0, rez6.getPopust(), 0.0001);
        assertEquals(2, lojalni.size());
	}
	
	@Test
	public void lojalnostTest4() {
		
		// Veci popust
		
		KrajnjiKorisnik k1 = Creator.createKrajnjiKorisnik(1, "BRONZE", "01/01/2019");
		KrajnjiKorisnik k2 = Creator.createKrajnjiKorisnik(1, "SILVER", "01/01/2019");
		
		Agent a = new Agent();
		a.setId(1L);
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setAgent(a);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(11, so);
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setSmestaj(sj);
		rez.setKorisnik(k1);
		rez.setPopust(0.0);
		
		RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
		rez2.setSmestaj(sj);
		rez2.setKorisnik(k1);
		rez2.setPopust(0.0);
		
		RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
		rez3.setSmestaj(sj);
		rez3.setKorisnik(k1);
		rez3.setPopust(0.0);
		
		RezervacijaKorisnika rez4 = new RezervacijaKorisnika();
		rez4.setSmestaj(sj);
		rez4.setKorisnik(k1);
		rez4.setPopust(0.0);
		
		RezervacijaKorisnika rez5 = new RezervacijaKorisnika();
		rez5.setSmestaj(sj);
		rez5.setKorisnik(k1);
		rez5.setPopust(0.0);
		
		kSession.insert(rez);
		kSession.insert(rez2);
		kSession.insert(rez3);

		kSession.insert(rez4);
		kSession.insert(rez5);
		
		int fired = kSession.fireAllRules();
        
		@SuppressWarnings("unchecked")
		Set<KrajnjiKorisnik> lojalni = (Set<KrajnjiKorisnik>)kSession.getGlobal("lojalni");
		
        assertEquals(5, fired);
        assertEquals(25.0, rez.getPopust(), 0.0001);
        assertEquals(1, lojalni.size());
	}
	
	@Test
	public void lojalnostTest5() {
		
		// Razliciti agenti, nema popusta
		
		KrajnjiKorisnik k1 = Creator.createKrajnjiKorisnik(1, "BRONZE", "01/01/2019");
		KrajnjiKorisnik k2 = Creator.createKrajnjiKorisnik(1, "SILVER", "01/01/2019");
		
		Agent a = new Agent();
		a.setId(1L);
		Agent a2 = new Agent();
		a2.setId(2L);
		
		SmestajniObjekat so = new SmestajniObjekat();
		so.setAgent(a);
		SmestajniObjekat so2 = new SmestajniObjekat();
		so2.setAgent(a2);
		
		SmestajnaJedinica sj = Creator.createSmestajnaJedinica(11, so);
		SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(12, so2);
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setSmestaj(sj2);
		rez.setKorisnik(k1);
		rez.setPopust(0.0);
		
		RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
		rez2.setSmestaj(sj2);
		rez2.setKorisnik(k1);
		rez2.setPopust(0.0);
		
		RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
		rez3.setSmestaj(sj2);
		rez3.setKorisnik(k2);
		rez3.setPopust(0.0);
		
		RezervacijaKorisnika rez4 = new RezervacijaKorisnika();
		rez4.setSmestaj(sj);
		rez4.setKorisnik(k1);
		rez4.setPopust(0.0);
		
		RezervacijaKorisnika rez5 = new RezervacijaKorisnika();
		rez5.setSmestaj(sj);
		rez5.setKorisnik(k1);
		rez5.setPopust(0.0);
		
		kSession.insert(rez);
		kSession.insert(rez2);
		kSession.insert(rez3);

		kSession.insert(rez4);
		kSession.insert(rez5);
		
		int fired = kSession.fireAllRules();
        
		@SuppressWarnings("unchecked")
		Set<KrajnjiKorisnik> lojalni = (Set<KrajnjiKorisnik>)kSession.getGlobal("lojalni");
		
        assertEquals(0, fired);
        assertEquals(0.0, rez.getPopust(), 0.0001);
        assertEquals(0, lojalni.size());
	}
	
    private KieSession createKieSessionFromDRL(String drl){
    	
	    KieHelper kieHelper = new KieHelper();
	    kieHelper.addContent(drl, ResourceType.DRL);
	    
	    Results results = kieHelper.verify();
	    
	    if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
	        List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
	        for (Message message : messages) {
	            System.out.println("Error: "+message.getText());
	        }
	        
	        throw new IllegalStateException("Compilation errors were found. Check the logs.");
	    }
	    
	    return kieHelper.build().newKieSession();
	}
}
