package com.project.agentBackend;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xmldb.api.base.Collection;

import com.project.agentBackend.model.users.KrajnjiKorisnik;
import com.project.agentBackend.repository.KorisnikRepository;

@SpringBootApplication
public class AgentBackendApplication {

	private final static Logger logger = Logger.getLogger(AgentBackendApplication.class.getName());
	
	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(AgentBackendApplication.class, args);
		
		logger.info("Agent application successfully started now");
		
		java.util.Collection<KrajnjiKorisnik> sve = new KorisnikRepository().getAll();
		
		System.out.print("ID-evi rezervacija: ");
		for (KrajnjiKorisnik k : sve) {
			System.out.print(k.getId() + " ");
		}
		
		
	}

}
