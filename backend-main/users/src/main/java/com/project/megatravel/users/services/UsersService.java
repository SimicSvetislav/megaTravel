package com.project.megatravel.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.repository.KorisnikRepository;

@Service
@CrossOrigin
public class UsersService {

	@Autowired
	private KorisnikRepository repo;
	
	public KrajnjiKorisnik getById(Long id) {
		
		KrajnjiKorisnik kk = repo.getOneById(id);
		
		return kk;
		
	}

	
	public List<KrajnjiKorisnik> getAll() {
		
		List<KrajnjiKorisnik> users = (List<KrajnjiKorisnik>)repo.getAll();
		
		return users;
		
	}
	
	
}
