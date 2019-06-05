package tests;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;

public class KPravilaViseOsobaTest {
	
	private KieSession ksession;
	
	@Before
    public void prepare(){
        
        InputStream template = KPravilaZabranaPorukaTest.class.getResourceAsStream("/templates/vise_osoba.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"1", "5"},
            new String[]{"2", "10"},
            new String[]{"4", "50"}
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        
        ksession = createKieSessionFromDRL(drl);
        
    }
	
	@Test
	public void doTest(){
        
		// Nema viska
		
		ksession.getAgenda().getAgendaGroup("negativni popust").setFocus();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setBrojOsoba(4);
		rez.setPopust(0.0);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBrojKreveta(4);
		rez.setSmestaj(sj);
		
		ksession.insert(rez);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(0, fired);
        assertEquals(0.0, rez.getPopust(), 0.0001);
        
    }
	
	@Test
	public void doTest2(){
        
		// Jedna osoba viska
		
		ksession.getAgenda().getAgendaGroup("negativni popust").setFocus();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setBrojOsoba(5);
		rez.setPopust(0.0);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBrojKreveta(4);
		rez.setSmestaj(sj);
		
		ksession.insert(rez);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(1, fired);
        assertEquals(-5.0, rez.getPopust(), 0.0001);
        
    }
	
	@Test
	public void doTest3(){
        
		// Dve osobe viska
		
		ksession.getAgenda().getAgendaGroup("negativni popust").setFocus();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setBrojOsoba(5);
		rez.setPopust(0.0);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBrojKreveta(3);
		rez.setSmestaj(sj);
		
		ksession.insert(rez);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(1, fired);
        assertEquals(-10.0, rez.getPopust(), 0.0001);
        
    }
	
	@Test
	public void doTest4(){
        
		// Tri osobe viska
		
		ksession.getAgenda().getAgendaGroup("negativni popust").setFocus();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setBrojOsoba(7);
		rez.setPopust(0.0);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBrojKreveta(4);
		rez.setSmestaj(sj);
		
		ksession.insert(rez);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(1, fired);
        assertEquals(-10.0, rez.getPopust(), 0.0001);
        
    }
	
	@Test
	public void doTest5(){
        
		// Cetiri osobe viska
		
		ksession.getAgenda().getAgendaGroup("negativni popust").setFocus();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setBrojOsoba(8);
		rez.setPopust(0.0);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBrojKreveta(4);
		rez.setSmestaj(sj);
		
		ksession.insert(rez);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(1, fired);
        assertEquals(-50.0, rez.getPopust(), 0.0001);
        
    }
	
	@Test
	public void doTest6(){
        
		// Jedna osoba manje od kapaciteta
		
		ksession.getAgenda().getAgendaGroup("negativni popust").setFocus();
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setBrojOsoba(3);
		rez.setPopust(0.0);
		
		SmestajnaJedinica sj = new SmestajnaJedinica();
		sj.setBrojKreveta(4);
		rez.setSmestaj(sj);
		
		ksession.insert(rez);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(0, fired);
        assertEquals(0.0, rez.getPopust(), 0.0001);
        
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
