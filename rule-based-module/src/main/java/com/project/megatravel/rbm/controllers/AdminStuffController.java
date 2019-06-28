package com.project.megatravel.rbm.controllers;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.manager.Manager;
import com.project.megatravel.rbm.repository.KorisnikRepository;
import com.project.megatravel.rbm.repository.SoRepository;

@RestController
@CrossOrigin
public class AdminStuffController {
	
	@Autowired
	KieContainer kieC;
	
	@Autowired
	SoRepository soRepo;
	
	@Autowired
	KorisnikRepository krepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/catAcc")
	public String catAcc() {
		
		KieSession sesija = Manager.getSession(kieC);
		
		//sesija.getAgenda().getAgendaGroup("smestajInit").setFocus();
		
		//int fired = sesija.fireAllRules();
		
		//System.out.println("fired: " + fired);
		
		QueryResults results = sesija.getQueryResults( "getAcc" ); 
		for ( QueryResultsRow row : results ) {
			SmestajniObjekat so = ( SmestajniObjekat ) row.get( "$result" );
			System.out.println(so.getId() + " : " + so.getKategorija());
			FactHandle handle = sesija.getFactHandle(so);
			so.setKategorija("NA");
			sesija.update(handle, so);
		}
		
		QueryResults results3 = sesija.getQueryResults( "getAcc" ); 
		for ( QueryResultsRow row : results3 ) {
			SmestajniObjekat so = ( SmestajniObjekat ) row.get( "$result" );
			System.out.println(so.getId() + " : " + so.getKategorija());
		}
		
		sesija.getAgenda().getAgendaGroup("smestaj").setFocus();
		
		/*try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		sesija.fireAllRules();
		
		String attach = "";
		
		QueryResults results2 = sesija.getQueryResults( "getAcc" ); 
		for ( QueryResultsRow row : results2 ) {
			SmestajniObjekat so = ( SmestajniObjekat ) row.get( "$result" );
			System.out.println(so.getId() + " : " + so.getKategorija());
			attach += so.getId() + " : " + so.getKategorija() + "\n";
			//soRepo.save(so);
		}
				
		return "Zavrsena kategorizacija smestaja\n" + attach;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/catUsr")
	public String catUsr() {
		
		KieSession sesija = Manager.getSession(kieC);
		
		QueryResults results = sesija.getQueryResults( "getUser" ); 
		for ( QueryResultsRow row : results ) {
			KrajnjiKorisnik kk = ( KrajnjiKorisnik ) row.get( "$result" );
			System.out.println(kk.getId() + " : " + kk.getKategorija());
			FactHandle handle = sesija.getFactHandle(kk);
			kk.setKategorija("NA");
			sesija.update(handle, kk);
		}
		
		QueryResults results2 = sesija.getQueryResults( "getUser" ); 
		for ( QueryResultsRow row : results2 ) {
			KrajnjiKorisnik so = ( KrajnjiKorisnik ) row.get( "$result" );
			System.out.println(so.getId() + " : " + so.getKategorija());
		}
		
		sesija.getAgenda().getAgendaGroup("klijent").setFocus(); 
		
		sesija.fireAllRules();
		
		String attach = "";
		
		QueryResults results3 = sesija.getQueryResults( "getUser" );
		for ( QueryResultsRow row : results3 ) {
			KrajnjiKorisnik so = ( KrajnjiKorisnik ) row.get( "$result" );
			System.out.println(so.getId() + " : " + so.getKategorija());
			attach += so.getId() + " : " + so.getKategorija() + "\n";
			//krepo.save(so);
		}
				
		return "Zavrsena kategorizacija korisnika\n" + attach;
	}
	
}
