//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.16 at 10:02:14 PM CEST 
//


package com.project.megatravel.model.accomodation;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Long>
{


    public Long unmarshal(String value) {
        return (com.project.megatravel.util.MyDatatypeConverter.parseLong(value));
    }

    public String marshal(Long value) {
        return (com.project.megatravel.util.MyDatatypeConverter.printLong(value));
    }

}
