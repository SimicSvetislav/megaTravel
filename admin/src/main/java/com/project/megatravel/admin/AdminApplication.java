package com.project.megatravel.admin;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApplication {

	private final static Logger logger = Logger.getLogger(AdminApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		
		logger.info("Admin application successfully started");
	}

}
