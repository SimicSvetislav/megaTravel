package com.project.megatravel.dto;

import com.project.megatravel.model.accomodation.SmestajniObjekat;

public class SmestajniObjekatDTO {

	private String naziv;
   
	private Long id;
    
	
	 public SmestajniObjekatDTO(Long id, String naziv) {
			super();
			this.naziv = naziv;
			this.id = id;
		}
	
	 public SmestajniObjekatDTO(SmestajniObjekat obj) {
		 this(obj.getId(), obj.getNaziv());
	 }
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	    
}
