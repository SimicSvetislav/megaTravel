package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.SmestajniObjekat;

@Repository
public class SoRepository implements ExistRepository {

	private static final String collectionName = "/objekti";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = SmestajniObjekat.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/accomodation schemas/SmestajSema.xsd";
	
	@Override
	public SmestajniObjekat save(Object entity) {
		
		SmestajniObjekat so = (SmestajniObjekat) entity;
		
		if (so.getId()==null) {
			// Dodeli id
			so.setId(++currentId);
		}

		return ExistDB.save(so, so.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public SmestajniObjekat getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<SmestajniObjekat> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public SmestajniObjekat deleteById(Long id) {
		
		SmestajniObjekat so = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return so;
		
	}
	
	public void overrideData(Collection<SmestajniObjekat> updatingData) {
		try {
			ExistDB.emptyCollection(collectionName);
			for(SmestajniObjekat usluga: updatingData) {
				ExistDB.save(usluga, usluga.getId(), collectionName, schemaLocation, jaxbContext);
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
		SoRepository.currentId = currentId;
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
