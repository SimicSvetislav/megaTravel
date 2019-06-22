package com.project.megatravel.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.megatravel.model.accomodation.SmestajnaJedinica;
import com.project.megatravel.model.accomodation.SmestajniObjekat;
import com.project.megatravel.model.chat.Poruka;
import com.project.megatravel.model.reservations.Komentar;
import com.project.megatravel.service.CommentMessageService;
import com.project.megatravel.util.Creator;

@RestController
@RequestMapping("/feedback")
public class CommentMessageController {
	
	@Autowired
	private CommentMessageService commentMessageService;
	
	@RequestMapping(value="/message/{messageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> viewMessage(@PathVariable("messageId") String messageId) {
		try {
			Long id = Long.parseLong(messageId);
			Poruka poruka = commentMessageService.viewMessage(id);
			
			return new ResponseEntity<>(poruka, HttpStatus.OK);		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/message/answer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> answerToMessage(@RequestBody Poruka messageAnswer) {
		try {
			Poruka poruka = commentMessageService.answerMessage(messageAnswer);
			
			return new ResponseEntity<>(poruka, HttpStatus.CREATED);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/message/sent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Poruka>> viewMessageAsSender() {
		List<Poruka> messages = new ArrayList<Poruka>();
		
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(value="/message/inbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Poruka>> viewMessageAsReceiver() {
		List<Poruka> messages = new ArrayList<Poruka>();
		XMLGregorianCalendar c1 = Creator.createXMLCalender(6);
		messages.add(Creator.createPoruka(1l, 2l, 4l, "Postovani da li na prozorima postoje komarnici", c1));
		
		XMLGregorianCalendar c2 = Creator.createXMLCalender(4);
		messages.add(Creator.createPoruka(2l, 2l, 4l, "Postovani, da li kod vas u sobama postoji fen?", c2));
		
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(value="/comments/unitId/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Komentar>> viewCommentsByUnit(@PathVariable("unitId") String messageId) {
		List<Komentar> comments = new ArrayList<Komentar>();
		comments.add(Creator.createKomentar(true, "odlicna usluga, jako cisto, redovno presvlaka posteljine", ""));
		comments.add(Creator.createKomentar(true, "Ljubazni domacini, mesto gde cu se vratiti jedanput ponovo", ""));
		comments.add(Creator.createKomentar(true, "Namestaj je stariji, domacini nisu bas toliko ljubazni", ""));
		comments.add(Creator.createKomentar(true, "Prelepa basta, stalno se odrzava", ""));

		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@RequestMapping(value="/message/objectId/{objectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Komentar>> viewCommentsByObject(@PathVariable("objectId") String messageId) {
		List<Komentar> comments = new ArrayList<Komentar>();
		
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}

}
