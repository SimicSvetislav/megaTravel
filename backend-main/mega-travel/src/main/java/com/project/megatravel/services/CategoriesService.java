package com.project.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.exceptions.ValueConflictException;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.repository.CategoriesRepository;
import com.project.megatravel.repository.SoRepository;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository repo;
	
	@Autowired
	private SoRepository soRepo;
	
	public List<KategorijaSm> getAll() {
		
		List<KategorijaSm> sve = (List<KategorijaSm>)repo.getAll();
		
		return sve;
	}
	
	public KategorijaSm getOneById(Long id) {

		KategorijaSm cat = repo.getOneById(id);
		
		return cat;
		
	}
	
	public KategorijaSm save(KategorijaSm cat) throws ValueConflictException {

		List<KategorijaSm> all = (List<KategorijaSm>) repo.getAll();
		
		for (KategorijaSm c : all) {
			if (c.getZvezdice()==cat.getZvezdice() && c.getId()!=cat.getId()) {
				throw new ValueConflictException();
			}
		}
		
		cat = repo.save(cat);
		
		return cat;
		
	}
	
	public KategorijaSm update(KategorijaSm cat) throws ValueConflictException {

		List<KategorijaSm> all = (List<KategorijaSm>) repo.getAll();
		
		List<SmestajniObjekat> allSo = (List<SmestajniObjekat>) soRepo.getAll();
		
		Long catId = cat.getId();
		
		if (allSo.stream().anyMatch(so -> so.getKategorijaSm().getId()==catId)) {
			throw new ValueConflictException();
		}
		
		for (KategorijaSm c : all) {
			if (c.getZvezdice()==cat.getZvezdice() && c.getId()!=cat.getId()) {
				throw new ValueConflictException();
			}
		}
		
		cat = repo.save(cat);
		
		return cat;
		
	}

	public KategorijaSm removeById(Long id) throws ValueConflictException {

		List<SmestajniObjekat> allSo = (List<SmestajniObjekat>) soRepo.getAll();
		
		if (allSo.stream().anyMatch(so -> so.getKategorijaSm().getId()==id)) {
			throw new ValueConflictException();
		}
		
		
		KategorijaSm cat = repo.deleteById(id);
		
		return cat;
		
	}
	
}
