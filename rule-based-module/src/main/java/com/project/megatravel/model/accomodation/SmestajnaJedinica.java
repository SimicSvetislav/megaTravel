//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.14 at 01:33:54 PM CEST 
//


package com.project.megatravel.model.accomodation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import com.project.megatravel.model.reservations.Komentar;


/**
 * <p>Java class for TSmestajnaJedinica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSmestajnaJedinica">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brojKreveta" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="balkon" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="slike" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
 *                 &lt;attribute name="putanja" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="rejting" type="{www.model.megatravel.project.com/accomodation}TRejting"/>
 *         &lt;element name="sObjekat" type="{www.model.megatravel.project.com/accomodation}TSmestajniObjekat"/>
 *         &lt;element name="komentari" type="{www.model.megatravel.project.com/reservations}TKomentar" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cenovnici" type="{www.model.megatravel.project.com/accomodation}TCenovnik" maxOccurs="unbounded"/>
 *         &lt;element name="podrazumevaniCenovnik" type="{www.model.megatravel.project.com/accomodation}TCenovnik"/>
 *         &lt;element name="opis">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dodatneUsluge" type="{www.model.megatravel.project.com/accomodation}TDodatnaUsluga" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="otkazivanje" type="{www.model.megatravel.project.com/accomodation}TOtkazivanje"/>
 *         &lt;element name="povecanjeVeciBrojOsoba">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSmestajnaJedinica", propOrder = {
    "brojKreveta",
    "balkon",
    "slike",
    "rejting",
    "sObjekat",
    "komentari",
    "cenovnici",
    "podrazumevaniCenovnik",
    "opis",
    "dodatneUsluge",
    "otkazivanje",
    "povecanjeVeciBrojOsoba"
})
public class SmestajnaJedinica {

    @XmlElement(required = true)
    protected BigInteger brojKreveta;
    @XmlElement(defaultValue = "false")
    protected boolean balkon;
    @XmlElement(required = true)
    protected List<SmestajnaJedinica.Slike> slike;
    @XmlElement(required = true)
    protected Rejting rejting;
    @XmlElement(required = true)
    protected SmestajniObjekat sObjekat;
    protected List<Komentar> komentari;
    @XmlElement(required = true)
    protected List<Cenovnik> cenovnici;
    @XmlElement(required = true)
    protected Cenovnik podrazumevaniCenovnik;
    @XmlElement(required = true)
    protected String opis;
    protected List<DodatnaUsluga> dodatneUsluge;
    @XmlElement(required = true)
    protected Otkazivanje otkazivanje;
    protected double povecanjeVeciBrojOsoba;
    @XmlAttribute(name = "id")
    protected Long id;

    /**
     * Gets the value of the brojKreveta property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojKreveta() {
        return brojKreveta;
    }

    /**
     * Sets the value of the brojKreveta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojKreveta(BigInteger value) {
        this.brojKreveta = value;
    }

    /**
     * Gets the value of the balkon property.
     * 
     */
    public boolean isBalkon() {
        return balkon;
    }

    /**
     * Sets the value of the balkon property.
     * 
     */
    public void setBalkon(boolean value) {
        this.balkon = value;
    }

    /**
     * Gets the value of the slike property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slike property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlike().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmestajnaJedinica.Slike }
     * 
     * 
     */
    public List<SmestajnaJedinica.Slike> getSlike() {
        if (slike == null) {
            slike = new ArrayList<SmestajnaJedinica.Slike>();
        }
        return this.slike;
    }

    /**
     * Gets the value of the rejting property.
     * 
     * @return
     *     possible object is
     *     {@link Rejting }
     *     
     */
    public Rejting getRejting() {
        return rejting;
    }

    /**
     * Sets the value of the rejting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rejting }
     *     
     */
    public void setRejting(Rejting value) {
        this.rejting = value;
    }

    /**
     * Gets the value of the sObjekat property.
     * 
     * @return
     *     possible object is
     *     {@link SmestajniObjekat }
     *     
     */
    public SmestajniObjekat getSObjekat() {
        return sObjekat;
    }

    /**
     * Sets the value of the sObjekat property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmestajniObjekat }
     *     
     */
    public void setSObjekat(SmestajniObjekat value) {
        this.sObjekat = value;
    }

    /**
     * Gets the value of the komentari property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the komentari property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKomentari().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Komentar }
     * 
     * 
     */
    public List<Komentar> getKomentari() {
        if (komentari == null) {
            komentari = new ArrayList<Komentar>();
        }
        return this.komentari;
    }

    /**
     * Gets the value of the cenovnici property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cenovnici property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCenovnici().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cenovnik }
     * 
     * 
     */
    public List<Cenovnik> getCenovnici() {
        if (cenovnici == null) {
            cenovnici = new ArrayList<Cenovnik>();
        }
        return this.cenovnici;
    }

    /**
     * Gets the value of the podrazumevaniCenovnik property.
     * 
     * @return
     *     possible object is
     *     {@link Cenovnik }
     *     
     */
    public Cenovnik getPodrazumevaniCenovnik() {
        return podrazumevaniCenovnik;
    }

    /**
     * Sets the value of the podrazumevaniCenovnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cenovnik }
     *     
     */
    public void setPodrazumevaniCenovnik(Cenovnik value) {
        this.podrazumevaniCenovnik = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the dodatneUsluge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dodatneUsluge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDodatneUsluge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DodatnaUsluga }
     * 
     * 
     */
    public List<DodatnaUsluga> getDodatneUsluge() {
        if (dodatneUsluge == null) {
            dodatneUsluge = new ArrayList<DodatnaUsluga>();
        }
        return this.dodatneUsluge;
    }

    /**
     * Gets the value of the otkazivanje property.
     * 
     * @return
     *     possible object is
     *     {@link Otkazivanje }
     *     
     */
    public Otkazivanje getOtkazivanje() {
        return otkazivanje;
    }

    /**
     * Sets the value of the otkazivanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Otkazivanje }
     *     
     */
    public void setOtkazivanje(Otkazivanje value) {
        this.otkazivanje = value;
    }

    /**
     * Gets the value of the povecanjeVeciBrojOsoba property.
     * 
     */
    public double getPovecanjeVeciBrojOsoba() {
        return povecanjeVeciBrojOsoba;
    }

    /**
     * Sets the value of the povecanjeVeciBrojOsoba property.
     * 
     */
    public void setPovecanjeVeciBrojOsoba(double value) {
        this.povecanjeVeciBrojOsoba = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>base64Binary">
     *       &lt;attribute name="putanja" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Slike {

        @XmlValue
        protected byte[] value;
        @XmlAttribute(name = "putanja")
        protected String putanja;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setValue(byte[] value) {
            this.value = value;
        }

        /**
         * Gets the value of the putanja property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPutanja() {
            return putanja;
        }

        /**
         * Sets the value of the putanja property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPutanja(String value) {
            this.putanja = value;
        }

    }

}
