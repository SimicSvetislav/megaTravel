//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.29 at 08:15:08 PM CEST 
//


package com.project.megatravel.model.reservations.managment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{www.model.megatravel.project.com/reservations}RezervacijaKorisnika"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rezervacijaKorisnika"
})
@XmlRootElement(name = "getUpcomingBookingsResponse")
public class GetUpcomingBookingsResponse {

    @XmlElement(name = "RezervacijaKorisnika", namespace = "www.model.megatravel.project.com/reservations")
    protected List<RezervacijaKorisnika> rezervacijaKorisnika;

    /**
     * Gets the value of the rezervacijaKorisnika property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rezervacijaKorisnika property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRezervacijaKorisnika().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RezervacijaKorisnika }
     * 
     * 
     */
    public List<RezervacijaKorisnika> getRezervacijaKorisnika() {
        if (rezervacijaKorisnika == null) {
            rezervacijaKorisnika = new ArrayList<RezervacijaKorisnika>();
        }
        return this.rezervacijaKorisnika;
    }

}
