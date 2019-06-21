package com.project.megatravel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.repository.RezervacijeRepository;
import com.project.megatravel.repository.SjRepository;

@Service
public class BookingService {
	
	@Autowired
	private RezervacijeRepository bookingRepository;
	
	@Autowired
	private SjRepository sJRepository;

	public RezervacijaKorisnika makeReservation(RezervacijaKorisnika rezervacija) {
		rezervacija.setDatumRezervacije(new Date());
		return bookingRepository.save(rezervacija);
	}

	public RezervacijaKorisnika updateReservation(RezervacijaKorisnika rezervacija) {
		return bookingRepository.save(rezervacija);
	}
	
	public RezervacijaKorisnika confirmBooking(Long bookingId) {
		RezervacijaKorisnika booking = getById(bookingId);
		booking.setStanje("POTVRDJENO");
		return bookingRepository.save(booking);
	}

//	public RezervacijaKorisnika deleteRez(Long id) {
//		
//		return new RezervacijaKorisnika();
//	}

	public RezervacijaKorisnika getById(Long id) {
		
		return bookingRepository.getOneById(id);
	}

	public Collection<RezervacijaKorisnika> getAll() {	
		return bookingRepository.getAll();	
	}
	
	
	public Collection<RezervacijaKorisnika> getBookingsHistory(){
		List<RezervacijaKorisnika> bookingHistory = new ArrayList<RezervacijaKorisnika>();
		Collection<RezervacijaKorisnika> allBookings = getAll();
		for(RezervacijaKorisnika rez: allBookings) {
			if(rez.getStanje().equals("REALIZOVANO")) {
				bookingHistory.add(rez);
			}
		}
		
		return bookingHistory;
	}
	
	public Collection<RezervacijaKorisnika> getBookingsUpcoming() {
		List<RezervacijaKorisnika> bookingUpcoming= new ArrayList<RezervacijaKorisnika>();
		Collection<RezervacijaKorisnika> allBookings = getAll();
		for(RezervacijaKorisnika rez: allBookings) {
			if(rez.getStanje().equals("REZERVISANO")) {
				bookingUpcoming.add(rez);
			}
		}
		
		return bookingUpcoming;
	}

	public Collection<RezervacijaKorisnika> getAllByUnit(Long id) {
		List<RezervacijaKorisnika> bookingUnita= new ArrayList<RezervacijaKorisnika>();
		Collection<RezervacijaKorisnika> allBookings = getAll();
		for(RezervacijaKorisnika rez: allBookings) {
			if(rez.getSmestajnaJedinica() == id) {
				bookingUnita.add(rez);
			}
		}
		
		return bookingUnita;
	}
	
	public Collection<RezervacijaKorisnika> getAllByObject(Long unitId) {
		List<RezervacijaKorisnika> bookingUpcoming= new ArrayList<RezervacijaKorisnika>();
		Collection<RezervacijaKorisnika> allBookings = getAll();
		
		Long objectId = sJRepository.getOneById(unitId).getSObjekat();
		
		for(RezervacijaKorisnika rez: allBookings) {
			Long rezObjectId = sJRepository.getOneById(unitId).getSObjekat();
			if(rezObjectId == objectId) {
				bookingUpcoming.add(rez);
			}
		}
		
		return bookingUpcoming;
	}

}
