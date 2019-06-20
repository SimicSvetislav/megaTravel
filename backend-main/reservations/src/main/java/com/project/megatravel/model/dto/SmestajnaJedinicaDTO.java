package com.project.megatravel.model.dto;

import com.project.megatravel.model.accomodation.Otkazivanje;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;

public class SmestajnaJedinicaDTO {

	
	protected Integer brojKreveta;
    protected boolean balkon;
    protected long sObjekat;
    protected String opis;  
    protected Otkazivanje otkazivanje;
    protected Long id;
	public SmestajnaJedinicaDTO(Integer brojKreveta, boolean balkon, long sObjekat, String opis,
			Otkazivanje otkazivanje, Long id) {
		super();
		this.brojKreveta = brojKreveta;
		this.balkon = balkon;
		this.sObjekat = sObjekat;
		this.opis = opis;
		this.otkazivanje = otkazivanje;
		this.id = id;
	}
	
	public SmestajnaJedinicaDTO(SmestajnaJedinica sj) {
		this(sj.getBrojKreveta(),sj.isBalkon(),sj.getSObjekat(),sj.getOpis(),sj.getOtkazivanje(),sj.getId());
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
	public long getsObjekat() {
		return sObjekat;
	}
	public void setsObjekat(long sObjekat) {
		this.sObjekat = sObjekat;
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
    
    
}
