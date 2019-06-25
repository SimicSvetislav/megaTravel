package com.project.megatravel.search.model.dto;

import java.util.List;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat.Slike;

public class ResultDTO {
	
	private SmestajnaJedinica jedinica;
	private List<Slike> slike;
	private Double ocena;
	private Integer kategorija;
	private String opis;
	private String nazivObj;
	private Double udaljenost;
	private String lokacija;

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	private Double cena; // TODO

	public ResultDTO() {
		
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Integer getKategorija() {
		return kategorija;
	}

	public void setKategorija(Integer kategorija) {
		this.kategorija = kategorija;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public SmestajnaJedinica getJedinica() {
		return jedinica;
	}

	public void setJedinica(SmestajnaJedinica jedinica) {
		this.jedinica = jedinica;
	}

	public List<Slike> getSlike() {
		return slike;
	}

	public void setSlike(List<Slike> slike) {
		this.slike = slike;
	}
	
	public String getNazivObj() {
		return nazivObj;
	}

	public void setNazivObj(String nazivObj) {
		this.nazivObj = nazivObj;
	}
	
	public Double getUdaljenost() {
		return udaljenost;
	}

	public void setUdaljenost(Double udaljenost) {
		this.udaljenost = udaljenost;
	}

}
