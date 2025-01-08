/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.wsdl.NFeStatusServico4;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.soap.Addressing;
import jakarta.xml.ws.soap.MTOM;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;


/**
 * NFeStatusServico4Service for Jakarta XML Web Services (JAX-WS).
 *
 *
 * @author bajinho
 */
@WebServiceClient(
    name = "NFeStatusServico4Service",
    targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeStatusServico4",
    wsdlLocation = "https://nfe.fazenda.sp.gov.br/ws/nfestatusservico4.asmx?wsdl"
)
@MTOM
@Addressing
public class NFeStatusServico4Service extends Service {

    public NFeStatusServico4Service(URL wsdlLocation) {
        super(wsdlLocation, new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NFeStatusServico4", "NFeStatusServico4Service"));
    }

    public NFeStatusServico4Service() {
        super(getWsdlLocation(), new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NFeStatusServico4", "NFeStatusServico4Service"));
    }

    private static URL getWsdlLocation() {
        URL url = null;
        try {
            url = new URL("https://nfe.fazenda.sp.gov.br/ws/nfestatusservico4.asmx?wsdl");
        } catch (Exception e) {
            throw new WebServiceException("Failed to create URL for the WSDL Location: " + e.getMessage(), e);
        }
        return url;
    }

    // Methods to marshal and unmarshal XML
    private Object fromXML(String xml, Class<?> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(new StringReader(xml));
    }

    private String toXML(Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);
        return sw.toString();
    }

    // Example method to call the web service
    public TRetConsStatServ nfeStatusServicoNF(TConsStatServ dadosMsg) throws Exception {
        // Create the web service client
        NFeStatusServico4 port = getPort(NFeStatusServico4.class);

        // Marshal the request object to XML
        String xmlRequest = toXML(dadosMsg);

        // Call the web service
        String xmlResponse = port.nfeStatusServicoNF(xmlRequest);

        // Unmarshal the response XML to an object
        return (TRetConsStatServ) fromXML(xmlResponse, TRetConsStatServ.class);
    }

    @Override
    public <T> T getPort(Class<T> serviceEndpointInterface) {
        return super.getPort(new QName("http://www.portalfiscal.inf.br/nfe/wsdl/NFeStatusServico4", "NFeStatusServico4Port"), serviceEndpointInterface);
    }
}
