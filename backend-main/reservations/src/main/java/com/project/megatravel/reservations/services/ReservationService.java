package com.project.megatravel.reservations.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;

@Service
public class ReservationService {

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
		
		return new ArrayList<>();
	}

	public List<RezervacijaKorisnika> getAllByUser(Long id) {
		
		return new ArrayList<>();
	}
	
	public List<RezervacijaKorisnika> getAllByUnit(Long id) {
		
		return new ArrayList<>();
	}
	
	public List<RezervacijaKorisnika> getAllByObject(Long id) {
		
		return new ArrayList<>();
	}

}
