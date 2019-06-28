package com.project.megatravel.rbm.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.mchange.v1.util.CollectionUtils;
import com.project.megatravel.events.CancellationEvent;
import com.project.megatravel.events.ReservationEvent;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.rbm.manager.Manager;
import com.project.megatravel.rbm.repository.RezervacijeRepository;
import com.project.megatravel.rbm.repository.SjRepository;
import com.project.megatravel.rbm.repository.SoRepository;

@RestController
@CrossOrigin
public class ReservationsController {
	
	@Autowired 
	RezervacijeRepository repo;
	
	@Autowired
	KieContainer kieC;
	
	@RequestMapping(method = RequestMethod.GET, path = "/cancel/{id}")
	public String cancel(@PathVariable("id") Long id) {
		RezervacijaKorisnika rez = repo.getOneById(id);
		//RezervacijaKorisnika rez2 = repo.getOneById(21L);
		//RezervacijaKorisnika rez3 = repo.getOneById(22L);
		
		KieSession sesija = Manager.getSession(kieC);
		
		sesija.insert(rez);
		//sesija.insert(rez2);
		//sesija.insert(rez3);
		
		FactHandle handle = sesija.getFactHandle(rez);
		
		rez.setStanje("OTKAZIVANJE");
		rez.setProcenatOtkazivanje(-1);
		sesija.update(handle, rez);
		
		sesija.getAgenda().getAgendaGroup("otkazivanje").setFocus();
		
		sesija.fireAllRules();
		
		//rez.setStanje("OTKAZANO");
		
		//repo.save(rez);
		
		/*KieSession ekie = Manager.getEventKieSession(kieC);
		CancellationEvent event = new CancellationEvent();
		event.setRk(rez);
		ekie.insert(event);*/
				
		return "Potrebno je da platite " + rez.getProcenatOtkazivanje() + "% rezervacije";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/make/{id}")
	public String make(@PathVariable("id") Long id) {
		RezervacijaKorisnika rez = repo.getOneById(id);
		
		KieSession sesija = Manager.getSession(kieC);
		
		FactHandle handle = sesija.getFactHandle(rez);
		
		rez.setStanje("REZERVISANO");
		sesija.update(handle, rez);
		
		sesija.fireAllRules();	
				
		return "Potrebno je da platite " + rez.getProcenatOtkazivanje()*100 + "% rezervacije";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/make/{aid}")
	public String make(@PathVariable("aid") Long aid, @RequestBody RezervacijaKorisnika rez) {
		
		KieSession sesija = Manager.getSession(kieC);
		
		rez.setPopust(0.0);
		rez.setStanje("REZERVISANO");
		sesija.insert(rez);
		
		sesija.getAgenda().getAgendaGroup("popusti").setFocus();
		
		sesija.setGlobal("finalizing", true);
		
		sesija.fireAllRules();
		
		SmestajnaJedinica jed = sjRepo.getOneById(aid);
		
		SmestajniObjekat so = soRepo.getOneById(jed.getId());
		
		//String msg = "Treba platiti dodatnih ";
		
		double keeper = rez.getPopust();
		
		rez.setPopust(0.0);
		
		sesija.getAgenda().getAgendaGroup("lojalnost " + so.getAgentE()).setFocus();
		
		sesija.insert(rez);
		
		sesija.fireAllRules();
		
		rez.setPopust(rez.getPopust()+keeper);
		
		/*KieSession ekie = Manager.getEventKieSession(kieC);
		ReservationEvent event = new ReservationEvent();
		event.setRk(rez);
		event.setLokacija(rez.getSmestaj().getSObjekat().getLokacija());
		ekie.insert(event);*/
		
		return "Ostvarili ste pravo na popust od " + rez.getPopust() + "%";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/rec/{id}")
	public String reccomend(@PathVariable("id") Long id) {
		
		String msg = "Reccomended units: ";
		
		KieSession kSession = Manager.getSession(kieC);
		
		kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", id);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
        
        int fired = kSession.fireAllRules();
        
        List lista1 = (List)kSession.getGlobal("listaDU");
        List lista2 = (List)kSession.getGlobal("listaUdaljenost");
        List lista3 = (List)kSession.getGlobal("lista");
		
        List<SmestajniObjekat> l1 = (List<SmestajniObjekat>)lista1;
        List<SmestajniObjekat> l2 = (List<SmestajniObjekat>)lista2;
        List<SmestajniObjekat> l3 = (List<SmestajniObjekat>)lista3;
        
        l1 = (List<SmestajniObjekat>)org.apache.commons.collections4.CollectionUtils.intersection(l1, l2);
		
        l1 = (List<SmestajniObjekat>)org.apache.commons.collections4.CollectionUtils.intersection(l1, l3);
        
        for (SmestajniObjekat so: l1) {
        	List<SmestajnaJedinica> jedinice = so.getSmestajneJedinice();
        	for (SmestajnaJedinica sj: jedinice) {
            	msg += sj.getId() + " "; 
        	}
        }
        
		return msg;
		
	}
	
	@Autowired
	SjRepository sjRepo;
	
	@Autowired
	SoRepository soRepo;
	
	@RequestMapping(method = RequestMethod.POST, path = "/more/{id}")
	public String morePersons(@PathVariable("id") Long aid, @RequestBody RezervacijaKorisnika rez) {
		
		SmestajnaJedinica jed = sjRepo.getOneById(aid);
		
		SmestajniObjekat so = soRepo.getOneById(jed.getId());
		
		String msg = "Treba platiti dodatnih ";
		
		rez.setPopust(0.0);
		rez.setBrojOsoba(jed.getBrojKreveta()+1);
		
		KieSession ksession = Manager.getSession(kieC);
		
		ksession.getAgenda().getAgendaGroup("negativni popust " + so.getAgentE()).setFocus();
		
		ksession.insert(rez);
		
		ksession.fireAllRules();
		
		return msg + Math.abs(rez.getPopust());
		
	}
	
	

}
