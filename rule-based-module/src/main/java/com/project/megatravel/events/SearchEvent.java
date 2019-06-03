package com.project.megatravel.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import com.project.megatravel.model.accomodation.Lokacija;

@Role(Role.Type.EVENT)
@Expires("10m")
public class SearchEvent implements Serializable {

	private Lokacija lokacija;
	private Date pocetak;
	private Date kraj; 
	
	public SearchEvent() {
	
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
	
}
