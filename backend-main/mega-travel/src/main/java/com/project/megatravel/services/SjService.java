package com.project.megatravel.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.SoRepository;
import com.project.megatravel.util.Creator;

@Service
public class SjService {

	@Autowired
	private SjRepository sjRepository;
		
	public Collection<SmestajnaJedinica> getAll() {
		return sjRepository.getAll();
	}
	
	public SmestajnaJedinica getOneById(long id) {
		return sjRepository.getOneById(id);
	}
	
	public SmestajnaJedinica save(SmestajnaJedinica jedinica) {
		SmestajnaJedinica jed = sjRepository.save(jedinica);
		
		return jed;
	}
	
	public SmestajnaJedinica deleteById(long id) {
		return sjRepository.deleteById(id);
	}
	
	public List<SmestajnaJedinica> getObjectUnits(long id){
		// Collection<SmestajnaJedinica> collection = getAll();
		
		List<SmestajnaJedinica> collection = new ArrayList<SmestajnaJedinica>();
		collection.add(Creator.createSmestajnaJedinica(1l, 1l));
		collection.add(Creator.createSmestajnaJedinica(2l, 2l));
		collection.add(Creator.createSmestajnaJedinica(3l, 1l));
		collection.add(Creator.createSmestajnaJedinica(4l, 2l));
		collection.add(Creator.createSmestajnaJedinica(5l, 1l));
		collection.add(Creator.createSmestajnaJedinica(6l, 3l));
		collection.add(Creator.createSmestajnaJedinica(7l, 1l));

		
		List<SmestajnaJedinica> units = collection.stream().
										filter(room -> room.getSObjekat() == id).
										collect(Collectors.toList());
		
		return units;
	}
	
}
