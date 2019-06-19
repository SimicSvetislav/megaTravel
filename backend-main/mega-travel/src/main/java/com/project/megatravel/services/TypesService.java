package com.project.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.repository.TypesRepository;

@Service
public class TypesService {

	@Autowired
	private TypesRepository repo;
	
	public List<TipSmestaja> getAll() {
		
		List<TipSmestaja> sve = (List<TipSmestaja>)repo.getAll();
		
		return sve;
	}
	
	public TipSmestaja getOneById(Long id) {

		TipSmestaja du = repo.getOneById(id);
		
		return du;
		
	}
	
	public TipSmestaja save(TipSmestaja du) {

		du = repo.save(du);
		
		return du;
		
	}

	public TipSmestaja removeById(Long id) {

		TipSmestaja du = repo.deleteById(id);
		
		return du;
		
	}
	
}
