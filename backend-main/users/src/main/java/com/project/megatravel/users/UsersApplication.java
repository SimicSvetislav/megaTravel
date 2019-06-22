	package com.project.megatravel.users;

import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.repository.AdminRepository;
import com.project.megatravel.users.repository.KorisnikRepository;
import com.project.megatravel.util.Creator;

@EnableDiscoveryClient
@SpringBootApplication
public class UsersApplication {

	private final static Logger logger = Logger.getLogger(UsersApplication.class.getName());
	
	@SuppressWarnings("static-access")
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
		KorisnikRepository r = new KorisnikRepository();
		AdminRepository a = new AdminRepository();
		
		
		
		Creator cr = new Creator();
	//	JAXBContext context = JAXBContext.newInstance(jaxbContext);
	///	Marshaller marshaller = context.createMarshaller();
		KrajnjiKorisnik kk = cr.createKrajnjiKorisnik("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra","sveta.simic.96@gmail.com","sveta","sveta","sveta","sveta");
		r.save(kk);
	//	r.sa 
		
		/*Administrator aa = cr.createAdmin("$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra", "admin@gmail.com","Beocin","939422934","Adam","Gudinci");
		a.save(aa);
		
		
		System.out.println(r.getByEmail("ppera@gmail.com"));
		System.out.println(r.getByEmail("pera@gmail.com"));

		
		KrajnjiKorisnik pom = r.getByEmail("pera@gmail.com");
		System.out.println(pom.getEmail() + " " +pom.getSifra());*/
	}

}
