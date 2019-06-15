package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;

@Repository
public class SjRepository implements ExistRepository {

	private static final String collectionName = "/jedinice";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = SmestajnaJedinica.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/accomodation schemas/SmestajSema.xsd";
	
	public SjRepository() {
		
	}
	
	@Override
	public SmestajnaJedinica save(Object entity) {
		
		SmestajnaJedinica sj = (SmestajnaJedinica) entity;
		
		if (sj.getId()==null) {
			// Dodeli id
			sj.setId(++currentId);
		}

		return ExistDB.save(sj, sj.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public SmestajnaJedinica getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<SmestajnaJedinica> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public SmestajnaJedinica deleteById(Long id) {
		
		SmestajnaJedinica sj = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return sj;
		
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		SjRepository.currentId = currentId;
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