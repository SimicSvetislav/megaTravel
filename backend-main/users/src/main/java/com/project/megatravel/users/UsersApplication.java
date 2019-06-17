package com.project.megatravel.users;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.project.megatravel.users.repository.KorisnikRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class UsersApplication {

	private final static Logger logger = Logger.getLogger(UsersApplication.class.getName());
	
	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(UsersApplication.class, args);
		
		logger.info("Users microservice successfully started");
		
		// Test
		//KorisnikRepository r = new KorisnikRepository();
		//System.out.println(r.getByEmail("ppera@gmail.com"));
		//System.out.println(r.getByEmail("pera@gmail.com"));
	}

}
