package com.project.megatravel.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;

@Role(Role.Type.EVENT)
@Expires("10m")
public class CancellationEvent {

	private RezervacijaKorisnika rk;
	
	public CancellationEvent() {
	
	}
	
	public CancellationEvent(RezervacijaKorisnika rk) {
		this.rk = rk;
	}

	public RezervacijaKorisnika getRk() {
		return rk;
	}

	public void setRk(RezervacijaKorisnika rk) {
		this.rk = rk;
	}
	
}
