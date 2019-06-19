package com.project.megatravel.reservations.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.project.megatravel.reservations.ExistDB;
import com.project.megatravel.model.accomodation.SmestajniObjekat;

@Repository
public class SoRepository implements ExistRepository {

	private static final String collectionName = "/objekti";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = SmestajniObjekat.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/reservations schemas/SmestajSema.xsd";
	
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
