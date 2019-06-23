package com.project.megatravel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import com.project.megatravel.controller.ws.client.AccomodationClient;
import com.project.megatravel.controller.ws.client.AccomodationRatingClient;
import com.project.megatravel.controller.ws.client.BookingClient;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.repository.RezervacijeRepository;
import com.project.megatravel.repository.SjRepository;
import com.project.megatravel.repository.TypesRepository;
import com.project.megatravel.util.Creator;

@Service
public class BookingService {
	
	@Autowired
	private RezervacijeRepository bookingRepository;
	
	@Autowired
	private SjRepository sJRepository;
	
	@Autowired
	private TypesRepository typesRepository;
	
	@Autowired
	private BookingClient bookingWsClient;
	
	@Autowired
	private AccomodationClient accomodationWsClient;
	
	@Autowired
	private AccomodationRatingClient accomodationRatingWsClient;

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
	
	public void overrideData(Collection<RezervacijaKorisnika> rezervacijaKorisnikaUpdated) {
		bookingRepository.overrideData(rezervacijaKorisnikaUpdated);
	}
	
	public void test() throws XMLDBException {
		List<TipSmestaja> tipSmestaja = new ArrayList<TipSmestaja>();
		tipSmestaja.add(Creator.createTipSmetaja(1l, "Resort"));
		tipSmestaja.add(Creator.createTipSmetaja(2l, "spa and fitness"));
		tipSmestaja.add(Creator.createTipSmetaja(3l, "prenociste"));
		
		typesRepository.overrideData(tipSmestaja);
		
		RezervacijaKorisnika rez = Creator.createRezervacija(1554382800000l, 1559826000000l, 1561053091417l, 30.0, 430.0, 1L, "POTVRDJENO", 1L);
		bookingWsClient.makeBooking(rez);
		
		accomodationRatingWsClient.answerMessage(Creator.createPoruka(1, 2, 2, "jflakjdfla", Creator.createXMLCalender(5)));
		
		SmestajniObjekat obj = Creator.createSmestajniObjekat(1, "Gold");
		SmestajnaJedinica jedinica = Creator.createSmestajnaJedinica(1, obj);
		
		
		accomodationWsClient.sendObject(obj);
		accomodationWsClient.sendUnit(jedinica);
	}

}
