package com.project.megatravel.dto;

import java.util.Date;

import com.project.megatravel.model.reservations.RezervacijaKorisnika;



public class ReservationDTO {

	private Date datumPocetka;	   
	private Date datumZavrsetka;
	private double popust;    
	private Long id;
	private Double cenaSmestaja;
    private String stanje;
    private Long korisnik;
    private boolean ocenjeno;
    private Date datumRezervacije;
    
    private SmestajnaJedinicaDTO smestajnaJedinicaDTO;
    private Long smestajnaJedinica;

    //koje je usluge odabrabrana pri rezervaciji?
    
	public ReservationDTO(Date datumPocetka, Date datumZavrsetka, double popust, Long smestajnaJedinica, SmestajnaJedinicaDTO smestajnaJedinicaDTO, Long id,
			Double cenaSmestaja, String stanje, Long korisnik, boolean ocenjeno, Date datumRezervacije) {
		super();
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.popust = popust;
		this.smestajnaJedinicaDTO = smestajnaJedinicaDTO;
		this.smestajnaJedinica = smestajnaJedinica;
		this.id = id;
		this.cenaSmestaja = cenaSmestaja;
		this.stanje = stanje;
		this.korisnik = korisnik;
		this.ocenjeno = ocenjeno;
		this.datumRezervacije = datumRezervacije;
	}
	
	public ReservationDTO(RezervacijaKorisnika rez, SmestajnaJedinicaDTO smestaj) {
		this(rez.getDatumPocetka(),rez.getDatumZavrsetka(),rez.getPopust(),rez.getId(), smestaj, rez.getId(), rez.getCenaSmestaja(),
				rez.getStanje(),rez.getKorisnik(), false, rez.getDatumRezervacije());
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

	public SmestajnaJedinicaDTO getSmestajnaJedinicaDTO() {
		return smestajnaJedinicaDTO;
	}

	public void setSmestajnaJedinicaDTO(SmestajnaJedinicaDTO smestajnaJedinicaDTO) {
		this.smestajnaJedinicaDTO = smestajnaJedinicaDTO;
	}

	public Long getSmestajnaJedinica() {
		return smestajnaJedinica;
	}

	public void setSmestajnaJedinica(Long smestajnaJedinica) {
		this.smestajnaJedinica = smestajnaJedinica;
	}
    
    
	
}
