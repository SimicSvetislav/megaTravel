package com.project.megatravel.reservations.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.reservations.repository.AgentRepository;
import com.project.megatravel.reservations.repository.RezervacijeRepository;
import com.project.megatravel.reservations.repository.SjRepository;
import com.project.megatravel.reservations.repository.SoRepository;

@Service
public class ReservationService {
	
	@Autowired
	private RezervacijeRepository repo;
	
	@Autowired
	private SjRepository sjRepo;
	
	@Autowired
	private SoRepository soRepo;
	
	@Autowired
	private AgentRepository agRepo;

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

	public Agent getAgentByReservation(Long id) {
		
		RezervacijaKorisnika rez = repo.getOneById(id);
		
		if (rez==null) {
			return null;
		}
		
		SmestajnaJedinica sj = sjRepo.getOneById(rez.getSmestajnaJedinica());
		
		if (sj == null) {
			return null;
		}
		
		SmestajniObjekat so = soRepo.getOneById(sj.getSObjekat());
		
		if (so == null) {
			return null;
		}
		
		Agent agent = agRepo.getOneById(so.getAgent());
		
		return agent;
	}

}
