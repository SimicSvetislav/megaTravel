//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.26 at 08:14:28 AM CEST 
//


package com.project.megatravel.model.reservations;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter6
    extends XmlAdapter<String, Long>
{


    public Long unmarshal(String value) {
        return (com.project.megatravel.util.MyDatatypeConverter.parseLong(value));
    }

    public String marshal(Long value) {
        return (com.project.megatravel.util.MyDatatypeConverter.printLong(value));
    }

}
