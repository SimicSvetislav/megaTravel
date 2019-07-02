package com.project.megatravel.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.repository.ExtrasRepository;
import com.project.megatravel.repository.SoRepository;

@Service
public class ExtrasService {

	@Autowired
	private ExtrasRepository repo;
	
	@Autowired
	private SoRepository soRepo;
	
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

		Collection<SmestajniObjekat> all = soRepo.getAll();
		
		for (SmestajniObjekat so: all) {
			List<DodatnaUsluga> du = so.getDodatnaUsluga();
			
			if (du.stream().anyMatch(u -> u.getId()==id)) {
				return null;
			}
			
		}
		
		DodatnaUsluga du = repo.deleteById(id);
		
		return du;
		
	}

	
	
}
