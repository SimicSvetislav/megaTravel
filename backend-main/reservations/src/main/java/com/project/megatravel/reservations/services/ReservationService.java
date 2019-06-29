package com.project.megatravel.reservations.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.reservations.repository.AgentRepository;
import com.project.megatravel.reservations.repository.KorisnikRepository;
import com.project.megatravel.reservations.repository.RezervacijeRepository;
import com.project.megatravel.reservations.repository.SjRepository;
import com.project.megatravel.reservations.repository.SoRepository;

@Service
public class ReservationService {
	
	@Autowired
	private RezervacijeRepository repo;
	
	@Autowired
	private SjRepository sjRepo;
	
	@Autowired
	private SoRepository soRepo;
	
	@Autowired
	private AgentRepository agRepo;
	
	@Autowired
	private KorisnikRepository userRepo;
	
	// Fabrika za kreiranje transformera formata
	private TransformerFactory transformerFactory = TransformerFactory.newInstance();

	//private String RBM = "http://localhost:8020/";
	
	public RezervacijaKorisnika makeReservation(RezervacijaKorisnika rezervacija) {
		
		return repo.save(rezervacija);
	}

	public RezervacijaKorisnika updateReservation(RezervacijaKorisnika rezervacija) {
		
		return repo.save(rezervacija);
	}

	public RezervacijaKorisnika deleteRez(Long id) {
		
		return repo.deleteById(id);
	}

	public RezervacijaKorisnika getById(Long id) {
		
		return repo.getOneById(id);
	}

	public List<RezervacijaKorisnika> getAll() {
		
		List<RezervacijaKorisnika> rk = (List<RezervacijaKorisnika>)repo.getAll();
		
		return rk;
	}

	public List<RezervacijaKorisnika> getAllByUser(Long id) {
		
		List<RezervacijaKorisnika> pom = new ArrayList<RezervacijaKorisnika>();
		
		List<RezervacijaKorisnika> rk = (List<RezervacijaKorisnika>)repo.getAll();
		
		for (RezervacijaKorisnika rezervacijaKorisnika : rk) {
			if(rezervacijaKorisnika.getKorisnik().equals(id)) {
				pom.add(rezervacijaKorisnika);
			}
		}
		
		
		return pom;
	}
	
	public List<RezervacijaKorisnika> getAllByUnit(Long id) {
		
		return getAll().stream().filter(b -> b.getSmestajnaJedinica() == id).collect(Collectors.toList());
	}
	
	public List<RezervacijaKorisnika> getAllByObject(Long id) {
		
		return new ArrayList<>();
	}

	public Agent getAgentByReservation(Long id) {
		
		RezervacijaKorisnika rez = repo.getOneById(id);
		
		if (rez==null) {
			return null;
		}
		
		SmestajnaJedinica sj = sjRepo.getOneById(rez.getSmestajnaJedinica());
		
		if (sj == null) {
			return null;
		}
		
		SmestajniObjekat so = soRepo.getOneById(sj.getSObjekat());
		
		if (so == null) {
			return null;
		}
		
		Agent agent = agRepo.getOneById(so.getAgent());
		
		return agent;
	}

	public String generateHTML(Long id) {
		
		// XSL sablon
		Source xslDoc = new StreamSource(TypeReference.class.getResourceAsStream("/xsl/reservation.xsl"));
		
		
		// XML fajl procitan kao string
		String xmlString = repo.getOneByIdStringify(id);
		Source xmlDoc = new StreamSource(new StringReader(xmlString));
		
		// Konkretan transformer koji se pravi na osnovu XSL fajla
		Transformer transformer = null;
		
		try {
			transformer = transformerFactory.newTransformer(xslDoc);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return "Can't make transformer";
		}
		
		// Izlaz iz transformacije
		OutputStream htmlFile = new ByteArrayOutputStream();		
		
		// Pozivanje transformacije za ulazni fajl
		try {
			transformer.transform(xmlDoc, new StreamResult(htmlFile));
		} catch (TransformerException e) {
			e.printStackTrace();	
			try {
				htmlFile.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			return "Transformation failed";
		}
		
		String rawHtml = htmlFile.toString();
	
		try {
			htmlFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return rawHtml;
	}
	
	public String generateHTMLToFile(Long id) {
		
		// XSL sablon
		Source xslDocMail = new StreamSource(TypeReference.class.getResourceAsStream("/xsl/reservation_mail.xsl"));
		
		// XML fajl procitan kao string
		String xmlString = repo.getOneByIdStringify(id);
		Source xmlDoc = new StreamSource(new StringReader(xmlString));
		
		// Konkretan transformer koji se pravi na osnovu XSL fajla
		Transformer transformer = null;
		
		try {
			transformer = transformerFactory.newTransformer(xslDocMail);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return "Can't make transformer";
		}
		
		// Izlaz iz transformacije		
		OutputStream htmlFile = null;
		
		// Pozivanje transformacije za ulazni fajl
		try {
			String outputFileName = "C:\\Users\\Sveta\\Desktop\\report.html";
			htmlFile = new FileOutputStream(outputFileName);
			transformer.transform(xmlDoc, new StreamResult(htmlFile));
		} catch (TransformerException | FileNotFoundException e) {
			e.printStackTrace();	
			try {
				htmlFile.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			return "Transformation failed";
		}
		
		String rawHtml = htmlFile.toString();
	
		try {
			htmlFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return rawHtml;
	}
	
	public InputStream generateHTMLForMail(Long id) {
		
		// XSL sablon
		Source xslDocMail = new StreamSource(TypeReference.class.getResourceAsStream("/xsl/reservation_mail.xsl"));
		
		// XML fajl procitan kao string
		String xmlString = repo.getOneByIdStringify(id);
		Source xmlDoc = new StreamSource(new StringReader(xmlString));
		
		// Konkretan transformer koji se pravi na osnovu XSL fajla
		Transformer transformer = null;
		
		try {
			transformer = transformerFactory.newTransformer(xslDocMail);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
		// Izlaz iz transformacije
		ByteArrayOutputStream htmlFile = new ByteArrayOutputStream();		
		
		// Pozivanje transformacije za ulazni fajl
		try {
			transformer.transform(xmlDoc, new StreamResult(htmlFile));
		} catch (TransformerException e) {
			e.printStackTrace();	
			try {
				htmlFile.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			return null;
		}
		
		InputStream stream = new ByteArrayInputStream(htmlFile.toByteArray());
	
		try {
			htmlFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return stream;
	}
	
	public RezervacijaKorisnika makingReservationFromAgent(RezervacijaKorisnika rezervacija) {
		RezervacijaKorisnika ret = null;
		
		if(isBookingPossible(rezervacija)) {
			ret = repo.save(rezervacija);
		}
		
		return ret;
	}
	
	private boolean isBookingPossible(RezervacijaKorisnika newBooking) {
		long newBookingBegin = newBooking.getDatumPocetka().getTime();
		long newBookingEnd = newBooking.getDatumZavrsetka().getTime();
		
		List<RezervacijaKorisnika> rezervacije = getAllByUnit(newBooking.getSmestajnaJedinica());
	
		for(RezervacijaKorisnika r : rezervacije) {
			long begin = r.getDatumPocetka().getTime();
			long end = r.getDatumZavrsetka().getTime();
			
			
//			if( (newBookingBegin >= begin && newBookingEnd <= end) || (begin >= newBookingBegin && begin <= newBookingEnd)) {
//				return false;
//			}
			
			long numberOfDaysInRange = numberOfDaysThatAreInRange(newBookingBegin, newBookingEnd, begin, end);
			if(numberOfDaysInRange > 0) {
				return false;
			}
			
		}
		
		return true;
	}
	
	private long  calculuteDaysBetween(long searchObjectBeginDate, long searchObjectEndDate) {
		long difference = (searchObjectBeginDate - searchObjectEndDate) / 86400000; // millisecond per day
		return Math.abs(difference);
	}
	
	private long numberOfDaysThatAreInRange(long searchObjectBeginDate, long searchObjectEndDate, long priceBeginDate, long priceEndDate) {
		if(searchObjectBeginDate < priceBeginDate && searchObjectEndDate > priceEndDate) {  //zeljeni termin obuhvata cak i prevazilazi  termin
			return calculuteDaysBetween(priceBeginDate, priceEndDate);
		}
		
		if(searchObjectBeginDate >= priceBeginDate && searchObjectBeginDate < priceEndDate) {  //pocetak zeljnog termina upada u ovaj  termin
			if(searchObjectEndDate <= priceEndDate) {				// zeljeni datumi su u celini u jednom  terminu
				return calculuteDaysBetween(searchObjectBeginDate, searchObjectEndDate);
			}else {
				return calculuteDaysBetween(searchObjectBeginDate, priceEndDate);		// delicmo termin upada u ovaj  termin
			}
		}
		
		if (searchObjectEndDate > priceBeginDate && searchObjectEndDate <= priceEndDate ){
			return calculuteDaysBetween(priceBeginDate, searchObjectEndDate);
		}
		
		
		return 0; // zeljeni datum uopste ne upada u ovaj  termina ;
	}

	public KrajnjiKorisnik getUser(Long id) {
		
		RezervacijaKorisnika rez = repo.getOneById(id);
		
		KrajnjiKorisnik user = userRepo.getOneById(rez.getKorisnik());
		
		return user;
	}

	public String cancel(Long id) {
		
		RezervacijaKorisnika rez = repo.getOneById(id);
		
		String response = "Ne treba nista platiti";
		
		SmestajnaJedinica sj = sjRepo.getOneById(rez.getSmestajnaJedinica());
		
		if (sj.getOtkazivanje().isDozvoljeno()) {
			Integer dana = sj.getOtkazivanje().getBrojDana();
			Date d = DateUtils.addDays(new Date(), dana);
			
			if (d.before(rez.getDatumPocetka())) {
				RestTemplate rest = new RestTemplate();
				
				/* SBNZ
				//String url = RBM + "cancel/" + id;
				//response = rest.getForObject(url, String.class);
				*/
			}
		}
		
		return response;
	}
	
	

}
