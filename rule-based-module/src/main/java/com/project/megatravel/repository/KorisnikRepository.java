package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.ExistDB;

@Repository
public class KorisnikRepository implements ExistRepository {

	private static final String collectionName = "/korisnici";
	private static Long currentId = ExistDB.determineIdUser();
	private static final String jaxbContext = KrajnjiKorisnik.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/users schemas/KorisnikSema.xsd";
	
	public KorisnikRepository() {
		
	}
	
	@Override
	public KrajnjiKorisnik save(Object entity) {
		
		KrajnjiKorisnik kor = (KrajnjiKorisnik) entity;
		
		if (kor.getId()==null) {
			// Dodeli id
			kor.setId(++currentId);
		}

		return ExistDB.save(kor, kor.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public KrajnjiKorisnik getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<KrajnjiKorisnik> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public KrajnjiKorisnik deleteById(Long id) {
		
		KrajnjiKorisnik kor = ExistDB.deleteById(id, collectionName, jaxbContext);
		
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
