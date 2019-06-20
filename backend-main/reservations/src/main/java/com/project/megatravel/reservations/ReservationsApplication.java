package com.project.megatravel.reservations;

import java.math.BigInteger;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.reservations.repository.ReservationsRepository;
import com.project.megatravel.reservations.repository.SjRepository;
import com.project.megatravel.reservations.repository.SoRepository;
import com.project.megatravel.reservations.repository.KorisnikRepository;
import com.project.megatravel.reservations.ExistDB;
import com.project.megatravel.util.Creator;

@EnableDiscoveryClient
@SpringBootApplication
public class ReservationsApplication {

	private final static Logger logger = Logger.getLogger(ReservationsApplication.class.getName());
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		logger.info("Connecting to database");
		
		try {
			ExistDB.initDatabase();
			logger.info("Connection with database established");
		} catch (Exception e) {
			logger.warning("Can't connect to database");
		}
		
		SpringApplication.run(ReservationsApplication.class, args);
		
		logger.info("Reservations microservice successfully started");
		
		Creator cr = new Creator();
		
	/*	ReservationsRepository r = new ReservationsRepository();
		SjRepository sjr = new SjRepository();
		SoRepository sjo = new SoRepository();
		KorisnikRepository kr = new KorisnikRepository();
		
		
		KrajnjiKorisnik kk = kr.getByEmail("pera@gmail.com");
		SmestajniObjekat so = cr.createSmestajniObjekat("Hotel 5*");
		SmestajnaJedinica sj = cr.createSmestajnaJedinica(new BigInteger("4"),so);
		RezervacijaKorisnika rk = cr.createRezervacija(250, 5, "19/6/2019", "19/7/2019", "28/7/2019", "Stanje?", sj,kk);
		
		
		
		sjo.save(so);
		sjr.save(sj);
		r.save(rk);*/
		
	}

}
