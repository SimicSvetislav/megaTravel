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
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.reservations.repository.AgentRepository;
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
	
	// XSL sabloni
	private Source xslDoc = new StreamSource(TypeReference.class.getResourceAsStream("/xsl/reservation.xsl"));
	private Source xslDocMail = new StreamSource(TypeReference.class.getResourceAsStream("/xsl/reservation_mail.xsl"));
	
	
	// Fabrika za kreiranje transformera formata
	private TransformerFactory transformerFactory = TransformerFactory.newInstance();

	public RezervacijaKorisnika makeReservation(RezervacijaKorisnika rezervacija) {
		
		return new RezervacijaKorisnika();
	}

	public RezervacijaKorisnika updateReservation(RezervacijaKorisnika rezervacija) {
		
		return new RezervacijaKorisnika();
	}

	public RezervacijaKorisnika deleteRez(Long id) {
		
		return new RezervacijaKorisnika();
	}

	public RezervacijaKorisnika getById(Long id) {
		
		return new RezervacijaKorisnika();
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
		
		return new ArrayList<>();
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

}
