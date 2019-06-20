package com.project.megatravel.model.dto;

import java.util.Date;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;



public class ReservationDTO {

	protected Date datumPocetka;	   
	protected Date datumZavrsetka;
	protected double popust;    
	protected Long smestajnaJedinica;
	protected Long id;
	protected Double cenaSmestaja;
    protected String stanje;
    protected Long korisnik;
    protected boolean ocenjeno;
    protected Date datumRezervacije;
    
   
    
	public ReservationDTO(Date datumPocetka, Date datumZavrsetka, double popust, Long smestajnaJedinica, Long id,
			Double cenaSmestaja, String stanje, Long korisnik, boolean ocenjeno, Date datumRezervacije) {
		super();
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.popust = popust;
		this.smestajnaJedinica = smestajnaJedinica;
		this.id = id;
		this.cenaSmestaja = cenaSmestaja;
		this.stanje = stanje;
		this.korisnik = korisnik;
		this.ocenjeno = ocenjeno;
		this.datumRezervacije = datumRezervacije;
	}
	
	public ReservationDTO(RezervacijaKorisnika rez) {
		this(rez.getDatumPocetka(),rez.getDatumZavrsetka(),rez.getPopust(), rez.getSmestajnaJedinica(),rez.getId(),rez.getCenaSmestaja(),
				rez.getStanje(),rez.getKorisnik(),false,rez.getDatumRezervacije());
	}


	public Date getDatumPocetka() {
		return datumPocetka;
	}


	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}


	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}


	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}


	public double getPopust() {
		return popust;
	}


	public void setPopust(double popust) {
		this.popust = popust;
	}


	public Long getSmestajnaJedinica() {
		return smestajnaJedinica;
	}


	public void setSmestajnaJedinica(Long smestajnaJedinica) {
		this.smestajnaJedinica = smestajnaJedinica;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getCenaSmestaja() {
		return cenaSmestaja;
	}


	public void setCenaSmestaja(Double cenaSmestaja) {
		this.cenaSmestaja = cenaSmestaja;
	}


	public String getStanje() {
		return stanje;
	}


	public void setStanje(String stanje) {
		this.stanje = stanje;
	}


	public Long getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(Long korisnik) {
		this.korisnik = korisnik;
	}


	public boolean isOcenjeno() {
		return ocenjeno;
	}


	public void setOcenjeno(boolean ocenjeno) {
		this.ocenjeno = ocenjeno;
	}


	public Date getDatumRezervacije() {
		return datumRezervacije;
	}


	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}
    
    
	
}
