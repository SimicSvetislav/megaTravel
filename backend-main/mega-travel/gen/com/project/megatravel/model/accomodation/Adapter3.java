//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 11:51:02 AM CEST 
//


package com.project.megatravel.model.accomodation;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Integer>
{


    public Integer unmarshal(String value) {
        return (com.project.megatravel.util.MyDatatypeConverter.parseInteger(value));
    }

    public String marshal(Integer value) {
        return (com.project.megatravel.util.MyDatatypeConverter.printInteger(value));
    }

}
