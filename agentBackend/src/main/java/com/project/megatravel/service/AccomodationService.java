package com.project.megatravel.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;

@Service
public class AccomodationService {
	
	@Autowired
	private SoRepository soRepository;
	
	@Autowired
	private SjRepository sjRepository;
	
	public Collection<SmestajniObjekat> getAllAccomodationObjects(){
		return soRepository.getAll();
	}
	
	public SmestajniObjekat getAccomodationObject(Long objekatId) {
		return soRepository.getOneById(objekatId);
	}
	
	public SmestajnaJedinica addNewObjectUnit(SmestajnaJedinica jedinica) {
		jedinica = sjRepository.save(jedinica); 
		updateSmestajniObjekat(jedinica);
		
		return jedinica;
	}
	
	public List<SmestajnaJedinica> getAllObjectUnits(Long objekatId){
		SmestajniObjekat object = getAccomodationObject(objekatId);
		return object.getSmestajnaJedinica();
	}
	
	public SmestajnaJedinica getObjectUnit(Long unitId) {
		return sjRepository.getOneById(unitId);
	}
	
	private void updateSmestajniObjekat(SmestajnaJedinica jedinica) {
		SmestajniObjekat objekat = soRepository.getOneById(jedinica.getSObjekat());
		objekat.getSmestajnaJedinica().add(jedinica);
		soRepository.save(objekat);
	}

}
