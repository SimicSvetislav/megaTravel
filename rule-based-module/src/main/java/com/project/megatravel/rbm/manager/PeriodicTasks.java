package com.project.megatravel.rbm.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PeriodicTasks {
	
	@Autowired
	private KieContainer kc;
	
	@Scheduled(fixedDelay = 6000000, initialDelay = 6000000)
	public void rebuildKjarDeamon() {
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
	
	@Scheduled(fixedDelay = 180000, initialDelay = 180000)
	public void periodicRefresh() {
		Manager.kieSession = kc.newKieSession("ksession-rules");
		Manager.populate();
	}
	
	@Scheduled(fixedDelay = 6000000, initialDelay = 6000000)
	public void agentNotifications() {
		//Map<Long, KieSession> agentSessions = Manager.agentSessions;
		
		KieSession ks = Manager.getSession(kc);
		
		ks.getAgenda().getAgendaGroup("agent").setFocus();
		
		ks.setGlobal("lista", new ArrayList<>());
        ks.setGlobal("lista2", new ArrayList<>());
		
		ks.fireAllRules();
		
		// Lista za preporuke za popust
		List lista = (List)ks.getGlobal("lista");
		// Lista za preporuke za upozorenja
		List lista2 = (List)ks.getGlobal("lista2");
		
	}

}
