//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 11:23:16 PM CEST 
//


package com.project.agentBackend.model.messages.rating.ws.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.project.agentBackend.model.messages.rating.OceneType;


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
 *         &lt;element name="ocene" type="{www.model.megatravel.project.com/messages/rating}OceneType"/>
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
    "ocene"
})
@XmlRootElement(name = "getRatingResponse")
public class GetRatingResponse {

    @XmlElement(required = true)
    protected OceneType ocene;

    /**
     * Gets the value of the ocene property.
     * 
     * @return
     *     possible object is
     *     {@link OceneType }
     *     
     */
    public OceneType getOcene() {
        return ocene;
    }

    /**
     * Sets the value of the ocene property.
     * 
     * @param value
     *     allowed object is
     *     {@link OceneType }
     *     
     */
    public void setOcene(OceneType value) {
        this.ocene = value;
    }

}