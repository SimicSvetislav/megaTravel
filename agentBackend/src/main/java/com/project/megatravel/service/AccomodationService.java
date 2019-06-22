package com.project.megatravel.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.repository.CategoriesRepository;
import com.project.megatravel.repository.ExtrasRepository;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;
import com.project.megatravel.repository.TypesRepository;

@Service
public class AccomodationService {
	
	@Autowired
	private SoRepository soRepository;
	
	@Autowired
	private SjRepository sjRepository;
	
	@Autowired
	private ExtrasRepository extrasRepository;
	
	@Autowired
	private TypesRepository typeRepository;
	
	@Autowired
	private CategoriesRepository categoryRepository;
	
	public Collection<SmestajniObjekat> getAllAccomodationObjects(){
		return soRepository.getAll();
	}
	
	public SmestajniObjekat getAccomodationObject(Long objekatId) {
		return soRepository.getOneById(objekatId);
	}
	
	public SmestajniObjekat addNewObject(SmestajniObjekat objekat) {		
		return soRepository.save(objekat);
	}
	
	public List<SmestajnaJedinica> getAllObjectUnits(Long objekatId){
		SmestajniObjekat object = getAccomodationObject(objekatId);
		return object.getSmestajnaJedinica();
	}
	
	public SmestajnaJedinica getObjectUnit(Long unitId) {
		return sjRepository.getOneById(unitId);
	}
	
	public SmestajnaJedinica addNewObjectUnit(SmestajnaJedinica jedinica) {
		jedinica = sjRepository.save(jedinica); 
		updateSmestajniObjekat(jedinica);
		
		return jedinica;
	}
	
	private void updateSmestajniObjekat(SmestajnaJedinica jedinica) {
		SmestajniObjekat objekat = soRepository.getOneById(jedinica.getSObjekat());
		objekat.getSmestajnaJedinica().add(jedinica);
		soRepository.save(objekat);
	}
	
	public Collection<DodatnaUsluga> getAllAccomodationExtras() {
		return extrasRepository.getAll(); 
	}
	
	public Collection<TipSmestaja> getAllAccomodationTypes() {
		return typeRepository.getAll(); 
	}
	
	public Collection<KategorijaSm> getAllAccomodationCategories() {
		return categoryRepository.getAll(); 
	}

}
