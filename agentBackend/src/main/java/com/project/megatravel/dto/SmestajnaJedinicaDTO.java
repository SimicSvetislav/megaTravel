package com.project.megatravel.dto;

import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;

public class SmestajnaJedinicaDTO {

	
	private Integer brojKreveta;
    private boolean balkon;
    private long sObjekat;
    private String opis;  
    private Otkazivanje otkazivanje;
    private Long id;
    private String oznaka;
    
    private SmestajniObjekatDTO sObjekatDTO;

	public SmestajnaJedinicaDTO(Integer brojKreveta, boolean balkon, String opis, Otkazivanje otkazivanje, Long id,
			String oznaka, Long sObjekat, SmestajniObjekatDTO sObjekatDTO) {
		super();
		this.brojKreveta = brojKreveta;
		this.balkon = balkon;
		this.opis = opis;
		this.otkazivanje = otkazivanje;
		this.id = id;
		this.oznaka = oznaka;
		this.sObjekatDTO = sObjekatDTO;
		this.sObjekat = sObjekat;
	}
	
	public SmestajnaJedinicaDTO(SmestajnaJedinica jed, SmestajniObjekatDTO obj) {
		this(jed.getBrojKreveta(), jed.isBalkon(), jed.getOpis(), jed.getOtkazivanje(), jed.getId(), jed.getOznaka(), jed.getSObjekat(), obj);
	}

	public Integer getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(Integer brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public boolean isBalkon() {
		return balkon;
	}

	public void setBalkon(boolean balkon) {
		this.balkon = balkon;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Otkazivanje getOtkazivanje() {
		return otkazivanje;
	}

	public void setOtkazivanje(Otkazivanje otkazivanje) {
		this.otkazivanje = otkazivanje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public SmestajniObjekatDTO getsObjekatDTO() {
		return sObjekatDTO;
	}

	public void setsObjekatDTO(SmestajniObjekatDTO sObjekatDTO) {
		this.sObjekatDTO = sObjekatDTO;
	}

	public long getsObjekat() {
		return sObjekat;
	}

	public void setsObjekat(long sObjekat) {
		this.sObjekat = sObjekat;
	}
    
	
	

    
    
}
