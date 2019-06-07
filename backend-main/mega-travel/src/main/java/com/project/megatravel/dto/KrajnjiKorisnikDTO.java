package com.project.megatravel.dto;

public class KrajnjiKorisnikDTO {
	
	private String datumRegistracije;
	private String kategorija;
	private Long id;
	
	public KrajnjiKorisnikDTO() {
	
	}

	public String getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(String datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
