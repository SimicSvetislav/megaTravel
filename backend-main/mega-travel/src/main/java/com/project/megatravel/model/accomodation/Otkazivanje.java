//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.16 at 10:02:14 PM CEST 
//


package com.project.megatravel.model.accomodation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TOtkazivanje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TOtkazivanje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dozvoljeno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="brojDana">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOtkazivanje", propOrder = {
    "dozvoljeno",
    "brojDana"
})
public class Otkazivanje {

    @XmlElement(defaultValue = "false")
    protected boolean dozvoljeno;
    @XmlElement(required = true, type = String.class, defaultValue = "0")
    @XmlJavaTypeAdapter(Adapter11 .class)
    protected Integer brojDana;

    /**
     * Gets the value of the dozvoljeno property.
     * 
     */
    public boolean isDozvoljeno() {
        return dozvoljeno;
    }

    /**
     * Sets the value of the dozvoljeno property.
     * 
     */
    public void setDozvoljeno(boolean value) {
        this.dozvoljeno = value;
    }

    /**
     * Gets the value of the brojDana property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getBrojDana() {
        return brojDana;
    }

    /**
     * Sets the value of the brojDana property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojDana(Integer value) {
        this.brojDana = value;
    }

}
