package com.project.megatravel.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@Service
@CrossOrigin
public class AdminService {

	public Agent getById(Long id) {
		
		return new Agent();
	}

	public List<Agent> getAll() {
		
		return new ArrayList<Agent>();
	}

	public KrajnjiKorisnik updateStatus(Long id, String newStatus) {
		
		return new KrajnjiKorisnik();
	}

}
