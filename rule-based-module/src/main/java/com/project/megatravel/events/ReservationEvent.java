package com.project.megatravel.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@Role(Role.Type.EVENT)
@Expires("3h")
public class ReservationEvent implements Serializable {

	private Lokacija lokacija;
	private RezervacijaKorisnika rk;
	
	public ReservationEvent() {
		super();
	}
	
	public ReservationEvent(Lokacija l) { 
		lokacija = l;
	}
	
	public ReservationEvent(Lokacija l, RezervacijaKorisnika rk) { 
		lokacija = l;
		this.rk = rk;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public RezervacijaKorisnika getRk() {
		return rk;
	}

	public void setRk(RezervacijaKorisnika rk) {
		this.rk = rk;
	}

	
	
}
