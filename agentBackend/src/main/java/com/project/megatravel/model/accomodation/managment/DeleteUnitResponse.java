//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.29 at 07:10:25 PM CEST 
//


package com.project.megatravel.model.accomodation.managment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.project.megatravel.model.accomodation.SmestajnaJedinica;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{www.model.megatravel.project.com/accomodation}SmestajnaJedinica"/>
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
    "smestajnaJedinica"
})
@XmlRootElement(name = "deleteUnitResponse")
public class DeleteUnitResponse {

    @XmlElement(name = "SmestajnaJedinica", namespace = "www.model.megatravel.project.com/accomodation", required = true)
    protected SmestajnaJedinica smestajnaJedinica;

    /**
     * Gets the value of the smestajnaJedinica property.
     * 
     * @return
     *     possible object is
     *     {@link SmestajnaJedinica }
     *     
     */
    public SmestajnaJedinica getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    /**
     * Sets the value of the smestajnaJedinica property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmestajnaJedinica }
     *     
     */
    public void setSmestajnaJedinica(SmestajnaJedinica value) {
        this.smestajnaJedinica = value;
    }

}
