package com.project.megatravel.reservations.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.reservations.repository.RezervacijeRepository;

@Service
public class ReservationService {
	
	@Autowired
	private RezervacijeRepository repo;

	public RezervacijaKorisnika makeReservation(RezervacijaKorisnika rezervacija) {
		
		return new RezervacijaKorisnika();
	}

	public RezervacijaKorisnika updateReservation(RezervacijaKorisnika rezervacija) {
		
		return new RezervacijaKorisnika();
	}

	public RezervacijaKorisnika deleteRez(Long id) {
		
		return new RezervacijaKorisnika();
	}

	public RezervacijaKorisnika getById(String id) {
		
		return new RezervacijaKorisnika();
	}

	public List<RezervacijaKorisnika> getAll() {
		
		@SuppressWarnings("unchecked")
		List<RezervacijaKorisnika> rk = (List<RezervacijaKorisnika>)repo.getAll();
		
		return rk;
	}

	public List<RezervacijaKorisnika> getAllByUser(Long id) {
		
		List<RezervacijaKorisnika> pom = new ArrayList<RezervacijaKorisnika>();
		
		@SuppressWarnings("unchecked")
		List<RezervacijaKorisnika> rk = (List<RezervacijaKorisnika>)repo.getAll();
		
		for (RezervacijaKorisnika rezervacijaKorisnika : rk) {
			if(rezervacijaKorisnika.getKorisnik().equals(id)) {
				pom.add(rezervacijaKorisnika);
			}
		}
		
		
		return pom;
	}
	
	public List<RezervacijaKorisnika> getAllByUnit(Long id) {
		
		return new ArrayList<>();
	}
	
	public List<RezervacijaKorisnika> getAllByObject(Long id) {
		
		return new ArrayList<>();
	}

}
