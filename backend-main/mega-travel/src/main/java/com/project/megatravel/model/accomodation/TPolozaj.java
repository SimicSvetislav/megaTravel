//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 01:57:01 AM CEST 
//


package com.project.megatravel.model.accomodation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TPolozaj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPolozaj">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stepeni">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="180"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="strana">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="N"/>
 *               &lt;enumeration value="S"/>
 *               &lt;enumeration value="W"/>
 *               &lt;enumeration value="E"/>
 *               &lt;length value="1"/>
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
@XmlType(name = "TPolozaj", propOrder = {
    "stepeni",
    "strana"
})
public class TPolozaj {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter6 .class)
    protected Double stepeni;
    @XmlElement(required = true)
    protected String strana;

    /**
     * Gets the value of the stepeni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getStepeni() {
        return stepeni;
    }

    /**
     * Sets the value of the stepeni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStepeni(Double value) {
        this.stepeni = value;
    }

    /**
     * Gets the value of the strana property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrana() {
        return strana;
    }

    /**
     * Sets the value of the strana property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrana(String value) {
        this.strana = value;
    }

}
