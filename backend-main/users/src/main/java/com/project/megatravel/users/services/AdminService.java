package com.project.megatravel.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.users.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repo;

	public Administrator save(Administrator korisnik) {
		
		Administrator admin = repo.save(korisnik);
		
		return admin;
	}

	public Administrator getOneById(Long id) {
		
		Administrator admin = repo.getOneById(id);
		
		return admin;
	}

	public Administrator deleteById(Long id) {
		
		Administrator admin = repo.deleteById(id);
		
		return admin;
	}
	
	public List<Administrator> getAll() {
		
		List<Administrator> all = (List<Administrator>)repo.getAll();
		
		return all;
		
	}
	
	

}
