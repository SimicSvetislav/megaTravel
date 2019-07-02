package com.project.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.exceptions.ValueConflictException;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.repository.SoRepository;
import com.project.megatravel.repository.TypesRepository;

@Service
public class TypesService {

	@Autowired
	private TypesRepository repo;
	
	@Autowired
	private SoRepository soRepo;
	
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
	
	public TipSmestaja update(TipSmestaja tip) throws ValueConflictException {

		List<SmestajniObjekat> allSo = (List<SmestajniObjekat>) soRepo.getAll();
		
		Long id = tip.getId();
		
		if (allSo.stream().anyMatch(so -> so.getTipSmestaja().getId()==id)) {
			throw new ValueConflictException();
		}
		
		tip = repo.save(tip);
		
		return tip;
		
	}

	public TipSmestaja removeById(Long id) throws ValueConflictException {
		
		List<SmestajniObjekat> allSo = (List<SmestajniObjekat>) soRepo.getAll();

		if (allSo.stream().anyMatch(so -> so.getTipSmestaja().getId()==id)) {
			throw new ValueConflictException();
		}

		TipSmestaja du = repo.deleteById(id);
		
		return du;
		
	}
	
}
