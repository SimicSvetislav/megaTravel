package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.accomodation.DodatnaUsluga;

@Repository
public class ExtrasRepository implements ExistRepository {

	private static final String collectionName = "/dodaci";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = DodatnaUsluga.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/accomodation schemas/SmestajSema.xsd";
	
	public ExtrasRepository() {
		
	}
	
	@Override
	public DodatnaUsluga save(Object entity) {
		
		DodatnaUsluga du = (DodatnaUsluga) entity;
		
		if (du.getId()==null) {
			// Dodeli id
			du.setId(++currentId);
		}

		return ExistDB.save(du, du.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public DodatnaUsluga getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<DodatnaUsluga> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public DodatnaUsluga deleteById(Long id) {
		
		DodatnaUsluga du = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return du;
		
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		ExtrasRepository.currentId = currentId;
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
