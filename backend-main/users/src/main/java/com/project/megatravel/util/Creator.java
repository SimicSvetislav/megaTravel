package com.project.megatravel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.Kupon;
import com.project.megatravel.model.users.KrajnjiKorisnik.Rezervacije;

public final class Creator {
	
	public Creator() {
		
	}
	
	public static RezervacijaKorisnika createRezervacija(long id, double cena, String datum, String stanje) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        //rez.setProcenatOtkazivanje(-1.0);
        rez.setId(id);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        
        try {
			rez.setDatumPocetka(sdf.parse(datum));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return rez;

	}
	
	public static RezervacijaKorisnika createRezervacija(String datumP, String datumZ) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        
        try {
			rez.setDatumPocetka(sdf.parse(datumP));
			rez.setDatumZavrsetka(sdf.parse(datumZ));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return rez;

	}
	
	public static RezervacijaKorisnika createRezervacija(long id, double cena, String datumP, String datumZ, String stanje, SmestajnaJedinica sj) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        //rez.setProcenatOtkazivanje(-1.0);
        rez.setId(id);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        rez.setSmestajnaJedinica(sj.getId());
        
        try {
			rez.setDatumPocetka(sdf.parse(datumP));
			rez.setDatumZavrsetka(sdf.parse(datumZ));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return rez;

	}
	
	public static RezervacijaKorisnika createRezervacija(long id, double cena, int ocena, String datumR, String datumP, String datumZ, String stanje, SmestajnaJedinica sj) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        //rez.setProcenatOtkazivanje(-1.0);
        rez.setId(id);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        rez.setSmestajnaJedinica(sj.getId());
        //rez.setOcena(ocena);
        
        try {
			rez.setDatumPocetka(sdf.parse(datumP));
			rez.setDatumZavrsetka(sdf.parse(datumZ));
			rez.setDatumRezervacije(sdf.parse(datumR));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return rez;

	}
	
	public static KrajnjiKorisnik createKrajnjiKorisnik(String sifra,
			String email, String adresa, String telefon,
			String ime, String prezime) {
		
		KrajnjiKorisnik k = new KrajnjiKorisnik();
		
		k.setSifra(sifra);
		k.setEmail(email);
		k.setAdresa(adresa);
		k.setTelefon(telefon);
		k.setIme(ime);
		k.setPrezime(prezime);
		
		return k;
	}
	
	public static Administrator createAdmin(String sifra,
			String email, String adresa, String telefon,
			String ime, String prezime) {
		
		Administrator k = new Administrator();
		
		k.setSifra(sifra);
		k.setEmail(email);
		k.setAdresa(adresa);
		k.setTelefon(telefon);
		k.setIme(ime);
		k.setPrezime(prezime);
		
		return k;
	}
	
	public static Agent createAgent(String sifra,
			String email, String adresa, String telefon) {
		
		Agent k = new Agent();
		
		k.setSifra(sifra);
		k.setEmail(email);
		k.setAdresa(adresa);
		k.setTelefon(telefon);
		
		return k;
	}
	
	
	public static KrajnjiKorisnik createKrajnjiKorisnik(long id, String kat, String datumReg) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        
        k.setId(id);
        k.setKategorija(kat);
        
        try {
			k.setDatumRegistracije(sdf.parse(datumReg));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return k;

	}
	
	public static SmestajniObjekat createSmestajniObjekat(long id, String kat) {
		
        SmestajniObjekat o = new SmestajniObjekat();
        
        o.setId(id);
        o.setKategorija(kat);
        
        return o;

	}
	
	public static SmestajniObjekat createSmestajniObjekat(long id, String kat, Rejting r) {
		
        SmestajniObjekat o = new SmestajniObjekat();
        
        o.setId(id);
        o.setKategorija(kat);
        o.setRejting(r);
        
        return o;

	}
	
	public static SmestajniObjekat createSmestajniObjekat(long id, String kat, Rejting r, Lokacija lokacija) {
		
        SmestajniObjekat o = new SmestajniObjekat();
        
        o.setId(id);
        o.setKategorija(kat);
        o.setRejting(r);
        o.setLokacija(lokacija);
        
        return o;

	}
	
	public static Lokacija createLokacija(long id, String naziv) {
		
		Lokacija l = new Lokacija();
		
		//l.setId(id);
		l.setNaziv(naziv);
		
		return l;
		
	}
	
	public static SmestajnaJedinica createSmestajnaJedinica(long id, SmestajniObjekat so) {
		
		SmestajnaJedinica j = new SmestajnaJedinica();
        
        j.setId(id);
        j.setSObjekat(so.getId());
        
        return j;

	}
	
	public static SmestajnaJedinica createSmestajnaJedinica(long id, SmestajniObjekat so, Rejting r) {
		
		SmestajnaJedinica j = new SmestajnaJedinica();
        
        j.setId(id);
        j.setSObjekat(so.getId());
        //j.setRejting(r);
        
        return j;

	}
	
	public static Rejting createRejting(long bo, long so) {
		
		Rejting r = new Rejting();
		
		r.setBrojOcena(bo);
		r.setSumaOcena(so);
		
		return r;	
	}

	public static RezervacijaKorisnika createRezervacija(Date d1, Date d2) {
			RezervacijaKorisnika rez = new RezervacijaKorisnika();
        
			rez.setDatumPocetka((d1));
			rez.setDatumZavrsetka((d2));
			
			return rez;
	}

	/*public static KrajnjiKorisnikDTO createKrajnjiKorisnikDTO(long id, String kat, String datumR) {
		
        
        KrajnjiKorisnikDTO k = new KrajnjiKorisnikDTO();
        
        k.setId(id);
        k.setKategorija(kat);
        k.setDatumRegistracije(datumR);
        
        return k;
	}*/

}
