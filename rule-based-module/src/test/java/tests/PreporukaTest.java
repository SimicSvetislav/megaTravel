package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.GeoDuzina;
import com.project.megatravel.model.accomodation.GeoSirina;
import com.project.megatravel.model.accomodation.Lokacija;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.rbm.ExistDB;
import com.project.megatravel.util.Creator;

public class PreporukaTest {
	
	@BeforeClass
	public static void initDatabase() throws Exception {
		
		try {
			ExistDB.initDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test	
	public void testRecommendationPlatinum() {
		
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("PLATINUM");
        k.setDatumRegistracije(new Date());
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(1, lista.size());
        assertEquals(2, fired); // + HALT

	}
	
	@Test	
	public void testRecommendationGold() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("GOLD");
        k.setDatumRegistracije(new Date());
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(2, lista.size());
        assertEquals(2, fired); // + HALT

	}
	
	@Test	
	public void testRecommendationSilver() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("SILVER");
        k.setDatumRegistracije(new Date());
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(3, lista.size());
        assertEquals(2, fired); // + HALT

	}
	
	
	@Test	
	public void testRecommendationBronze() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("BRONZE");
        k.setDatumRegistracije(new Date());
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(4, lista.size());
        assertEquals(2, fired); // + HALT

	}
	
	@Test	
	public void testRecommendationNA() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        k.setDatumRegistracije(new Date());
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so4 = new SmestajniObjekat();
        so4.setKategorija("SILVER");
        so4.setRejting(Creator.createRejting(2, 9));
        
        SmestajniObjekat so5= new SmestajniObjekat();
        so5.setKategorija("NA");
        so5.setRejting(Creator.createRejting(2, 9));
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(so4);
        kSession.insert(so5);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("lista");
        
        assertEquals(5, lista.size());
        assertEquals(2, fired); // + HALT

	}
	
	@Test	
	public void testRecommendationDistance() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        k.setDatumRegistracije(new Date());
        k.setId(1L);
        Lokacija lok = Creator.createLokacija(1, "Novi Sad");
        
        KrajnjiKorisnik k2 = new KrajnjiKorisnik();
        k2.setKategorija("GOLD");
        k2.setDatumRegistracije(new Date());
        k2.setId(2L);
        
        GeoSirina sirina = new GeoSirina();
        sirina.setStepeni(45.267136);
        
        GeoDuzina duzina = new GeoDuzina();
        duzina.setStepeni(19.833549);
        
        lok.setGeoSirina(sirina);
        lok.setGeoDuzina(duzina);
        
        k.setLokacija(lok);
        
        
        Lokacija beograd = Creator.createLokacija(2L, "Beograd", 44.815071, 20.460480);
        Lokacija nis = Creator.createLokacija(3L, "Nis", 43.320904, 21.895760);
        Lokacija paris = Creator.createLokacija(4L, "Pariz", 48.856613, 2.352222);
        
        k2.setLokacija(paris);
        
        //System.out.println(Creator.distance(beograd, nis));
        //System.out.println(Creator.distance(beograd, paris));
        //System.out.println(Creator.distance(beograd, lok));
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        so.setLokacija(nis);
        so.setId(1L);
        
        SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
        so.getSmestajneJedinice().add(sj);
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        so2.setId(2L);
        
        SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
        so2.getSmestajneJedinice().add(sj2);
        so2.setLokacija(beograd);
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(2, so3);
        so3.getSmestajneJedinice().add(sj3);
        so3.setLokacija(paris);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez.setDatumRezervacije(new Date());
        rez.setKorisnik(k);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj2);        
        rez2.setKorisnik(k);
        rez2.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez3.setKorisnik(k);
        rez3.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj3);
        rez4.setKorisnik(k2);
        rez4.setDatumRezervacije(new Date());
        
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
		List lista = (List)kSession.getGlobal("listaUdaljenost");
        
        // Nis i Beograd, preporucen Beograd (Pariz kod drugog korisnika)
        assertEquals(1, lista.size());
        assertEquals(4, fired); // + HALT, 2 dodatna za zajednicku agenda grupu

	}
	
	@Test	
	public void testRecommendationDistance2() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        k.setDatumRegistracije(new Date());
        k.setId(1L);
        Lokacija lok = Creator.createLokacija(1, "Novi Sad");
        
        KrajnjiKorisnik k2 = new KrajnjiKorisnik();
        k2.setKategorija("GOLD");
        k2.setDatumRegistracije(new Date());
        k2.setId(2L);
        
        GeoSirina sirina = new GeoSirina();
        sirina.setStepeni(45.267136);
        
        GeoDuzina duzina = new GeoDuzina();
        duzina.setStepeni(19.833549);
        
        lok.setGeoSirina(sirina);
        lok.setGeoDuzina(duzina);
        
        k.setLokacija(lok);
        
        Lokacija beograd = Creator.createLokacija(2L, "Beograd", 44.815071, 20.460480);
        Lokacija nis = Creator.createLokacija(3L, "Nis", 43.320904, 21.895760);
        Lokacija paris = Creator.createLokacija(4L, "Pariz", 48.856613, 2.352222);
        
        k2.setLokacija(paris);
        
        //System.out.println(Creator.distance(beograd, nis));
        //System.out.println(Creator.distance(beograd, paris));
        //System.out.println(Creator.distance(beograd, lok));
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        so.setLokacija(nis);
        so.setId(1L);
        
        SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
        so.getSmestajneJedinice().add(sj);
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        so2.setId(2L);
        
        SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
        so2.getSmestajneJedinice().add(sj2);
        so2.setLokacija(beograd);
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(2, so3);
        so3.getSmestajneJedinice().add(sj3);
        so3.setLokacija(paris);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez.setDatumRezervacije(new Date());
        rez.setKorisnik(k);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj2);        
        rez2.setKorisnik(k);
        rez2.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez3.setKorisnik(k);
        rez3.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj3);
        rez4.setKorisnik(k);
        rez4.setDatumRezervacije(new Date());
        
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        int fired = kSession.fireAllRules();
        
        @SuppressWarnings("rawtypes")
        List lista = (List)kSession.getGlobal("listaUdaljenost");
        
        // Nis, Beograd i Pariz preporuceni Beograd i Nis
        assertEquals(2, lista.size());
        assertEquals(4, fired); // + HALT, 2 dodatna za zajednicku agenda grupu

	}
	
	@Test	
	public void testRecommendationDistanceQuery() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        k.setDatumRegistracije(new Date());
        k.setId(1L);
        Lokacija lok = Creator.createLokacija(1, "Novi Sad");
        
        KrajnjiKorisnik k2 = new KrajnjiKorisnik();
        k2.setKategorija("GOLD");
        k2.setDatumRegistracije(new Date());
        k2.setId(2L);
        
        GeoSirina sirina = new GeoSirina();
        sirina.setStepeni(45.267136);
        
        GeoDuzina duzina = new GeoDuzina();
        duzina.setStepeni(19.833549);
        
        lok.setGeoSirina(sirina);
        lok.setGeoDuzina(duzina);
        
        k.setLokacija(lok);
        
        Lokacija beograd = Creator.createLokacija(2L, "Beograd", 44.815071, 20.460480);
        Lokacija nis = Creator.createLokacija(3L, "Nis", 43.320904, 21.895760);
        Lokacija paris = Creator.createLokacija(4L, "Pariz", 48.856613, 2.352222);
        
        k2.setLokacija(paris);
        
        //System.out.println(Creator.distance(beograd, nis));
        //System.out.println(Creator.distance(beograd, paris));
        //System.out.println(Creator.distance(beograd, lok));
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        so.setLokacija(nis);
        so.setId(1L);
        
        SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
        so.getSmestajneJedinice().add(sj);
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        so2.setId(2L);
        
        SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
        so2.getSmestajneJedinice().add(sj2);
        so2.setLokacija(beograd);
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(2, so3);
        so3.getSmestajneJedinice().add(sj3);
        so3.setLokacija(paris);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez.setDatumRezervacije(new Date());
        rez.setKorisnik(k);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj2);        
        rez2.setKorisnik(k);
        rez2.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez3.setKorisnik(k);
        rez3.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj3);
        rez4.setKorisnik(k);
        rez4.setDatumRezervacije(new Date());
        
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        QueryResults results = kSession.getQueryResults( "preporukaUdaljenost", k ); 
		for ( QueryResultsRow row : results ) {
			SmestajniObjekat soR = ( SmestajniObjekat ) row.get( "$result" );
			System.out.println(soR.getId());
		}
        
        // Nis, Beograd i Pariz preporuceni Beograd i Nis
        assertEquals(2, results.size());

	}
	
	@Test	
	public void testRecommendationExtrasQuery() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        k.setDatumRegistracije(new Date());
        k.setId(1L);
        Lokacija lok = Creator.createLokacija(1, "Novi Sad");
        
        KrajnjiKorisnik k2 = new KrajnjiKorisnik();
        k2.setKategorija("GOLD");
        k2.setDatumRegistracije(new Date());
        k2.setId(2L);
        
        GeoSirina sirina = new GeoSirina();
        sirina.setStepeni(45.267136);
        
        GeoDuzina duzina = new GeoDuzina();
        duzina.setStepeni(19.833549);
        
        lok.setGeoSirina(sirina);
        lok.setGeoDuzina(duzina);
        
        k.setLokacija(lok);
        
        Lokacija beograd = Creator.createLokacija(2L, "Beograd", 44.815071, 20.460480);
        Lokacija nis = Creator.createLokacija(3L, "Nis", 43.320904, 21.895760);
        Lokacija paris = Creator.createLokacija(4L, "Pariz", 48.856613, 2.352222);
        
        k2.setLokacija(paris);
        
        //System.out.println(Creator.distance(beograd, nis));
        //System.out.println(Creator.distance(beograd, paris));
        //System.out.println(Creator.distance(beograd, lok));
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        so.setLokacija(nis);
        so.setId(1L);
        
        
        SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
        so.getSmestajneJedinice().add(sj);
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        so2.setId(2L);
        
        SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
        so2.getSmestajneJedinice().add(sj2);
        so2.setLokacija(beograd);
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(2, so3);
        so3.getSmestajneJedinice().add(sj3);
        so3.setLokacija(paris);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez.setDatumRezervacije(new Date());
        rez.setKorisnik(k);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj2);        
        rez2.setKorisnik(k);
        rez2.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez3.setKorisnik(k);
        rez3.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj3);
        rez4.setKorisnik(k2);
        rez4.setDatumRezervacije(new Date());
        
        DodatnaUsluga du = new DodatnaUsluga();
        du.setId(1L);
        du.setIme("Bazen");
        
        DodatnaUsluga du2 = new DodatnaUsluga();
        du2.setId(2L);
        du2.setIme("Wi-fi");
        
        DodatnaUsluga du3 = new DodatnaUsluga();
        du3.setId(3L);
        du3.setIme("Minibar");
        
        DodatnaUsluga du4 = new DodatnaUsluga();
        du4.setId(4L);
        du4.setIme("Sauna");
        
        so.getDodatneUsluge().add(du);
        so.getDodatneUsluge().add(du2);
        so.getDodatneUsluge().add(du3);
        
        so2.getDodatneUsluge().add(du2);
        so2.getDodatneUsluge().add(du3);
        
        so3.getDodatneUsluge().add(du2);
        so3.getDodatneUsluge().add(du3);
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        kSession.insert(du);
        kSession.insert(du2);
        kSession.insert(du3);
        kSession.insert(du4);
        
        //QueryResults results = kSession.getQueryResults( "preporukaDodatneUsluge", k ); 
		/*for ( QueryResultsRow row : results ) {
			DodatnaUsluga d = ( DodatnaUsluga ) row.get( "$sve" );
			System.out.println(d.getIme());
		}*/
        
        int fired = kSession.fireAllRules();
        
        assertEquals(4, fired); // 2 dodatna za zajednicku agenda grupu
        
        @SuppressWarnings("rawtypes")
        List lista = (List)kSession.getGlobal("listaDU");
      
        // Nis, Beograd i Pariz preporuceni Beograd i Nis
        assertEquals(3, lista.size());

	}
	
	@Test	
	public void testRecommendationExtras2() {
	
		System.out.println( "Bootstrapping the Rule Engine ..." );

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession("ksession-rules");
        
        kSession.setGlobal("lista", new ArrayList<>());
        kSession.setGlobal("listaUdaljenost", new ArrayList<>());
        kSession.setGlobal("listaDU", new ArrayList<>());
        kSession.setGlobal("klijent", 1L);
        
        kSession.getAgenda().getAgendaGroup("preporuka").setFocus();
		
        KrajnjiKorisnik k = new KrajnjiKorisnik();
        k.setKategorija("NA");
        k.setDatumRegistracije(new Date());
        k.setId(1L);
        Lokacija lok = Creator.createLokacija(1, "Novi Sad");
        
        KrajnjiKorisnik k2 = new KrajnjiKorisnik();
        k2.setKategorija("GOLD");
        k2.setDatumRegistracije(new Date());
        k2.setId(2L);
        
        GeoSirina sirina = new GeoSirina();
        sirina.setStepeni(45.267136);
        
        GeoDuzina duzina = new GeoDuzina();
        duzina.setStepeni(19.833549);
        
        lok.setGeoSirina(sirina);
        lok.setGeoDuzina(duzina);
        
        k.setLokacija(lok);
        
        Lokacija beograd = Creator.createLokacija(2L, "Beograd", 44.815071, 20.460480);
        Lokacija nis = Creator.createLokacija(3L, "Nis", 43.320904, 21.895760);
        Lokacija paris = Creator.createLokacija(4L, "Pariz", 48.856613, 2.352222);
        
        k2.setLokacija(paris);
        
        //System.out.println(Creator.distance(beograd, nis));
        //System.out.println(Creator.distance(beograd, paris));
        //System.out.println(Creator.distance(beograd, lok));
        
        SmestajniObjekat so = new SmestajniObjekat();
        so.setKategorija("BRONZE");
        so.setRejting(Creator.createRejting(2, 9));
        so.setLokacija(nis);
        so.setId(1L);
        
        
        SmestajnaJedinica sj = Creator.createSmestajnaJedinica(1, so);
        so.getSmestajneJedinice().add(sj);
        
        SmestajniObjekat so2 = new SmestajniObjekat();
        so2.setKategorija("GOLD");
        so2.setRejting(Creator.createRejting(2, 9));
        so2.setId(2L);
        
        SmestajnaJedinica sj2 = Creator.createSmestajnaJedinica(2, so2);
        so2.getSmestajneJedinice().add(sj2);
        so2.setLokacija(beograd);
        
        SmestajniObjekat so3 = new SmestajniObjekat();
        so3.setKategorija("PLATINUM");
        so3.setRejting(Creator.createRejting(2, 9));
        
        SmestajnaJedinica sj3 = Creator.createSmestajnaJedinica(2, so3);
        so3.getSmestajneJedinice().add(sj3);
        so3.setLokacija(paris);
        
        RezervacijaKorisnika rez = Creator.createRezervacija(1L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez.setDatumRezervacije(new Date());
        rez.setKorisnik(k);
        RezervacijaKorisnika rez2 = Creator.createRezervacija(2L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj2);        
        rez2.setKorisnik(k);
        rez2.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez3 = Creator.createRezervacija(3L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj);
        rez3.setKorisnik(k);
        rez3.setDatumRezervacije(new Date());
        RezervacijaKorisnika rez4 = Creator.createRezervacija(4L, 300, "07/07/2019", "17/07/2019", "REALIZOVANO", sj3);
        rez4.setKorisnik(k2);
        rez4.setDatumRezervacije(new Date());
        
        DodatnaUsluga du = new DodatnaUsluga();
        du.setId(1L);
        du.setIme("Bazen");
        
        DodatnaUsluga du2 = new DodatnaUsluga();
        du2.setId(2L);
        du2.setIme("Wi-fi");
        
        DodatnaUsluga du3 = new DodatnaUsluga();
        du3.setId(3L);
        du3.setIme("Minibar");
        
        DodatnaUsluga du4 = new DodatnaUsluga();
        du4.setId(4L);
        du4.setIme("Sauna");
        
        so.getDodatneUsluge().add(du);
        so.getDodatneUsluge().add(du2);
        so.getDodatneUsluge().add(du3);
        
        so2.getDodatneUsluge().add(du2);
        so2.getDodatneUsluge().add(du3);
        
        so3.getDodatneUsluge().add(du2);
        
        kSession.insert(k);
        kSession.insert(so);
        kSession.insert(so2);
        kSession.insert(so3);
        kSession.insert(rez);
        kSession.insert(rez2);
        kSession.insert(rez3);
        kSession.insert(rez4);
        
        kSession.insert(du);
        kSession.insert(du2);
        kSession.insert(du3);
        kSession.insert(du4);
        
        //QueryResults results = kSession.getQueryResults( "preporukaDodatneUsluge", k ); 
		/*for ( QueryResultsRow row : results ) {
			DodatnaUsluga d = ( DodatnaUsluga ) row.get( "$sve" );
			System.out.println(d.getIme());
		}*/
        
        int fired = kSession.fireAllRules();
        
        assertEquals(4, fired); // 2 dodatna za zajednicku agenda grupu
        
        @SuppressWarnings("rawtypes")
        List lista = (List)kSession.getGlobal("listaDU");
      
        // Nis, Beograd i Pariz preporuceni Beograd i Nis
        assertEquals(2, lista.size()); 

	}
	
}
