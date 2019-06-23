package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.messages.agent.Rezervacija;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;

@Repository
public class RezervacijeRepository implements ExistRepository {

	private static final String collectionName = "/rezervacije";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = Rezervacija.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/reservations schemas/Rezervacija.xsd";
	
	public RezervacijeRepository() {
		
	}
	
	@Override
	public RezervacijaKorisnika save(Object entity) {
		
		RezervacijaKorisnika rez = (RezervacijaKorisnika) entity;
		
		if (rez.getId()==null) {
			// Dodeli id
			rez.setId(++currentId);
		}

		return ExistDB.save(rez, rez.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public RezervacijaKorisnika getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<RezervacijaKorisnika> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public RezervacijaKorisnika deleteById(Long id) {
		
		RezervacijaKorisnika rez = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return rez;
		
	}
	
	public void overrideData(Collection<RezervacijaKorisnika> updatingData) {
		try {
			ExistDB.emptyCollection(collectionName);
			for(RezervacijaKorisnika rezervacija: updatingData) {
				ExistDB.save(rezervacija, rezervacija.getId(), collectionName, schemaLocation, jaxbContext);
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		RezervacijeRepository.currentId = currentId;
	}

	public static String getCollectionname() {
		return collectionName;
	}

	public static String getJaxbcontext() {
		return jaxbContext;
	}

	public static String getSchemalocation() {
		return schemaLocation;
	}

	
	
}
