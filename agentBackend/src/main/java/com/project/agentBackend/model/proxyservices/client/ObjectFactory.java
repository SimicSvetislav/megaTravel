
package com.project.agentBackend.model.proxyservices.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.project.megatravel.model.proxyservices.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestClient_QNAME = new QName("http://model.megatravel.project.com/proxyServices/client", "testClient");
    private final static QName _TestClientResponse_QNAME = new QName("http://model.megatravel.project.com/proxyServices/client", "testClientResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.project.megatravel.model.proxyservices.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.megatravel.project.com/proxyServices/client", name = "testClient")
    public JAXBElement<String> createTestClient(String value) {
        return new JAXBElement<String>(_TestClient_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.megatravel.project.com/proxyServices/client", name = "testClientResponse")
    public JAXBElement<String> createTestClientResponse(String value) {
        return new JAXBElement<String>(_TestClientResponse_QNAME, String.class, null, value);
    }

}
