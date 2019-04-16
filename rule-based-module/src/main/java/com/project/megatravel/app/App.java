package com.project.megatravel.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.Rejting;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.Kupon;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.util.Creator;


public class App {

	public static void main(String[] args) {
		System.out.println("Rule based module started successfully.");
		
		//testRecommendation(); C
		//testDiscount(); 		C
		//testCancelling();		C
		//testClient(); 		C
		//testAccomodation();	C
		//testDiscount2();
		testDiscount3();
		//testCancelling2();	C
		//testCancelling3();	C
	}
	
	public static void testDiscount3() {
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("popusti2").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        Lokacija l2 = Creator.createLokacija(2, "Novi Sad");
        Lokacija l3 = Creator.createLokacija(3, "Novi Sad");
        Lokacija l4 = Creator.createLokacija(4, "Novi Sad");
        Lokacija l5 = Creator.createLokacija(5, "Novi Sad");
        Lokacija l6 = Creator.createLokacija(6, "Novi Sad");
        Lokacija l7 = Creator.createLokacija(7, "Novi Sad");
        Lokacija l8 = Creator.createLokacija(8, "Novi Sad");
        
        Rejting r = Creator.createRejting(2, 9);
        Rejting r0 = Creator.createRejting(0, 0);
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "BRONZE", r, l);
        
        SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "SILVER", r, l2);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "GOLD", r, l3);
        
        SmestajniObjekat so4 = Creator.createSmestajniObjekat(4, "SILVER", r, l4);
        
        SmestajniObjekat so5 = Creator.createSmestajniObjekat(5, "SILVER", r, l5);
        
        SmestajniObjekat so6 = Creator.createSmestajniObjekat(6, "BRONZE", r0, l6);
        
        SmestajniObjekat so7 = Creator.createSmestajniObjekat(7, "GOLD", r0, l7);
        
        SmestajniObjekat so8 = Creator.createSmestajniObjekat(8, "GOLD", r0, l8);
        
        Agent a1 = new Agent();
        a1.setId(1L);
        
        Agent a2 = new Agent();
        a2.setId(2L);
        
        Agent a3 = new Agent();
        a3.setId(3L);
        
        so.setAgent(a1);
        so2.setAgent(a2);
        so3.setAgent(a3);
        so4.setAgent(a1);
        so5.setAgent(a2);
        so6.setAgent(a3);
        
        
        SmestajnaJedinica j1 = Creator.createSmestajnaJedinica(1L, so);
        so.getSmestajneJedinice().add(j1);
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2L, so2);
        so2.getSmestajneJedinice().add(j2);
        
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3L, so3);
        so3.getSmestajneJedinice().add(j3);
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4L, so4);
        so4.getSmestajneJedinice().add(j4);
        
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5L, so5);
        so5.getSmestajneJedinice().add(j5);
        
        SmestajnaJedinica j6 = Creator.createSmestajnaJedinica(6L, so6);
        so6.getSmestajneJedinice().add(j6);
        
        SmestajnaJedinica j7 = Creator.createSmestajnaJedinica(7L, so7);
        so7.getSmestajneJedinice().add(j7);
        
        SmestajnaJedinica j8 = Creator.createSmestajnaJedinica(8L, so8);
        so8.getSmestajneJedinice().add(j8);
        
        KrajnjiKorisnik k = Creator.createKrajnjiKorisnik(1, "SILVER", "10/10/2018");
        
        RezervacijaKorisnika rez1 = Creator.createRezervacija(11, 100, 3, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j1);
        rez1.setPopust(0.0);
        rez1.setKorisnik(k);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(12, 100, 2, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j2);
        rez2.setPopust(0.0);
        rez2.setKorisnik(k);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(13, 100, 4, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j3);
        rez3.setPopust(0.0);
        rez3.setKorisnik(k);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(14, 500, 5, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j4);
        rez4.setPopust(0.0);
        rez4.setKorisnik(k);
        
        RezervacijaKorisnika rez5 = Creator.createRezervacija(15, 800, 3, "09/09/2018", "09/04/2019", "09/04/2019", "REALIZOVANO", 
        		j5);
        rez5.setPopust(0.0);
        rez5.setKorisnik(k);
        
        // Nije u poslednjih godinu dana
        RezervacijaKorisnika rez6 = Creator.createRezervacija(16, 800, 4, "06/04/2019", "09/04/2019", "19/09/2017", "REALIZOVANO", 
        		j6);
        rez6.setPopust(0.0);
        rez6.setKorisnik(k);
        
        RezervacijaKorisnika rezNew = Creator.createRezervacija(20, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REALIZOVANO", 
        		j2);
        rezNew.setPopust(0.0);
        rezNew.setKorisnik(k);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(17, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REALIZOVANO", 
        		j7);
        rez7.setPopust(0.0);
        rez7.setKorisnik(k);
        
        RezervacijaKorisnika rez8 = Creator.createRezervacija(18, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REZERVISANO", 
        		j8);
        rez8.setPopust(0.0);
        rez8.setKorisnik(k);
        
        k.getRezervacije().add(rez1);
        k.getRezervacije().add(rez2);
        k.getRezervacije().add(rez3);
        k.getRezervacije().add(rez4);
        k.getRezervacije().add(rez5);
        k.getRezervacije().add(rez6);
        k.getRezervacije().add(rez7);
        k.getRezervacije().add(rez8);
        
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        kSession.insert(so6);
        kSession.insert(so7);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rezNew);
        
        kSession.insert(l);
        
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
	}
	
	public static void testCancelling3() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j = Creator.createSmestajnaJedinica(1, so, Creator.createRejting(2, 9));
        
        so.getSmestajneJedinice().add(j);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "01/01/2019", "05/05/2019", "10/05/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "01/05/2019", "04/05/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "04/01/2019", "11/05/2019", "15/05/2019", "REALIZOVANO", j);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 100, 0, "04/01/2019", "05/06/2019", "15/06/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 100, 0, "04/01/2019", "01/06/2019", "04/06/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 100, 0, "04/01/2019", "17/06/2019", "21/06/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        kSession.insert(rez7);
        
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
		
	}
	
	public static void testCancelling2() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("otkazivanje").setFocus();
        
        //kSession.setGlobal("procenat", 0);
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j = Creator.createSmestajnaJedinica(1, so, Creator.createRejting(2, 9));
        
        so.getSmestajneJedinice().add(j);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1, 100, 0, "04/04/2019", "10/04/2019", "15/04/2019", "REZERVISANO", j);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez3 = Creator.createRezervacija(2, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j);
        RezervacijaKorisnika rez4 = Creator.createRezervacija(2, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j);
        RezervacijaKorisnika rez5 = Creator.createRezervacija(2, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j);
        
        
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        
        SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2, so2, Creator.createRejting(2, 9));
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3, so2, Creator.createRejting(2, 9));
        
        so2.getSmestajneJedinice().add(j2);
        so2.getSmestajneJedinice().add(j3);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 100, 0, "04/04/2019", "10/04/2019", "15/04/2019", "REZERVISANO", j2);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j2);
        RezervacijaKorisnika rez8 = Creator.createRezervacija(8, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j3);
        RezervacijaKorisnika rez9 = Creator.createRezervacija(9, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j3);
        RezervacijaKorisnika rez10 = Creator.createRezervacija(10, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j2);
        
        
        kSession.insert(rez6);
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rez9);
        kSession.insert(rez10);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "NA", Creator.createRejting(2, 9));
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4, so3, Creator.createRejting(2, 9));
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5, so3, Creator.createRejting(2, 9));
        
        so3.getSmestajneJedinice().add(j4);
        so3.getSmestajneJedinice().add(j5);
        
        RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, 0, "04/04/2019", "10/04/2019", "15/04/2019", "REZERVISANO", j4);
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j5);
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, 0, "04/01/2017", "10/01/2017", "15/01/2017", "REALIZOVANO", j4);
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 100, 0, "04/01/2019", "10/01/2019", "15/01/2019", "REALIZOVANO", j4);
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 100, 0, "04/01/2018", "10/01/2018", "15/01/2018", "REALIZOVANO", j5);
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 100, 0, "04/01/2014", "10/01/2014", "15/01/2014", "REALIZOVANO", j5);
        RezervacijaKorisnika rez17 = Creator.createRezervacija(17, 100, 0, "04/01/2011", "10/01/2011", "15/01/2011", "REALIZOVANO", j4);
        
        
        kSession.insert(rez11);
        kSession.insert(rez12);
        kSession.insert(rez13);
        kSession.insert(rez14);
        kSession.insert(rez15);
        kSession.insert(rez16);
        kSession.insert(rez17);
        
        int fired = kSession.fireAllRules();
        
        System.out.println( "Number of Rules executed = " + fired );
	}
	
	public static void testDiscount2 () {
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.getAgenda().getAgendaGroup("popusti").setFocus();
        
        Lokacija l = Creator.createLokacija(1, "Novi Sad");
        
        Rejting r = Creator.createRejting(2, 9);
        Rejting r0 = Creator.createRejting(0, 0);
        
        SmestajniObjekat so = Creator.createSmestajniObjekat(1, "BRONZE", r, l);
        
        SmestajniObjekat so2 = Creator.createSmestajniObjekat(2, "SILVER", r, l);
        
        SmestajniObjekat so3 = Creator.createSmestajniObjekat(3, "GOLD", r, l);
        
        SmestajniObjekat so4 = Creator.createSmestajniObjekat(4, "SILVER", r, l);
        
        SmestajniObjekat so5 = Creator.createSmestajniObjekat(5, "SILVER", r, l);
        
        SmestajniObjekat so6 = Creator.createSmestajniObjekat(6, "BRONZE", r0, l);
        
        SmestajniObjekat so7 = Creator.createSmestajniObjekat(7, "GOLD", r0, l);
        
        Agent a1 = new Agent();
        a1.setId(1L);
        
        Agent a2 = new Agent();
        a2.setId(2L);
        
        Agent a3 = new Agent();
        a3.setId(3L);
        
        so.setAgent(a1);
        so2.setAgent(a2);
        so3.setAgent(a3);
        so4.setAgent(a1);
        so5.setAgent(a2);
        so6.setAgent(a3);
        
        
        SmestajnaJedinica j1 = Creator.createSmestajnaJedinica(1L, so);
        so.getSmestajneJedinice().add(j1);
        
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2L, so2);
        so2.getSmestajneJedinice().add(j2);
        
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3L, so3);
        so3.getSmestajneJedinice().add(j3);
        
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4L, so);
        so.getSmestajneJedinice().add(j4);
        
        KrajnjiKorisnik k = Creator.createKrajnjiKorisnik(1, "SILVER", "10/10/2018");
        
        RezervacijaKorisnika rez1 = Creator.createRezervacija(11, 100, 3, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j1);
        rez1.setPopust(0.0);
        rez1.setKorisnik(k);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(12, 100, 2, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j2);
        rez2.setPopust(0.0);
        rez2.setKorisnik(k);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(13, 100, 4, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j3);
        rez3.setPopust(0.0);
        rez3.setKorisnik(k);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(14, 500, 5, "09/09/2018", "09/04/2019", "09/09/2018", "REALIZOVANO", 
        		j4);
        rez4.setPopust(0.0);
        rez4.setKorisnik(k);
        
        RezervacijaKorisnika rez5 = Creator.createRezervacija(15, 800, 3, "09/09/2018", "09/04/2019", "09/04/2019", "REALIZOVANO", 
        		j1);
        rez5.setPopust(0.0);
        rez5.setKorisnik(k);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(16, 800, 4, "06/04/2019", "09/04/2019", "19/09/2017", "REALIZOVANO", 
        		j2);
        rez6.setPopust(0.0);
        rez6.setKorisnik(k);
        
        RezervacijaKorisnika rezNew = Creator.createRezervacija(20, 800, 4, "16/04/2019", "25/04/2019", "30/04/2019", "REZERVISANO", 
        		j2);
        rezNew.setPopust(0.0);
        rezNew.setKorisnik(k);
        
        k.getRezervacije().add(rez1);
        k.getRezervacije().add(rez2);
        k.getRezervacije().add(rez3);
        k.getRezervacije().add(rez4);
        k.getRezervacije().add(rez5);
        k.getRezervacije().add(rez6);
        
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        kSession.insert(so6);
        kSession.insert(so7);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        kSession.insert(rezNew);
        
        kSession.insert(l);
        
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
        SmestajniObjekat s = Creator.createSmestajniObjekat(1, "NA", Creator.createRejting(0L, 0L));
        kSession.insert(s);
        
        SmestajniObjekat s2 = Creator.createSmestajniObjekat(2, "NA", Creator.createRejting(2L, 3L));
        kSession.insert(s2);
        
        SmestajniObjekat s3 = Creator.createSmestajniObjekat(3, "NA", Creator.createRejting(10L, 25L));
        kSession.insert(s3);
        
        // SILVER
        SmestajniObjekat s4 = Creator.createSmestajniObjekat(4, "NA", Creator.createRejting(10L, 29L));
        kSession.insert(s4);
       
        SmestajnaJedinica j1 = Creator.createSmestajnaJedinica(1L, s4);
        s4.getSmestajneJedinice().add(j1);
        SmestajnaJedinica j2 = Creator.createSmestajnaJedinica(2L, s4);
        s4.getSmestajneJedinice().add(j2);
        
        RezervacijaKorisnika rez1 = Creator.createRezervacija(1, 100, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2, 100, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j2);
        
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3, 100, 0, "01/09/2018", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4, 500, 0, "01/04/2019", "10/10/2018", "09/09/2018", "REALIZOVANO", 
        		j2);
        
        RezervacijaKorisnika rez5 = Creator.createRezervacija(5, 800, 0, "09/09/2018", "10/10/2018", "09/04/2019", "REALIZOVANO", 
        		j1);
        
        RezervacijaKorisnika rez6 = Creator.createRezervacija(6, 800, 0, "01/04/2019", "10/10/2017", "19/09/2017", "REALIZOVANO", 
        		j1);
        
        kSession.insert(rez1);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        kSession.insert(rez5);
        kSession.insert(rez6);
        
        // GOLD
        SmestajniObjekat s5 = Creator.createSmestajniObjekat(5, "NA", Creator.createRejting(10L, 39L));
        kSession.insert(s5);
        
        SmestajnaJedinica j3 = Creator.createSmestajnaJedinica(3L, s5);
        s5.getSmestajneJedinice().add(j3);
        SmestajnaJedinica j4 = Creator.createSmestajnaJedinica(4L, s5);
        s5.getSmestajneJedinice().add(j4);
        
        RezervacijaKorisnika rez7 = Creator.createRezervacija(7, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j3);
        
        RezervacijaKorisnika rez8 = Creator.createRezervacija(8, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j3);
        
        RezervacijaKorisnika rez9 = Creator.createRezervacija(9, 100, 5, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j4);
        
        RezervacijaKorisnika rez10 = Creator.createRezervacija(10, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j4);
        
        RezervacijaKorisnika rez11 = Creator.createRezervacija(10, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j4);
        
        kSession.insert(rez7);
        kSession.insert(rez8);
        kSession.insert(rez9);
        kSession.insert(rez10);
        kSession.insert(rez11);
        
        // NOT GOLD - poslednja ocena manja od 3
        SmestajniObjekat s6 = Creator.createSmestajniObjekat(6, "NA", Creator.createRejting(10L, 39L));
        kSession.insert(s6);
        
        SmestajnaJedinica j5 = Creator.createSmestajnaJedinica(5L, s6);
        s6.getSmestajneJedinice().add(j5);
        SmestajnaJedinica j6 = Creator.createSmestajnaJedinica(6L, s6);
        s6.getSmestajneJedinice().add(j6);
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j5);
        
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j6);
        
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 100, 2, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j6);
        
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j5);
        
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
        		j6);
        
        kSession.insert(rez12);
        kSession.insert(rez13);
        kSession.insert(rez14);
        kSession.insert(rez15);
        kSession.insert(rez16);
        
        // PLATINUM
        SmestajniObjekat s7 = Creator.createSmestajniObjekat(7, "GOLD", Creator.createRejting(10L, 39L));
        kSession.insert(s7);
        
        SmestajnaJedinica j7 = Creator.createSmestajnaJedinica(7L, s7);
        s7.getSmestajneJedinice().add(j7);
        SmestajnaJedinica j8 = Creator.createSmestajnaJedinica(8L, s7);
        s7.getSmestajneJedinice().add(j8);
        
        RezervacijaKorisnika rez17 = Creator.createRezervacija(17, 100, 4, "01/04/2019", "10/10/2018", "05/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez18 = Creator.createRezervacija(18, 100, 4, "01/04/2019", "10/10/2018", "06/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez19 = Creator.createRezervacija(19, 100, 2, "01/09/2018", "10/10/2018", "13/04/2019", "REALIZOVANO", 
        		j7);
        
        RezervacijaKorisnika rez20 = Creator.createRezervacija(20, 500, 4, "01/04/2019", "10/10/2018", "08/04/2019", "REALIZOVANO", 
        		j8);
        
        RezervacijaKorisnika rez21 = Creator.createRezervacija(21, 500, 5, "01/04/2019", "10/10/2018", "10/04/2019", "REALIZOVANO", 
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
        
        KrajnjiKorisnik k5 = Creator.createKrajnjiKorisnik(5, "NA", "10/04/2019");
        k5.getRezervacije().add(rez3);
        
        KrajnjiKorisnik k6 = Creator.createKrajnjiKorisnik(6, "NA", "10/04/2019");
        k6.getRezervacije().add(rez3);
        k6.getRezervacije().add(rez2);
        k6.getRezervacije().add(rez3);
        k6.getRezervacije().add(rez2);
        
        RezervacijaKorisnika rez11 = Creator.createRezervacija(11, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(11, Creator.createSmestajniObjekat(11, "GOLD")));
        
        RezervacijaKorisnika rez12 = Creator.createRezervacija(12, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(12, Creator.createSmestajniObjekat(12, "PLATINUM")));
        
        RezervacijaKorisnika rez13 = Creator.createRezervacija(13, 100, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(13, Creator.createSmestajniObjekat(13, "SILVER")));
        
        RezervacijaKorisnika rez14 = Creator.createRezervacija(14, 500, "09/09/2018", "09/09/2018", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(14, Creator.createSmestajniObjekat(14, "BRONZE")));
        
        RezervacijaKorisnika rez15 = Creator.createRezervacija(15, 800, "09/09/2018", "09/04/2019", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(15, Creator.createSmestajniObjekat(15, "GOLD")));
        
        RezervacijaKorisnika rez16 = Creator.createRezervacija(16, 800, "09/09/2017", "19/09/2017", "REALIZOVANO", 
        		Creator.createSmestajnaJedinica(15, Creator.createSmestajniObjekat(16, "GOLD")));
        
        KrajnjiKorisnik k7 = Creator.createKrajnjiKorisnik(7, "NA", "10/04/2019");
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
        KrajnjiKorisnik k8 = Creator.createKrajnjiKorisnik(8, "NA", "10/04/2019");
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
        KrajnjiKorisnik k9 = Creator.createKrajnjiKorisnik(9, "NA", "10/04/2019");
        k9.getRezervacije().add(rez13);
        k9.getRezervacije().add(rez15);
        //k9.getRezervacije().add(rez15);
        k9.getRezervacije().add(rez14);
        k9.getRezervacije().add(rez14);
        
        // Stare rezervacije
        KrajnjiKorisnik k10 = Creator.createKrajnjiKorisnik(10, "NA", "10/04/2019");
        k10.getRezervacije().add(rez13);
        k10.getRezervacije().add(rez15);
        k10.getRezervacije().add(rez15);
        k10.getRezervacije().add(rez16);
        k10.getRezervacije().add(rez16);
        
        KrajnjiKorisnik k11 = Creator.createKrajnjiKorisnik(11, "NA", "10/04/2019");
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
        
        kSession.getAgenda().getAgendaGroup("popusti").setFocus();
        
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

}

