package com.project.megatravel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Polozaj;
import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;

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
        //rez.setSmestaj(sj.getId());
        rez.setSmestajnaJedinica(sj.getId());
        
        try {
			rez.setDatumPocetka(sdf.parse(datumP));
			rez.setDatumZavrsetka(sdf.parse(datumZ));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return rez;

	}
	
	public static RezervacijaKorisnika createRezervacija(double cena, int ocena, String datumR, String datumP, String datumZ, String stanje, SmestajnaJedinica sj) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        //rez.setProcenatOtkazivanje(-1.0);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        //rez.setSmestaj(sj.getId());
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
	
	public static RezervacijaKorisnika createRezervacija(double cena, int ocena, String datumR, String datumP, String datumZ, String stanje, SmestajnaJedinica sj, KrajnjiKorisnik kk) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        //rez.setProcenatOtkazivanje(-1.0);
        rez.setCenaSmestaja(cena);
        rez.setStanje(stanje);
        //rez.setSmestaj(sj);
        rez.setSmestajnaJedinica(sj.getId());
        //rez.setOcena(ocena);
        rez.setKorisnik(kk.getId());
        
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
        //o.setRejting(r);
        
        return o;

	}
	
	public static SmestajniObjekat createSmestajniObjekat(long id, String kat, Rejting r, Lokacija lokacija) {
		
        SmestajniObjekat o = new SmestajniObjekat();
        
        o.setId(id);
        o.setKategorija(kat);
        //o.setRejting(r);
        o.setLokacija(lokacija);
        
        return o;

	}
	
public static SmestajniObjekat createSmestajniObjekat(String kat) {
		
        SmestajniObjekat o = new SmestajniObjekat();
        
        
        o.setKategorija(kat);
        
        return o;

	}
	
	public static Lokacija createLokacija(long id, String naziv) {
		
		Lokacija l = new Lokacija();
		
		//l.setId(id);
		l.setNaziv(naziv);
		
		return l;
		
	}
	
	/*public static SmestajnaJedinica createSmestajnaJedinica(long id, SmestajniObjekat so) {
		
		SmestajnaJedinica j = new SmestajnaJedinica();
        
        j.setId(id);
        j.setSObjekat(so);
        
        return j;

	}*/
	
	public static SmestajnaJedinica createSmestajnaJedinica(Integer brkr, SmestajniObjekat so) {
		
		SmestajnaJedinica j = new SmestajnaJedinica();
        
		j.setBrojKreveta(brkr);
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
	
	/**
	 * Na osnovu validnog imena mesta, koje može biti kako na srpskom tako i na nekom drugom jeziku (proban engleski), 
	 * određuju se geografske koordinate tog mesta.
	 * @param locationName Naziv lokacije
	 * @return Dva double broja koji predstavljaju geografsku širinu, odnosno geografska dužinu u okviru objekta klase Polozaj. 
	 */
	public static Polozaj getCoordinates(String locationName) {
		
		Polozaj p = new Polozaj();
		
		final WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);	
		// Mora se dozvoliti da bi se dobili rezultati
		//webClient.getOptions().setJavaScriptEnabled(false);

		try  {
	
		    final HtmlPage page = webClient.getPage("https://www.latlong.net/");
			
		    //final HtmlForm form = page.getHtmlElementById("frmPlace");
	
		    //final HtmlSubmitInput button = page.getHtmlElementById("btnfind");
		    final HtmlButton button = page.getHtmlElementById("btnfind");
		    final HtmlTextInput textField = page.getHtmlElementById("place");
	
		    textField.setValueAttribute(locationName);
	
		    final HtmlPage resultPage = button.click();
		    
		    final HtmlTextInput latitudeField = resultPage.getHtmlElementById("lat");
		    final HtmlTextInput longitudeField = resultPage.getHtmlElementById("lng");
			
		    String latStr = latitudeField.getValueAttribute();
		    String lngStr = longitudeField.getValueAttribute();
		    
		    p.setGeoSirina(Double.parseDouble(latStr));
		    p.setGeoDuzina(Double.parseDouble(lngStr));
		    
		    System.out.println("Latitude: " + p.getGeoSirina());
		    System.out.println("Longitude: " + p.getGeoDuzina());
	    
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		    webClient.close();
		}
	    
		return p;
	}
	
	public static double distanceForLocations(Lokacija l1, Lokacija l2) {

		return distance(l1.getKoordinate().getGeoSirina(), l2.getKoordinate().getGeoSirina(),
				l1.getKoordinate().getGeoDuzina(), l2.getKoordinate().getGeoDuzina(), 0.0, 0.0);
	    
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

}
