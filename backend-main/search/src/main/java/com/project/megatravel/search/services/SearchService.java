package com.project.megatravel.search.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import com.project.megatravel.model.accomodation.Cenovnik;
import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.Polozaj;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;
import com.project.megatravel.model.search.PretragaObjekat;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.search.model.dto.ResultDTO;
import com.project.megatravel.search.repository.KorisnikRepository;
import com.project.megatravel.search.repository.RezervacijeRepository;
import com.project.megatravel.search.repository.SjRepository;
import com.project.megatravel.search.repository.SoRepository;

@Service
@CrossOrigin
public class SearchService {

	@Autowired
	private SjRepository repo;
	
	@Autowired
	private SoRepository soRepo;
	
	@Autowired
	private RezervacijeRepository resRepo;
	
	@Autowired
	private KorisnikRepository korRepo;
	
	@Autowired
	private RestTemplate rest;
	
	//private static final String GRADE = "http://localhost:8010/rating-module/us-central1/averageGrade?room=";
	private static final String GRADE = "http://host.docker.internal:8010/rating-module/us-central1/averageGrade?room=";
	
	public List<SmestajniObjekat> searchObject(PretragaObjekat po) {
		
		//List<SmestajnaJedinica> all = (List<SmestajnaJedinica>)repo.getAll();
		
		return new ArrayList<>();
	}
	
	public List<ResultDTO> search(PretragaObjekat po, Long korId) {
		
		List<SmestajnaJedinica> list = (List<SmestajnaJedinica>)repo.getAll();
		List<SmestajniObjekat> objekti = (List<SmestajniObjekat>)soRepo.getAll();
		List<RezervacijaKorisnika> ress = (List<RezervacijaKorisnika>)resRepo.getAll();
		
		Map<Long, SmestajniObjekat> mapa = objekti.stream().collect(Collectors.toMap(SmestajniObjekat::getId, so -> so));
		
		if (po.getLokacija()!=null && !po.getLokacija().trim().equals("")) {
			// Filtriranje po imenu
			list = list.stream().filter(sj -> mapa.get(sj.getSObjekat()).getLokacija().getNaziv().contains(po.getLokacija())).collect(Collectors.toList());
		}
		
		if (po.getBrojOsoba()!=null && po.getBrojOsoba()!=0) {
			// Filtriranje po broju kreveta
			list = list.stream().filter(sj -> sj.getBrojKreveta() >= po.getBrojOsoba()).collect(Collectors.toList());
		}
		
		// Tip smestaja
		if (po.getTipSmestaja()!=null && !po.getTipSmestaja().equals("")) {
			// Filtriranje tipu smestaja
			list = list.stream().filter(sj -> mapa.get(sj.getSObjekat()).getTipSmestaja().getNaziv().equals(po.getTipSmestaja())).collect(Collectors.toList());
		}
		
		
		// Kategorija smestaja, nula nekategorisan
		if (po.getKategorijaSmestaja()!=null) {
			// Filtriranje po kategoriji
			list = list.stream().filter(sj -> mapa.get(sj.getSObjekat()).getKategorijaSm().getZvezdice()==po.getKategorijaSmestaja()).collect(Collectors.toList());
		}
		
		// Besplatno otkazivanje
		if (po.isBesplatnoOtkazivanje()!=null && po.isBesplatnoOtkazivanje()) {
			list = list.stream().filter(sj -> sj.getOtkazivanje().isDozvoljeno()).collect(Collectors.toList());
			if (po.getOtkazivanjePre()!=null && po.getOtkazivanjePre()>0) {
				list = list.stream().filter(sj -> sj.getOtkazivanje().getBrojDana() <= po.getOtkazivanjePre()).collect(Collectors.toList());
			}
		}
		
		// Udaljenost
		if (korId!=null && korId > 0 && po.getUdaljenost()!=null && po.getUdaljenost() > 0) {
			KrajnjiKorisnik korisnik = korRepo.getOneById(korId);
			list = list.stream().filter(sj -> checkDistance(mapa.get(sj.getSObjekat()).getLokacija().getKoordinate(), korisnik.getLokacija().getKoordinate(), po.getUdaljenost())).collect(Collectors.toList());
		}
		
		// Dodatne usluge
		List<SmestajnaJedinica> toRemoveUsluge = new ArrayList<>();
		for(SmestajnaJedinica s: list) {
			SmestajniObjekat so = mapa.get(s.getSObjekat());
			
			List<DodatnaUsluga> usluge = so.getDodatnaUsluga();
			List<Long> ids = usluge.stream().map(u -> u.getId()).collect(Collectors.toList());
			
			if (!ids.containsAll(po.getDodatneUsluge())) {
				toRemoveUsluge.add(s);
			}
			
		}
		
		list.removeAll(toRemoveUsluge);
		
		
		List<SmestajnaJedinica> toRemove = new ArrayList<>();
		
		// Da li slobodna jedinica
		for(SmestajnaJedinica s : list) {
			// Rezervacije za neku jedinicu
			List<RezervacijaKorisnika> resSj = getByJed(s.getId(), ress);
			
			for (RezervacijaKorisnika r : resSj) {
				System.out.println("PO: " + po.getDolazak() + "    " + po.getOdlazak());
				System.out.println("R: " + r.getDatumZavrsetka() + "    " + r.getDatumPocetka());
				if (po.getDolazak().before(r.getDatumZavrsetka()) && r.getDatumPocetka().before(po.getOdlazak())) {
					if (!r.getStanje().equals("OTKAZANO")) {
						toRemove.add(s);
						break;
					}
					
				}
			}
		}
		
		list.removeAll(toRemove);
		
		List<ResultDTO> results = new ArrayList<>();
		for (SmestajnaJedinica f: list) {
			ResultDTO dto = new ResultDTO();
			SmestajniObjekat so = mapa.get(f.getSObjekat());
			
			dto.setJedinica(f);
			dto.setSlike(so.getSlike());
			dto.setOpis(so.getOpis());
			dto.setKategorija(so.getZvezdice());
			dto.setNazivObj(so.getNaziv());
			dto.setLokacija(so.getLokacija());
			
			//Double ocenaRac = rest.getForObject(GRADE + f.getId(), Double.class);
			Double ocenaRac = getOcena(f.getId());
			dto.setOcena(ocenaRac);
			
			// TODO: odrediti cenu
			// dto.setOcena(cena);
			double cena = generatePrice(po, f);
			dto.setCena(cena);
			System.out.println("Cena: " + cena);
			// TODO pozvati cloud
			//dto.setOcena(ocena);
			
			results.add(dto);
			
		}
		
		
		return results;
	}
	
	public List<RezervacijaKorisnika> getByJed(Long id, Collection<RezervacijaKorisnika> all) {
		
		List<RezervacijaKorisnika> res =  all.stream()
				.filter(u -> u.getSmestajnaJedinica().equals(id))
				.collect(Collectors.toList());
		
		
		return res;
		
	}
	
	public boolean checkDistance(Polozaj coordsSmestaj, Polozaj coordsKorisnik, Double limit) {
		
		double meters = distance(coordsSmestaj.getGeoSirina(), coordsKorisnik.getGeoSirina(), coordsSmestaj.getGeoDuzina(), coordsKorisnik.getGeoDuzina(), 0.0, 0.0);
		
		double km = meters / 1000;
		
		if (km < limit) {
			return true;
		}
		
		return false;
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
	
	public Double getOcena(Long id) {
		RestTemplate rest2 = new RestTemplate();
		Double ret = rest2.getForObject(GRADE + id, Double.class);
		return ret;
	}
	
	private double generatePrice(PretragaObjekat po, SmestajnaJedinica s) {
		double cena = 0.0;
				
		long searchObjectBeginDate = po.getOdlazak().getTime();
		long searchObjectEndDate = po.getDolazak().getTime();
		
		long numberOfDaysForTrip = calculuteDaysBetween(searchObjectBeginDate, searchObjectEndDate);
		long numberOfDaysThatHaveAssignedPrice = 0;
		
		// TODO
		
		for(Cenovnik c : s.getCenovnici()) {
			long priceBeginDate = c.getPocetak().getTime();
			long priceEndDate = c.getKraj().getTime();
			
			long numberOfDaysInRange = numberOfDaysThatAreInRange(searchObjectBeginDate, searchObjectEndDate, priceBeginDate, priceEndDate);
			
			numberOfDaysThatHaveAssignedPrice += numberOfDaysInRange;
			cena += (numberOfDaysInRange * c.getCena());
			
		}
		
		if(numberOfDaysThatHaveAssignedPrice <= numberOfDaysForTrip) {
			cena += ((numberOfDaysForTrip - numberOfDaysThatHaveAssignedPrice) * s.getPodrazumevanaCena());
		} else {
			System.out.println("GRESKA!!!! Prekoracenje pri brojanju dana");
		}
		
		
		return cena;
	}
	
	private long  calculuteDaysBetween(long searchObjectBeginDate, long searchObjectEndDate) {
		long difference = (searchObjectBeginDate - searchObjectEndDate) / 86400000; // millisecond per day
		return Math.abs(difference);
	}
	
	private long numberOfDaysThatAreInRange(long searchObjectBeginDate, long searchObjectEndDate, long priceBeginDate, long priceEndDate) {		
		if(searchObjectBeginDate < priceBeginDate && searchObjectEndDate > priceEndDate) {  //zeljeni termin obuhvata cak i prevazilazi platni termin
			return calculuteDaysBetween(priceBeginDate, priceEndDate);
		}
		
		if(searchObjectBeginDate >= priceBeginDate && searchObjectBeginDate < priceEndDate) {  //pocetak zeljnog termina upada u ovaj platni termin
			if(searchObjectEndDate <= priceEndDate) {				// zeljeni datumi su u celini u jednom platnom terminu
				return calculuteDaysBetween(searchObjectBeginDate, searchObjectEndDate);
			}else {
				return calculuteDaysBetween(searchObjectBeginDate, priceEndDate);		// delicmo termin upada u ovaj platni termin
			}
		}
		
		if (searchObjectEndDate > priceBeginDate && searchObjectEndDate <= priceEndDate ){
			return calculuteDaysBetween(priceBeginDate, searchObjectEndDate);
		}
		
		
		return 0; // zeljeni datum uopste ne upada u ovaj platni termina cena;
	}

}
