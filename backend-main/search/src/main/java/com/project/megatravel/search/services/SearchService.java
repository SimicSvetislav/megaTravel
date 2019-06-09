package com.project.megatravel.search.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.search.PretragaObjekat;

@Service
@CrossOrigin
public class SearchService {

	public List<SmestajniObjekat> searchObject(PretragaObjekat po) {
		
		return new ArrayList<>();
	}
	
	public List<SmestajniObjekat> search(PretragaObjekat po) {
		
		return new ArrayList<>();
	}

}
