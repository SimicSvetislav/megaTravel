package com.project.megatravel.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<SmestajniObjekat> getAllAgentObject(long agentId){
		Collection<SmestajniObjekat> collection = getAll();
		
		List<SmestajniObjekat> objects =  collection.stream().
										  filter(object -> object.getAgent() == agentId).
										  collect(Collectors.toList());
		
		return objects;
	}
	
}

