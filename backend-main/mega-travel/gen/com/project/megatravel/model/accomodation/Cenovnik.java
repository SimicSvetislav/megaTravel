//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.21 at 08:16:49 PM CEST 
//


package com.project.megatravel.model.accomodation;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TCenovnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TCenovnik">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pocetak">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}date">
 *               &lt;minInclusive value="1900-01-01"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="kraj">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}date">
 *               &lt;minInclusive value="1900-01-01"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cena">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="smestaj">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="1"/>
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
@XmlType(name = "TCenovnik", propOrder = {
    "pocetak",
    "kraj",
    "cena",
    "smestaj"
})
public class Cenovnik {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter7 .class)
    protected Date pocetak;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter8 .class)
    protected Date kraj;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter9 .class)
    protected Long cena;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter10 .class)
    protected Long smestaj;

    /**
     * Gets the value of the pocetak property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPocetak() {
        return pocetak;
    }

    /**
     * Sets the value of the pocetak property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPocetak(Date value) {
        this.pocetak = value;
    }

    /**
     * Gets the value of the kraj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getKraj() {
        return kraj;
    }

    /**
     * Sets the value of the kraj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKraj(Date value) {
        this.kraj = value;
    }

    /**
     * Gets the value of the cena property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getCena() {
        return cena;
    }

    /**
     * Sets the value of the cena property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCena(Long value) {
        this.cena = value;
    }

    /**
     * Gets the value of the smestaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getSmestaj() {
        return smestaj;
    }

    /**
     * Sets the value of the smestaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmestaj(Long value) {
        this.smestaj = value;
    }

}
