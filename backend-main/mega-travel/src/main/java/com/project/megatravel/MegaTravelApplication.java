package com.project.megatravel;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class MegaTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaTravelApplication.class, args);
		
		/*Logger.ok("Main backend application successfully started");
		
		Logger.info("Connecting to database");
		
		/*URL url = null;
		try {
			url = new URL("http://localhost:8080/testservice?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}*/
		
		/*try {
			//ExistDB.initDatabase();
			Logger.ok("Connection with database established");
		} catch (Exception e) {
			Logger.error("Can't connect to database");
		}*/
		
		
	}
}
