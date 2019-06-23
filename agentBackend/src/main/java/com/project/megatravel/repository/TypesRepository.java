package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.accomodation.TipSmestaja;

@Repository
public class TypesRepository implements ExistRepository {
	
	private static final String collectionName = "/tipovi";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = TipSmestaja.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/accomodation schemas/SmestajSema.xsd";
	
	public TypesRepository() {
		
	}
	
	@Override
	public TipSmestaja save(Object entity) {
		
		TipSmestaja tip = (TipSmestaja) entity;
		
		if (tip.getId()==null) {
			// Dodeli id
			tip.setId(++currentId);
		}

		return ExistDB.save(tip, tip.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public TipSmestaja getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<TipSmestaja> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public TipSmestaja deleteById(Long id) {
		
		TipSmestaja tip = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return tip;
		
	}
	
	public void overrideData(Collection<TipSmestaja> updatingData) {
		try {
			ExistDB.emptyCollection(collectionName);
			for(TipSmestaja tip: updatingData) {
				ExistDB.save(tip, tip.getId(), collectionName, schemaLocation, jaxbContext);
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
		TypesRepository.currentId = currentId;
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
