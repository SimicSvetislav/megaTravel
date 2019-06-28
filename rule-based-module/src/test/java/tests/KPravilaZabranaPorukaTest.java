package tests;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

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

import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.rbm.ExistDB;

public class KPravilaZabranaPorukaTest {

	private KieSession ksession;
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
        
        InputStream template = KPravilaZabranaPorukaTest.class.getResourceAsStream("/templates/filter.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"bad", "1", (counter++).toString()},
            new String[]{"words", "1", (counter++).toString()},
            new String[]{"gonna", "1", (counter++).toString()},
            new String[]{"hurt", "1", (counter++).toString()},
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        
        ksession = createKieSessionFromDRL(drl);
        
        ksession.getAgenda().getAgendaGroup("filter_1").setFocus();
        
    }
    
	@Test
	public void doTest(){
        
		Poruka poruka = new Poruka();
		poruka.setTekst("This text doesn't contain not even one forbidden word");
	
		ksession.insert(poruka);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(0, fired);
        
    }
	
	@Test
	public void doTest2(){
        
		Poruka poruka = new Poruka();
		poruka.setTekst("This text contains one bad word");
		
		ksession.insert(poruka);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(1, fired);
        
    }
	
	@Test
	public void doTest3(){
        
		Poruka poruka = new Poruka();
		poruka.setTekst("This text contains one bad word");
		Poruka poruka2 = new Poruka();
		poruka2.setTekst("Good text");
		Poruka poruka3 = new Poruka();
		poruka3.setTekst("Gonna do that");
		Poruka poruka4 = new Poruka();
		poruka4.setTekst("This hurts");
	
		ksession.insert(poruka);
		ksession.insert(poruka2);
		ksession.insert(poruka3);
		ksession.insert(poruka4);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(2, fired);
        
    }
	
	@Test
	public void doTest4(){
        
		Poruka poruka = new Poruka();
		poruka.setTekst("This text contains two bad words");
	
		ksession.insert(poruka);
		
        int fired = ksession.fireAllRules();
        
        assertEquals(2, fired);
        
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
