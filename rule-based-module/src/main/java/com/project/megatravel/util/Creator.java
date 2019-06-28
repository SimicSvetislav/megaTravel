package com.project.megatravel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.project.megatravel.model.accomodation.GeoDuzina;
import com.project.megatravel.model.accomodation.GeoSirina;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;

public final class Creator {
	
	private Creator() {
		
	}
	
	public static RezervacijaKorisnika createRezervacija(long id, double cena, String datum, String stanje) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        rez.setProcenatOtkazivanje(-1.0);
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
        rez.setProcenatOtkazivanje(-1.0);
        rez.setId(id);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        rez.setSmestaj(sj);
        
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
        rez.setProcenatOtkazivanje(-1.0);
        rez.setId(id);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        rez.setSmestaj(sj);
        rez.setOcena(ocena);
        
        try {
			rez.setDatumPocetka(sdf.parse(datumP));
			rez.setDatumZavrsetka(sdf.parse(datumZ));
			rez.setDatumRezervacije(sdf.parse(datumR));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return rez;

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
		
		l.setId(id);
		l.setNaziv(naziv);
		
		return l;
		
	}
	
	public static SmestajnaJedinica createSmestajnaJedinica(long id, SmestajniObjekat so) {
		
		SmestajnaJedinica j = new SmestajnaJedinica();
        
        j.setId(id);
        j.setSObjekat(so);
        
        return j;

	}
	
	public static SmestajnaJedinica createSmestajnaJedinica(long id, SmestajniObjekat so, Rejting r) {
		
		SmestajnaJedinica j = new SmestajnaJedinica();
        
        j.setId(id);
        j.setSObjekat(so);
        j.setRejting(r);
        
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
	
	public static Lokacija createLokacija(Long id, String naziv, double sirina, double duzina) {
		
		Lokacija lok = Creator.createLokacija(id, naziv);
        
        GeoSirina gsirina = new GeoSirina();
        gsirina.setStepeni(sirina);
        
        GeoDuzina gduzina = new GeoDuzina();
        gduzina.setStepeni(duzina);
        
        lok.setGeoSirina(gsirina);
        lok.setGeoDuzina(gduzina);
		
        return lok;
        
	}
	
	public static double distance2(Lokacija l1, Lokacija l2) {

		return distance(l1.getGeoSirina().getStepeni(), l2.getGeoSirina().getStepeni(),
				l1.getGeoDuzina().getStepeni(), l2.getGeoDuzina().getStepeni(), 0.0, 0.0);
	    
	}
	
	public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

	    final int R = 6371;

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}

	public static double test() {
		return 1;
	}
	
	public static double distance3(Lokacija l1, Lokacija l2) {

		double lat1 = l1.getGeoSirina().getStepeni();
		double lat2 = l2.getGeoSirina().getStepeni();
		double lon1 = l1.getGeoDuzina().getStepeni();
		double lon2 = l2.getGeoDuzina().getStepeni();
		double el1 = 0.0;
		double el2 = 0.0;
		
		final int R = 6371;

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	    
	}
	
}
