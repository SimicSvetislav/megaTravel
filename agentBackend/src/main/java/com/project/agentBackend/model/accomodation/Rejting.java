//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.29 at 11:59:37 PM CEST 
//


package com.project.agentBackend.model.accomodation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TRejting complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TRejting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brojOcena">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="sumaOcena">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
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
@XmlType(name = "TRejting", propOrder = {
    "brojOcena",
    "sumaOcena"
})
public class Rejting {

    protected long brojOcena;
    protected long sumaOcena;

    /**
     * Gets the value of the brojOcena property.
     * 
     */
    public long getBrojOcena() {
        return brojOcena;
    }

    /**
     * Sets the value of the brojOcena property.
     * 
     */
    public void setBrojOcena(long value) {
        this.brojOcena = value;
    }

    /**
     * Gets the value of the sumaOcena property.
     * 
     */
    public long getSumaOcena() {
        return sumaOcena;
    }

    /**
     * Sets the value of the sumaOcena property.
     * 
     */
    public void setSumaOcena(long value) {
        this.sumaOcena = value;
    }

}