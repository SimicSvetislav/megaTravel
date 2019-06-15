package com.project.megatravel.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.repository.SoRepository;

@Service
public class SoService {

	@Autowired
	private SoRepository repo;
	
	public SmestajniObjekat save(SmestajniObjekat entity) {
		
		SmestajniObjekat so = repo.save(entity);
		
		return so;
		
	}
	
	public SmestajniObjekat getOneById(Long id) {
		
		SmestajniObjekat so = repo.getOneById(id);
		
		return so;
		
	}

	public Collection<SmestajniObjekat> getAll() {
		
		Collection<SmestajniObjekat> collection = repo.getAll();
		
		return collection;
	
	}
	
	public SmestajniObjekat deleteById(Long id) {
		
		SmestajniObjekat so = repo.deleteById(id);
		
		return so;
		
	}
	
}

