package com.project.megatravel.reservations.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.megatravel.reservations.ExistDB;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;

@Repository
public class SjRepository implements ExistRepository {

	private static final String collectionName = "/jedinice";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = SmestajnaJedinica.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/accomodation schemas/SmestajSema.xsd";
	
	private SoRepository soRepo = new SoRepository();
	
	@Override
	public SmestajnaJedinica save(Object entity) {
		
		SmestajnaJedinica sj = (SmestajnaJedinica) entity;
		
		if (sj.getId()==null) {
			// Dodeli id, prvi put se cuva
			sj.setId(++currentId);
		} else {
			// Id postoji, radi se update
			Long soId = sj.getSObjekat().getId();
			if (soId!=null) {
				SmestajniObjekat so = soRepo.getOneById(soId);
				List<SmestajnaJedinica> jedinice = so.getSmestajnaJedinica();
				for (SmestajnaJedinica s : jedinice) {
					if (s.getId() == sj.getId()) {
						so.getSmestajnaJedinica().remove(s);
						so.getSmestajnaJedinica().add(sj);
						
						// Pretpostavka da nema duplikata
						break;
					}
				}
				
				so = soRepo.save(so);
			}
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
		
		Long soId = sj.getSObjekat().getId();
		if (soId!=null) {
			SmestajniObjekat so = soRepo.getOneById(soId);
			List<SmestajnaJedinica> jedinice = so.getSmestajnaJedinica();
			for (SmestajnaJedinica s : jedinice) {
				if (s.getId() == sj.getId()) {
					so.getSmestajnaJedinica().remove(s);
					
					// Pretpostavka da nema duplikata
					break;
				}
			}
			
			so = soRepo.save(so);
		}
		
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