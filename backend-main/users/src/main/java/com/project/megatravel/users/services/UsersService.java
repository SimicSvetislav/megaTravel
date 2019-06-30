package com.project.megatravel.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.users.repository.AdminRepository;
import com.project.megatravel.users.repository.KorisnikRepository;

@Service
@CrossOrigin
public class UsersService {

	@Autowired
	private KorisnikRepository repo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	public KrajnjiKorisnik getById(Long id) {
		
		KrajnjiKorisnik kk = repo.getOneById(id);
		
		return kk;
		
	}

	
	public List<KrajnjiKorisnik> getAll() {
		
		List<KrajnjiKorisnik> users = (List<KrajnjiKorisnik>)repo.getAll();
		
		return users;
		
	}
	
	public KrajnjiKorisnik save(KrajnjiKorisnik korisnik) {
		
		KrajnjiKorisnik kk = repo.save(korisnik);
		
		return kk;
		
	}
	
	public KrajnjiKorisnik remove(Long id) {
		
		KrajnjiKorisnik kk = repo.deleteById(id);
		
		return kk;
		
	}
	
	public KrajnjiKorisnik getByEmail(String email) {
		
		KrajnjiKorisnik kk = repo.getByEmail(email);
		
		return kk;
		
	}
	
	public Administrator getAdminById(Long id) {
		
		Administrator kk = adminRepo.getOneById(id);
		
		return kk;
		
	}
	
	
}
