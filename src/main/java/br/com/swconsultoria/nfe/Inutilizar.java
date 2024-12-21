package br.com.swconsultoria.nfe;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AssinaturaEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.StubUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.NFeInutilizacao4Stub;
import lombok.extern.java.Log;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe Responsavel por inutilizar uma Faixa de numeracao da Nfe.
 *
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
@Log
class Inutilizar {
    
    private static final Logger log = LoggerFactory.getLogger(Inutilizar.class);

    private Inutilizar() {
    }

    static TRetInutNFe inutiliza(ConfiguracoesNfe config, TInutNFe inutNFe, DocumentoEnum tipoDocumento, boolean validar)
            throws NfeException {

        try {

            String xml = XmlNfeUtil.objectToXml(inutNFe, config.getEncode());
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = Assinar.assinaNfe(config, xml, AssinaturaEnum.INUTILIZACAO);

            log.info("[XML-ENVIO]: " + xml);

            if (validar) {
                new Validar().validaXml(config, xml, ServicosEnum.INUTILIZACAO);
            }

            OMElement ome = AXIOMUtil.stringToOM(xml);

            String url = WebServiceUtil.getUrl(config, tipoDocumento, ServicosEnum.INUTILIZACAO);
            if (EstadosEnum.CE.equals(config.getEstado()) ) {
                br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.ce.NFeInutilizacao4Stub.NfeDadosMsg dadosMsgCe =
                        new  br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.ce.NFeInutilizacao4Stub.NfeDadosMsg();
                dadosMsgCe.setExtraElement(ome);
                br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.ce.NFeInutilizacao4Stub stubCe = new br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.ce.NFeInutilizacao4Stub(
                        url);
                StubUtil.configuraHttpClient(stubCe, config, url);

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stubCe._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stubCe._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
                }
                br.com.swconsultoria.nfe.wsdl.NFeInutilizacao.ce.NFeInutilizacao4Stub.NfeResultMsg resultCe = stubCe.nfeInutilizacaoNF(dadosMsgCe);

                log.info("[XML-RETORNO]: " + resultCe.getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(resultCe.getExtraElement().toString(), TRetInutNFe.class);
            } else{
                NFeInutilizacao4Stub.NfeDadosMsg dadosMsg = new NFeInutilizacao4Stub.NfeDadosMsg();
                dadosMsg.setExtraElement(ome);
                NFeInutilizacao4Stub stub = new NFeInutilizacao4Stub(
                        url);

                StubUtil.configuraHttpClient(stub, config, url);

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
                }
                NFeInutilizacao4Stub.NfeResultMsg result = stub.nfeInutilizacaoNF(dadosMsg);

                log.info("[XML-RETORNO]: " + result.getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetInutNFe.class);
            }

        } catch (RemoteException | XMLStreamException | JAXBException | CertificadoException e) {
            throw new NfeException(e.getMessage(),e);
        }

    }

}
