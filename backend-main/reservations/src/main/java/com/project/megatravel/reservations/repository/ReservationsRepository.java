package com.project.megatravel.reservations.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.model.reservations.Rezervacija;
import com.project.megatravel.reservations.ExistDB;

@Repository
public class ReservationsRepository implements ExistRepository {

	private static final String collectionName = "/rezervacije";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = Rezervacija.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/reservations schemas/Rezervacija.xsd";

	
	@Override
	public Object save(Object entity) {
		// TODO Auto-generated method stub
		
		Rezervacija r = (Rezervacija) entity;
		
		if(r.getId()==null) {
			r.setId(++currentId);
		}
		
		return ExistDB.save(r, r.getId(), collectionName, schemaLocation, jaxbContext);
	}

	@Override
	public Object getOneById(Long id) {
		// TODO Auto-generated method stub
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<? extends Object> getAll() {
		// TODO Auto-generated method stub
		return ExistDB.getAll(collectionName, jaxbContext);
	}

	@Override
	public Object deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
