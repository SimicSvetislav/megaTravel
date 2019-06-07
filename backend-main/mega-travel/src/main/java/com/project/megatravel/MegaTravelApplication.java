package com.project.megatravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
