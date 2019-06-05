package com.project.megatravel.app;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MegatravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegatravelApplication.class, args);
		
		Logger.ok("Main backend application successfully started");
		
		Logger.info("Connecting to database");
		
		URL url = null;
		try {
			url = new URL("http://localhost:8080/testservice?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			//ExistDB.initDatabase();
			Logger.ok("Connection with database established");
		} catch (Exception e) {
			Logger.error("Can't connect to database");
		}
		
		
	}

}
