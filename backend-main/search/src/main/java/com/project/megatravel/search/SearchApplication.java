package com.project.megatravel.search;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.project.megatravel.search.services.SearchService;

@EnableDiscoveryClient
@SpringBootApplication
public class SearchApplication {
	
	private final static Logger logger = Logger.getLogger(SearchApplication.class.getName());
	
	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(SearchApplication.class, args);
		
		logger.info("Search microservice successfully started");
		
		//SearchService ss = new SearchService();
		//System.out.println(ss.getOcena(1L));
		
	}

}
