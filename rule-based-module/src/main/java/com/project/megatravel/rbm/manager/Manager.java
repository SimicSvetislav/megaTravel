package com.project.megatravel.rbm.manager;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ExtractingResponseErrorHandler;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.repository.AdminRepository;
import com.project.megatravel.rbm.repository.AgentRepository;
import com.project.megatravel.rbm.repository.CategoriesRepository;
import com.project.megatravel.rbm.repository.ExtrasRepository;
import com.project.megatravel.rbm.repository.KorisnikRepository;
import com.project.megatravel.rbm.repository.RezervacijeRepository;
import com.project.megatravel.rbm.repository.SjRepository;
import com.project.megatravel.rbm.repository.SoRepository;
import com.project.megatravel.rbm.repository.TypesRepository;

@Component
public class Manager {

	private Manager() {}
	
	public static ReleaseId releaseId;
	
	public static KieSession kieSession;
	public static KieSession kieEventSession;
	public static Map<Long, KieSession> agentSessions = new HashMap<>(); 
	public static boolean populated = false;
	public static Integer counter = 0;
	
	public static void init() {
		
		// kieSession = kieContainer.newKieSession("ksession-rules");
		
	}


	
	public static void rebuildKjar() {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile( new File( "C:\\Users\\Sveta\\Desktop\\novo\\megaTravel\\rule-based-module-kjar\\pom.xml" ) );
		request.setGoals( Arrays.asList( "clean", "install" ) );

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
		try {
			invoker.execute( request );
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
		
	}

	public static KieSession getSession(KieContainer kieContainer2) {
		
		if (kieSession==null || releaseId!=kieContainer2.getReleaseId()) {
			System.out.println("New session");
			releaseId = kieContainer2.getReleaseId();
			kieSession = kieContainer2.newKieSession("ksession-rules");
			populated = false;
		}
		
		if (!populated) {
			populate();
			populated = true;
		}
		
		return kieSession;
	}
	
	public static KieSession getEventKieSession(KieContainer kieContainer2) {
		
		if (kieEventSession==null) {
			System.out.println("New event session");
			kieEventSession = kieContainer2.newKieSession("cepConfigKsessionRealtimeClock");
			kieEventSession.fireUntilHalt();
		}
		
		return kieEventSession;
	}
	
	public static void populate() {
		
		if (kieSession==null) {
			return;
		}
		
		SoRepository soRepo = new SoRepository();
		
		List<SmestajniObjekat> soList = (List<SmestajniObjekat>)soRepo.getAll();
		
		for (SmestajniObjekat so: soList) {
			kieSession.insert(so);
		}
		
		SjRepository sjRepo = new SjRepository();
		
		List<SmestajnaJedinica> sjList = (List<SmestajnaJedinica>)sjRepo.getAll();
		
		for (SmestajnaJedinica sj: sjList) {
			kieSession.insert(sj);
		}
		
		RezervacijeRepository rRepo = new RezervacijeRepository();
			
		List<RezervacijaKorisnika> rezList = (List<RezervacijaKorisnika>)rRepo.getAll();
		
		for (RezervacijaKorisnika r: rezList) {
			kieSession.insert(r);
		}
		
		KorisnikRepository kRepo = new KorisnikRepository();
		
		List<KrajnjiKorisnik> kkList = (List<KrajnjiKorisnik>)kRepo.getAll();
		
		for (KrajnjiKorisnik kk: kkList) {
			kieSession.insert(kk);
		}
		
		ExtrasRepository eRepo = new ExtrasRepository();
		
		List<DodatnaUsluga> dList = (List<DodatnaUsluga>)eRepo.getAll();
		
		for (DodatnaUsluga d: dList) {
			kieSession.insert(d);
		}
		
		AgentRepository aRepo = new AgentRepository();
		
		List<Agent> aList = (List<Agent>)aRepo.getAll();
		
		for (Agent a: aList) {
			kieSession.insert(a);
		}		
		
	}

	public static KieSession getAgentSession(Long id, KieContainer k) {
		
		KieSession sesija = agentSessions.get(id);
		if (sesija==null) {
			System.out.println("New agent session " + id);
			releaseId = k.getReleaseId();
			sesija = k.newKieSession("ksession-rules");
			agentSessions.put(id, sesija);
		}
		
		
		return sesija;
	}
	
}
