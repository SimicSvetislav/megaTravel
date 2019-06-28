package com.project.megatravel.rbm.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.rbm.Word;
import com.project.megatravel.rbm.manager.Manager;

@RestController
@CrossOrigin
public class AgentsController {
	
	@Autowired
	KieContainer kieC;
	
	@RequestMapping( value = "/addRuleF/{id}", method = RequestMethod.POST, consumes = "application/json")
	public String addFilterRule(@RequestBody Word word, @PathVariable("id") String id) {		
		//ObjectMapper mapper = new ObjectMapper();
		
		InputStream template = AgentsController.class.getResourceAsStream("/templates/filter.drt");
        
		DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{word.getWord(), id, (++Manager.counter).toString()},
        });
		
		DataProviderCompiler converter = new DataProviderCompiler();
        String ruleText = converter.compile(dataProvider, template);
        
        System.out.println(ruleText);
		
        try {
            File file = new File("C:\\Users\\Sveta\\Desktop\\novo\\megaTravel\\rule-based-module-kjar\\src\\main\\resources\\rules\\filter_" + id + "_" + (Manager.counter) + ".drl");
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ruleText);
            bw.close();
            
            Manager.rebuildKjar();
            
            return "Rule added";
        }
        catch(Exception e) {
             System.out.println(e);
             return "Error occured";
        }
	
    }
	@RequestMapping( value = "/addRuleP1/{id}/{persons}", method = RequestMethod.POST, consumes = "application/json")
	public String addExtraPersons(@RequestBody Double percent, @PathVariable("id") String id, @PathVariable("persons") String persons) {		
		//ObjectMapper mapper = new ObjectMapper();
		
		InputStream template = AgentsController.class.getResourceAsStream("/templates/vise_osoba.drt");
        
		DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{1+"", percent.toString(), id, (++Manager.counter).toString()},
        });
		
		DataProviderCompiler converter = new DataProviderCompiler();
        String ruleText = converter.compile(dataProvider, template);
        
        System.out.println(ruleText);
		
        try {
            File file = new File("C:\\Users\\Sveta\\Desktop\\novo\\megaTravel\\rule-based-module-kjar\\src\\main\\resources\\rules\\vise_osoba_" + id + "_" + (Manager.counter) + ".drl");
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ruleText);
            bw.close();
            
            Manager.rebuildKjar();
            
            return "Rule added";
        }
        catch(Exception e) {
             System.out.println(e);
             return "Error occured";
        }
	
    }
	
	@RequestMapping( value = "/addRuleP2/{id}/{res}", method = RequestMethod.POST, consumes = "application/json")
	public String addLoyal(@RequestBody Double discount, @PathVariable("id") String id, @PathVariable("res") String res) {		
		//ObjectMapper mapper = new ObjectMapper();
		
		InputStream template = AgentsController.class.getResourceAsStream("/templates/lojalnost.drt");
        
		DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{res, discount.toString(), id, (++Manager.counter).toString()},
        });
		
		DataProviderCompiler converter = new DataProviderCompiler();
        String ruleText = converter.compile(dataProvider, template);
        
        System.out.println(ruleText);
		
        try {
            File file = new File("C:\\Users\\Sveta\\Desktop\\novo\\megaTravel\\rule-based-module-kjar\\src\\main\\resources\\rules\\lojalnost_" + id + "_" + (Manager.counter) + ".drl");
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ruleText);
            bw.close();
            
            Manager.rebuildKjar();
            
            return "Rule added";
        }
        catch(Exception e)
        {
             System.out.println(e);
             return "Error occured";
        }
	
    }
	
	@RequestMapping( value = "/filter/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public String filter(@RequestBody Poruka poruka, @PathVariable("id") Long id) {
		
		String msg = "Message accepted";
		
		KieSession ksession = Manager.getAgentSession(id, kieC);
		
		ksession.getAgenda().getAgendaGroup("filter_" + id).setFocus();
		
		ksession.insert(poruka);
		
		int fired = ksession.fireAllRules();
		
		if (fired > 0) {
			msg = "Message rejected";
		}
		
		FactHandle handle = ksession.getFactHandle(poruka);
		
		ksession.delete(handle);
		
		return msg;
		
	}
	

}
