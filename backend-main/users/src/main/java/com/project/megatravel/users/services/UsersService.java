package com.project.megatravel.users.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.users.KrajnjiKorisnik;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class UsersService {

	public KrajnjiKorisnik getById(Long id) {
		
		KrajnjiKorisnik kk = new KrajnjiKorisnik();
		kk.setId(1L);
		
		return kk;
		
	}

	
	public List<KrajnjiKorisnik> getAll() {
		
		return new ArrayList<>();
		
	}
	
	
}
