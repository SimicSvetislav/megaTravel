//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.29 at 11:59:36 PM CEST 
//


package com.project.megatravel.model.messages.agent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.project.megatravel.model.reservations.RezervacijaKorisnika;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="rezervacijaKorisnika" type="{www.model.megatravel.project.com/reservations}TRezervacijaKorisnika"/>
 *         &lt;element name="rezervacijaAgenta" type="{www.model.megatravel.project.com/reservations}TRezervacija"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rezervacijaKorisnika",
    "rezervacijaAgenta"
})
@XmlRootElement(name = "Rezervacija")
public class Rezervacija {

    protected RezervacijaKorisnika rezervacijaKorisnika;
    protected com.project.megatravel.model.reservations.Rezervacija rezervacijaAgenta;

    /**
     * Gets the value of the rezervacijaKorisnika property.
     * 
     * @return
     *     possible object is
     *     {@link RezervacijaKorisnika }
     *     
     */
    public RezervacijaKorisnika getRezervacijaKorisnika() {
        return rezervacijaKorisnika;
    }

    /**
     * Sets the value of the rezervacijaKorisnika property.
     * 
     * @param value
     *     allowed object is
     *     {@link RezervacijaKorisnika }
     *     
     */
    public void setRezervacijaKorisnika(RezervacijaKorisnika value) {
        this.rezervacijaKorisnika = value;
    }

    /**
     * Gets the value of the rezervacijaAgenta property.
     * 
     * @return
     *     possible object is
     *     {@link com.project.megatravel.model.reservations.Rezervacija }
     *     
     */
    public com.project.megatravel.model.reservations.Rezervacija getRezervacijaAgenta() {
        return rezervacijaAgenta;
    }

    /**
     * Sets the value of the rezervacijaAgenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.project.megatravel.model.reservations.Rezervacija }
     *     
     */
    public void setRezervacijaAgenta(com.project.megatravel.model.reservations.Rezervacija value) {
        this.rezervacijaAgenta = value;
    }

}
