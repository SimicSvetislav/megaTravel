package com.project.megatravel.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.Kupon;

public class App {

	public static void main(String[] args) {
		System.out.println("Rule based module started successfully.");
		
		//testRecommendation();
		//testDiscount();
		//testCancelling();
		//testClient();
		testAccomodation();
		//testDiscount2();
		
	}
	
	public static void testDiscount2 () {
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        
        
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
        
	}
	
	public static void testAccomodation() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("smestaj").setFocus();
        
        // BRONZE
        SmestajniObjekat s = createSmestajniObjekat(1, "NA", createRejting(0L, 0L));
        kSession.insert(s);
        
        SmestajniObjekat s2 = createSmestajniObjekat(2, "NA", createRejting(2L, 3L));
        kSession.insert(s2);
        
        SmestajniObjekat s3 = createSmestajniObjekat(3, "NA", createRejting(10L, 25L));
        kSession.insert(s3);
        
        // SILVER
        SmestajniObjekat s4 = createSmestajniObjekat(4, "NA", createRejting(10L, 29L));
        kSession.insert(s4);
       
        SmestajnaJedinica j1 = createSmestajnaJedinica(1L, s4);
        s4.getSmestajneJedinice().add(j1);
        SmestajnaJedinica j2 = createSmestajnaJedinica(2L, s4);
        s4.getSmestajneJedinice().add(j2);
        
        RezervacijaKorisnika rez1 = createRezervacija(1, 100, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez2 = createRezervacija(2, 100, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j2);
        
        RezervacijaKorisnika rez3 = createRezervacija(3, 100, 0, "01/09/2018", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez4 = createRezervacija(4, 500, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j2);
        
        RezervacijaKorisnika rez5 = createRezervacija(5, 800, 0, "09/09/2018", "10/10/2018", "09/04/2019", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez6 = createRezervacija(6, 800, 0, "01/04/2019", "10/10/2017", "19/09/2017", "REALIZOVANO", 
        		j1);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        
        // GOLD
        SmestajniObjekat s5 = createSmestajniObjekat(5, "NA", createRejting(10L, 39L));
        kSession.insert(s5);
        
        SmestajnaJedinica j3 = createSmestajnaJedinica(3L, s5);
        s5.getSmestajneJedinice().add(j3);
        SmestajnaJedinica j4 = createSmestajnaJedinica(4L, s5);
        s5.getSmestajneJedinice().add(j4);
        
        RezervacijaKorisnika rez7 = createRezervacija(7, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j3);
        
        RezervacijaKorisnika rez8 = createRezervacija(8, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j3);
        
        RezervacijaKorisnika rez9 = createRezervacija(9, 100, 5, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j4);
        
        RezervacijaKorisnika rez10 = createRezervacija(10, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j4);
        
        RezervacijaKorisnika rez11 = createRezervacija(10, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j4);
        
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rez9);
        kSession.insert(rez10);
        kSession.insert(rez11);
        
        // NOT GOLD - poslednja ocena manja od 3
        SmestajniObjekat s6 = createSmestajniObjekat(6, "NA", createRejting(10L, 39L));
        kSession.insert(s6);
        
        SmestajnaJedinica j5 = createSmestajnaJedinica(5L, s6);
        s6.getSmestajneJedinice().add(j5);
        SmestajnaJedinica j6 = createSmestajnaJedinica(6L, s6);
        s6.getSmestajneJedinice().add(j6);
        
        RezervacijaKorisnika rez12 = createRezervacija(12, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j5);
        
        RezervacijaKorisnika rez13 = createRezervacija(13, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j6);
        
        RezervacijaKorisnika rez14 = createRezervacija(14, 100, 2, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j6);
        
        RezervacijaKorisnika rez15 = createRezervacija(15, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j5);
        
        RezervacijaKorisnika rez16 = createRezervacija(16, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j6);
        
        kSession.insert(rez12);
        kSession.insert(rez13);
        kSession.insert(rez14);
        kSession.insert(rez15);
        kSession.insert(rez16);
        
        // PLATINUM
        SmestajniObjekat s7 = createSmestajniObjekat(7, "GOLD", createRejting(10L, 39L));
        kSession.insert(s7);
        
        SmestajnaJedinica j7 = createSmestajnaJedinica(7L, s7);
        s7.getSmestajneJedinice().add(j7);
        SmestajnaJedinica j8 = createSmestajnaJedinica(8L, s7);
        s7.getSmestajneJedinice().add(j8);
        
        RezervacijaKorisnika rez17 = createRezervacija(17, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez18 = createRezervacija(18, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez19 = createRezervacija(19, 100, 2, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez20 = createRezervacija(20, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j8);
        
        RezervacijaKorisnika rez21 = createRezervacija(21, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j8);
        
        kSession.insert(rez17);
        kSession.insert(rez18);
        kSession.insert(rez19);
        kSession.insert(rez20);
        kSession.insert(rez21);
        
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
        
	}
	
	public static void testClient() {
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("klijent").setFocus();
        
        kSession.setGlobal("kuponi", new ArrayList<>());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Rezervacije
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        rez.setProcenatOtkazivanje(-1.0);
        rez.setId(1L);
        rez.setCenaSmestaja(400);
        rez.setStanje("REALIZOVANO");
        
        try {
			rez.setDatumPocetka(sdf.parse("17/04/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
        rez2.setProcenatOtkazivanje(-1.0);
        rez2.setId(2L);
        rez2.setCenaSmestaja(100);
        rez2.setStanje("REALIZOVANO");
        
        try {
			rez2.setDatumPocetka(sdf.parse("18/11/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
        rez3.setProcenatOtkazivanje(-1.0);
        rez3.setId(3L);
        rez3.setCenaSmestaja(450);
        rez3.setStanje("REALIZOVANO");
        
        try {
			rez3.setDatumPocetka(sdf.parse("20/04/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        RezervacijaKorisnika rez4 = new RezervacijaKorisnika();
        rez4.setProcenatOtkazivanje(-1.0);
        rez4.setId(4L);
        rez4.setCenaSmestaja(310);
        rez4.setStanje("REALIZOVANO");
        
        try {
			rez4.setDatumPocetka(sdf.parse("03/05/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        // END: Rezervacije
        
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setId(1L);
        k.setKategorija("NA");
        k.getRezervacije().add(rez);
        k.getRezervacije().add(rez2);
        
        try {
			k.setDatumRegistracije(sdf.parse("10/04/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        KrajnjiKorisnik k2 = new KrajnjiKorisnik();
        k2.setId(2L);
        k2.setKategorija("NA");
        k2.getRezervacije().add(rez2);
        k2.getRezervacije().add(rez2);
        
        try {
			k2.setDatumRegistracije(sdf.parse("10/04/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        KrajnjiKorisnik k3 = new KrajnjiKorisnik();
        k3.setId(3L);
        k3.setKategorija("NA");
        k3.getRezervacije().add(rez2);
        
        try {
			k3.setDatumRegistracije(sdf.parse("10/04/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        KrajnjiKorisnik k4 = new KrajnjiKorisnik();
        k4.setId(4L);
        k4.setKategorija("NA");
        k4.getRezervacije().add(rez4);
        
        try {
			k4.setDatumRegistracije(sdf.parse("10/04/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        KrajnjiKorisnik k5 = createKrajnjiKorisnik(5, "NA", "10/04/2019");
        k5.getRezervacije().add(rez3);
        
        KrajnjiKorisnik k6 = createKrajnjiKorisnik(6, "NA", "10/04/2019");
        k6.getRezervacije().add(rez3);
        k6.getRezervacije().add(rez2);
        k6.getRezervacije().add(rez3);
        k6.getRezervacije().add(rez2);
        
        RezervacijaKorisnika rez11 = createRezervacija(11, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		createSmestajnaJedinica(11, createSmestajniObjekat(11, "GOLD")));
        
        RezervacijaKorisnika rez12 = createRezervacija(12, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		createSmestajnaJedinica(12, createSmestajniObjekat(12, "PLATINUM")));
        
        RezervacijaKorisnika rez13 = createRezervacija(13, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		createSmestajnaJedinica(13, createSmestajniObjekat(13, "SILVER")));
        
        RezervacijaKorisnika rez14 = createRezervacija(14, 500, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		createSmestajnaJedinica(14, createSmestajniObjekat(14, "BRONZE")));
        
        RezervacijaKorisnika rez15 = createRezervacija(15, 800, "09/09/2018", "09/04/2019", "REALIZOVANO", 
        		createSmestajnaJedinica(15, createSmestajniObjekat(15, "GOLD")));
        
        RezervacijaKorisnika rez16 = createRezervacija(16, 800, "09/09/2017", "19/09/2017", "REALIZOVANO", 
        		createSmestajnaJedinica(15, createSmestajniObjekat(16, "GOLD")));
        
        KrajnjiKorisnik k7 = createKrajnjiKorisnik(7, "NA", "10/04/2019");
        k7.getRezervacije().add(rez11);
        k7.getRezervacije().add(rez12);
        k7.getRezervacije().add(rez13);
        k7.getRezervacije().add(rez13);
        k7.getRezervacije().add(rez13);
        k7.getRezervacije().add(rez12);
        k7.getRezervacije().add(rez12);
        k7.getRezervacije().add(rez11);
        k7.getRezervacije().add(rez11);
        k7.getRezervacije().add(rez11);
        
        // Deset rezervacija u SILVER objektima
        KrajnjiKorisnik k8 = createKrajnjiKorisnik(8, "NA", "10/04/2019");
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        k8.getRezervacije().add(rez13);
        
        // SILVER
        KrajnjiKorisnik k9 = createKrajnjiKorisnik(9, "NA", "10/04/2019");
        k9.getRezervacije().add(rez13);
        k9.getRezervacije().add(rez15);
        //k9.getRezervacije().add(rez15);
        k9.getRezervacije().add(rez14);
        k9.getRezervacije().add(rez14);
        
        // Stare rezervacije
        KrajnjiKorisnik k10 = createKrajnjiKorisnik(10, "NA", "10/04/2019");
        k10.getRezervacije().add(rez13);
        k10.getRezervacije().add(rez15);
        k10.getRezervacije().add(rez15);
        k10.getRezervacije().add(rez16);
        k10.getRezervacije().add(rez16);
        
        KrajnjiKorisnik k11 = createKrajnjiKorisnik(11, "NA", "10/04/2019");
        k11.getRezervacije().add(rez13);
        k11.getRezervacije().add(rez15);
        k11.getRezervacije().add(rez15);
        k11.getRezervacije().add(rez14);
        k11.getRezervacije().add(rez16);
        
        /*kSession.insert(k);
        kSession.insert(k2);
        kSession.insert(k3);
        kSession.insert(k4);
        kSession.insert(k5);
        kSession.insert(k6);
        kSession.insert(k7);
        kSession.insert(k8);
        kSession.insert(k9);*/
        kSession.insert(k10);
        kSession.insert(k11);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("unchecked")
		List<Kupon> kuponi = (List<Kupon>)kSession.getGlobal("kuponi");
        
        System.out.println( "Number of Rules executed = " + fired );
        System.out.println( "Kreirano kupona " +  kuponi.size());
	}

	
	public static void testCancelling() {
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        rez.setProcenatOtkazivanje(-1.0);
        rez.setId(1L);
        
        try {
			rez.setDatumPocetka(sdf.parse("17/04/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
        rez2.setProcenatOtkazivanje(-1.0);
        rez2.setId(2L);
        
        try {
			rez2.setDatumPocetka(sdf.parse("18/11/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
        rez3.setProcenatOtkazivanje(-1.0);
        rez3.setId(3L);
        
        try {
			rez3.setDatumPocetka(sdf.parse("20/04/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        RezervacijaKorisnika rez4 = new RezervacijaKorisnika();
        rez4.setProcenatOtkazivanje(-1.0);
        rez4.setId(4L);
        
        try {
			rez4.setDatumPocetka(sdf.parse("03/05/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
           
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        //kSession.fireUntilHalt();
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
        
        
	}
	
	public static void testRecommendation() {
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("PLATINUM");
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
    
	}
	
	public static void testDiscount() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        RezervacijaKorisnika rez = new RezervacijaKorisnika();
        rez.setPopust(0.0);
        
        try {
			rez.setDatumRezervacije(sdf.parse("24/01/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        rez.setId(1L);
        
        RezervacijaKorisnika rez2 = new RezervacijaKorisnika();
        rez2.setPopust(0.0);
        
        try {
			rez2.setDatumRezervacije(sdf.parse("18/11/2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        rez2.setId(2L);
        
        RezervacijaKorisnika rez3 = new RezervacijaKorisnika();
        
        rez3.setDatumRezervacije(new Date());
        rez3.setPopust(0.0);
        
        rez3.setId(3L);
           
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        int fired = kSession.fireAllRules();
        
        System.out.println();
        System.out.println("Popust rez : " + rez.getPopust());
        System.out.println("Popust rez2 : " + rez2.getPopust());
        System.out.println("Popust rez3 : " + rez3.getPopust());
        
        System.out.println( "Number of Rules executed = " + fired );
        
		
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
	
}

