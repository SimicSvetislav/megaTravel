package com.project.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.repository.ExtrasRepository;

@Service
public class ExtrasService {

	@Autowired
	private ExtrasRepository repo;
	
	public List<DodatnaUsluga> getAll() {
		
		List<DodatnaUsluga> sve = (List<DodatnaUsluga>)repo.getAll();
		
		return sve;
	}
	
	public DodatnaUsluga getOneById(Long id) {

		DodatnaUsluga du = repo.getOneById(id);
		
		return du;
		
	}
	
	public DodatnaUsluga save(DodatnaUsluga du) {

		du = repo.save(du);
		
		return du;
		
	}

	public DodatnaUsluga removeById(Long id) {

		DodatnaUsluga du = repo.deleteById(id);
		
		return du;
		
	}

	
	
}
