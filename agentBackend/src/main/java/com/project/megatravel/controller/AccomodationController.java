package com.project.megatravel.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.accomodation.DodatnaUsluga;
import com.project.megatravel.model.accomodation.KategorijaSm;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.accomodation.TipSmestaja;
import com.project.megatravel.service.AccomodationService;

@RestController
@RequestMapping("/accomodation")
@CrossOrigin
public class AccomodationController {

//	@Autowired
//	private AccomodationClient accClient;
	
	@Autowired
	private AccomodationService accomodationService;
	
	@RequestMapping(value="/object", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<SmestajniObjekat>> getAllAccomodationObjects() {
			try {
				Collection<SmestajniObjekat> objects = accomodationService.getAllAccomodationObjects();
				return new ResponseEntity<>(objects, HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}
	
	@RequestMapping(value="/object/agentId/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmestajniObjekat>> getAllAccomodationObjects(@PathVariable("agentId") String agentId) {
		
		
		return new ResponseEntity<>(new ArrayList<SmestajniObjekat>(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object/{objectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajniObjekat> getAccomodationObject(@PathVariable("objectId") String objectId) {
		try {
			Long id = Long.parseLong(objectId);
			SmestajniObjekat objekat = accomodationService.getAccomodationObject(id);
			
			return new ResponseEntity<>(objekat, HttpStatus.OK);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/object/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajniObjekat> addNewAccomodationObject(@RequestBody SmestajniObjekat newObject) {
		try {
			SmestajniObjekat objekat = accomodationService.addNewObject(newObject);
			return new ResponseEntity<>(objekat, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="/object/{objectId}/unit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SmestajnaJedinica>> getAllObjectUnits(@PathVariable("objectId") String objectId) {
		try {
			Long id = Long.parseLong(objectId);
			List<SmestajnaJedinica> jedinice = accomodationService.getAllObjectUnits(id);
			
			return new ResponseEntity<>(jedinice, HttpStatus.OK);		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value="/object/{objectId}/unit/new",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinica> addNewObjectUnit(@PathVariable("objectId") String objectId, @RequestBody SmestajnaJedinica newUnit) {
		try {
			Long id = Long.parseLong(objectId);
			newUnit.setSObjekat(id);  //ne parsira se long id...
			SmestajnaJedinica jedinica = accomodationService.addNewObjectUnit(newUnit);
			
			return new ResponseEntity<>(jedinica, HttpStatus.CREATED);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}	
		
	}
	
	@RequestMapping(value="/object/{objectId}/unit/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinica> getObjectUnit(@PathVariable("objectId") String objectId, @PathVariable("unitId") String unitId) {
		try {
			Long id = Long.parseLong(objectId);
			Long uId= Long.parseLong(unitId);
			SmestajnaJedinica jedinica = accomodationService.getObjectUnit(uId);
			
			return new ResponseEntity<>(jedinica, HttpStatus.OK);		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/object/{objectId}/unit/{unitId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajnaJedinica> updateObjectUnit(@PathVariable("objectId") String objectId, @PathVariable("unitId") String unitId, @RequestBody SmestajnaJedinica unit) {
		
		
		//return new ResponseEntity<>(new SmestajnaJedinica(), HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(new SmestajnaJedinica(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/object/extras", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<DodatnaUsluga>> getAllExtras() {
		try {
			Collection<DodatnaUsluga> usluge = accomodationService.getAllAccomodationExtras();
			return new ResponseEntity<>(usluge, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="/object/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<TipSmestaja>> getAllAccomodationTypes() {
		try {
			Collection<TipSmestaja> tipovi = accomodationService.getAllAccomodationTypes();
			return new ResponseEntity<>(tipovi, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="/object/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<KategorijaSm>> getAllAccomodationCategories() {
		try {
			Collection<KategorijaSm> kategorije = accomodationService.getAllAccomodationCategories();
			return new ResponseEntity<>(kategorije, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}
