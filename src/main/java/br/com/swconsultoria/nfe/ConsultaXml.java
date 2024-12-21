package br.com.swconsultoria.nfe;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.*;
import br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocolo.NFeConsultaProtocolo4Stub;
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
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 *
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
@Log
class ConsultaXml {

    private static final Logger log = LoggerFactory.getLogger(ConsultaXml.class);
    
    private ConsultaXml() {}

    /**
     * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
     *
     * @param chave
     * @param tipoDocumento
     * @return
     * @throws NfeException
     */
    static TRetConsSitNFe consultaXml(ConfiguracoesNfe config, String chave, DocumentoEnum tipoDocumento) throws NfeException {

        try {

            TConsSitNFe consSitNFe = new TConsSitNFe();
            consSitNFe.setVersao(ConstantesUtil.VERSAO.NFE);
            consSitNFe.setTpAmb(config.getAmbiente().getCodigo());
            consSitNFe.setXServ("CONSULTAR");
            consSitNFe.setChNFe(chave);

            String xml = XmlNfeUtil.objectToXml(consSitNFe, config.getEncode());

            log.info("[XML-ENVIO]: " + xml);

            OMElement ome = AXIOMUtil.stringToOM(xml);

            String url = WebServiceUtil.getUrl(config, tipoDocumento, ServicosEnum.CONSULTA_XML);
            if (EstadosEnum.MS.equals(config.getEstado())) {
                br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocoloMS.NFeConsultaProtocolo4Stub.NfeDadosMsg dadosMsg = new br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocoloMS.NFeConsultaProtocolo4Stub.NfeDadosMsg();
                dadosMsg.setExtraElement(ome);

                br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocoloMS.NFeConsultaProtocolo4Stub stub =
                        new br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocoloMS.NFeConsultaProtocolo4Stub(url);

                StubUtil.configuraHttpClient(stub, config, url);

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                            config.getTimeout());
                }
                br.com.swconsultoria.nfe.wsdl.NFeConsultaProtocoloMS.NFeConsultaProtocolo4Stub.NfeResultMsg result = stub.nfeConsultaNF(dadosMsg);

                log.info("[XML-RETORNO]: " + result.getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetConsSitNFe.class);
            } else {
                NFeConsultaProtocolo4Stub.NfeDadosMsg dadosMsg = new NFeConsultaProtocolo4Stub.NfeDadosMsg();
                dadosMsg.setExtraElement(ome);

                NFeConsultaProtocolo4Stub stub = new NFeConsultaProtocolo4Stub(
                        url);

                StubUtil.configuraHttpClient(stub, config, url);

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                            config.getTimeout());
                }
                NFeConsultaProtocolo4Stub.NfeResultMsg result = stub.nfeConsultaNF(dadosMsg);

                log.info("[XML-RETORNO]: " + result.getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetConsSitNFe.class);
            }

        } catch (RemoteException | XMLStreamException | JAXBException | CertificadoException e) {
            throw new NfeException(e.getMessage(), e);
        }

    }

}
