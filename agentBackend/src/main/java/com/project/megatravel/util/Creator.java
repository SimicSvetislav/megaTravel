package com.project.megatravel.util;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.project.megatravel.model.accomodation.Cenovnik;
import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.model.reservations.Komentar;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.AgentKredencijali;
import com.project.megatravel.model.users.KrajnjiKorisnik;



public final class Creator {
	
	

	
	private Creator() {
		
	}
	
	public static RezervacijaKorisnika createRezervacija(long datumRez, long datumPoc, long datumZav, double popust, double cena, long korisnik, String stanje, long soba) {
		
		RezervacijaKorisnika rez = new RezervacijaKorisnika();
		rez.setDatumRezervacije(new Date(datumRez));
		rez.setDatumPocetka(new Date(datumPoc));
		rez.setDatumZavrsetka(new Date(datumZav));
		rez.setPopust(popust);
		rez.setCenaSmestaja(cena);
		rez.setKorisnik(korisnik);
		rez.setStanje(stanje);
		rez.setSmestajnaJedinica(soba);
        
        return rez;

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
        ///o.setRejting(r);
        
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
	
	public static DodatnaUsluga createDodatnaUsluge(Long id, String ime, Double cena, String jedinaPlacanja) {
		DodatnaUsluga rez = new DodatnaUsluga();
		rez.setId(id);
		rez.setIme(ime);
		rez.setCena(cena);
		rez.setJedinicaPlacanja(jedinaPlacanja);
		
		return rez;
	}
	
	public static TipSmestaja createTipSmetaja(Long id, Object naziv) {
		TipSmestaja  rez = new TipSmestaja();
		rez.setId(id);
		rez.setNaziv(naziv);
		return rez;
	}
	
	public static KategorijaSm createKategorijaSm(Long id, Integer value) {
		KategorijaSm rez = new KategorijaSm();
		
		rez.setId(id);
		rez.setZvezdice(value);
		return rez;
	}
	
	public static Cenovnik createCenovnik(Long pocetak, Long kraj, Long cena, Long smestaj) {
		Cenovnik rez = new Cenovnik();
		rez.setPocetak(new Date(pocetak));   
		rez.setKraj(new Date(kraj));
		rez.setCena(cena);
		rez.setSmestaj(smestaj);
		
		return rez;
	}
	
	public static Otkazivanje createOtkazivanje(BigInteger brojDana, boolean dozvoljeno) {
		Otkazivanje otkazivanje = new Otkazivanje();
		otkazivanje.setBrojDana(brojDana);
		otkazivanje.setDozvoljeno(dozvoljeno);
		
		return otkazivanje;
	}
	
	public static Poruka createPoruka(long id, long sender, long receiver, String text, XMLGregorianCalendar timestamp) {
		Poruka poruka = new Poruka();
		poruka.setId(id);
		poruka.setSender(sender);
		poruka.setReceiver(receiver);
		poruka.setText(text);
		poruka.setTimestamp(timestamp);
		
		return poruka;
	}
	
	public static Komentar createKomentar(boolean odobren, String tekst, Object prilog) {
		Komentar komentar = new Komentar();
		
		komentar.setOdobren(odobren);
		komentar.setTekst(tekst);
		komentar.setPrilog(prilog);
		
		return komentar;
	}
	
	public static XMLGregorianCalendar createXMLCalender(int dayOfMonth) {
		GregorianCalendar c = new GregorianCalendar(2019, 06, dayOfMonth, 15, 00);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static Agent createAgent(String ime, String prezime, String email, String username, String adresa, String telefon, long pib, String pass) {
		Agent agent = new Agent();
		
		agent.setAdresa(adresa);
		agent.setEmail(email);
		agent.setIme(ime);
		agent.setKorisnickoIme(username);
		agent.setPoslovniMaticniBroj(pib);
		agent.setPrezime(prezime);
		agent.setTelefon(telefon);
				
		agent.setSifra(pass);
		
		return agent;
	}
	
	public static AgentKredencijali createKredencijali(String username, String password) {
		AgentKredencijali agentKredencijali = new AgentKredencijali();
		agentKredencijali.setUsername(username);
		agentKredencijali.setPassword(password);
		
		return agentKredencijali;
	}

	/*public static KrajnjiKorisnikDTO createKrajnjiKorisnikDTO(long id, String kat, String datumR) {
		
        
        KrajnjiKorisnikDTO k = new KrajnjiKorisnikDTO();
        
        k.setId(id);
        k.setKategorija(kat);
        k.setDatumRegistracije(datumR);
        
        return k;
	}*/

}
