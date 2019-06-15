package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.users.Korisnik;

@Repository
public class KorisnikRepository implements ExistRepository {

	private static final String collectionName = "/korisnici";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = Korisnik.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/users schemas/KorisnikSema.xsd";
	
	public KorisnikRepository() {
		
	}
	
	@Override
	public Korisnik save(Object entity) {
		
		Korisnik kor = (Korisnik) entity;
		
		if (kor.getId()==null) {
			// Dodeli id
			kor.setId(++currentId);
		}

		return ExistDB.save(kor, kor.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public Korisnik getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<Korisnik> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public Korisnik deleteById(Long id) {
		
		Korisnik kor = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return kor;
		
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		KorisnikRepository.currentId = currentId;
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
