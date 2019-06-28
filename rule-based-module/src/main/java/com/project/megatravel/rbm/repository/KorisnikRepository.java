package com.project.megatravel.rbm.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
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
	
	@Autowired
	SjRepository sjRepo = new SjRepository();
	
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
		
		KrajnjiKorisnik kk = ExistDB.getOneById(id, collectionName, jaxbContext);
		
		if (kk!=null) {
		for (RezervacijaKorisnika r: kk.getRezervacije()) {
			if (r.getSmestajE()!=null) {
				r.setSmestaj(sjRepo.getOneById(r.getSmestajE()));
			}
			
		} }
				
		return kk;
	}

	@Override
	public Collection<KrajnjiKorisnik> getAll() {
		
		Collection<KrajnjiKorisnik> all = ExistDB.getAll(collectionName, jaxbContext);
		
		for (KrajnjiKorisnik s: all) {
			if (s!=null && s.getRezervacije()!=null) {
			for (RezervacijaKorisnika r: s.getRezervacije()) {
				if (r.getSmestajE()!=null) {
					r.setSmestaj(sjRepo.getOneById(r.getSmestajE()));
				} 
			} }
		}
		
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
