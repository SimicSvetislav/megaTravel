package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.TipSmestaja;

@Repository
public class CategoriesRepository implements ExistRepository {
	
	private static final String collectionName = "/kategorije";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = TipSmestaja.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/accomodation schemas/SmestajSema.xsd";
	
	public CategoriesRepository() {
		
	}
	
	@Override
	public KategorijaSm save(Object entity) {
		
		KategorijaSm cat = (KategorijaSm) entity;
		
		if (cat.getId()==null) {
			// Dodeli id
			cat.setId(++currentId);
		}

		return ExistDB.save(cat, cat.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public KategorijaSm getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<KategorijaSm> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public KategorijaSm deleteById(Long id) {
		
		KategorijaSm cat = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return cat;
		
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		CategoriesRepository.currentId = currentId;
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
