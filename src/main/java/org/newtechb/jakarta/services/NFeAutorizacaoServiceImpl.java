/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.jakarta.services;

import jakarta.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newtechb.jakarta.wsdl.NfeDadosMsg;
import org.newtechb.jakarta.wsdl.NfeDadosMsgZip;
import org.newtechb.jakarta.wsdl.NfeResultMsg;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 *
 * Implementação do serviço web para autorização de NFe. Esta classe implementa
 * a interface NFeAutorizacaoService e define os métodos para tratar requisições
 * de autorização de lotes de NF-e e lotes compactados de NF-e.
 *
 * @author bajinho
 */
@WebService(endpointInterface = "br.com.swconsultoria.nfe.wsdl.NFeAutorizacaoService")
public class NFeAutorizacaoServiceImpl implements NFeAutorizacaoService {

    /**
     * Método que processa a autorização de um lote de NF-e.
     *
     * @param nfeDadosMsg O objeto que contém os dados do lote de NF-e.
     * @return NfeResultMsg O resultado do processamento do lote de NF-e.
     * @throws Exception Se ocorrer um erro durante o processamento.
     */
    @Override
    public NfeResultMsg nfeAutorizacaoLote(NfeDadosMsg nfeDadosMsg) throws Exception {
        // Cria um novo objeto NfeResultMsg para armazenar o resultado
        NfeResultMsg result = new NfeResultMsg();
        // Define o elemento extra do resultado com base no elemento extra dos dados da NF-e
        result.setExtraElement(nfeDadosMsg.getExtraElement());
        return result;
    }

    /**
     * Método que processa a autorização de um lote compactado de NF-e.
     *
     * @param nfeDadosMsgZip O objeto que contém os dados do lote compactado de
     * NF-e.
     * @return NfeResultMsg O resultado do processamento do lote compactado de
     * NF-e.
     * @throws Exception Se ocorrer um erro durante o processamento.
     */
    @Override
    public NfeResultMsg nfeAutorizacaoLoteZip(NfeDadosMsgZip nfeDadosMsgZip) throws Exception {
        // Cria um novo objeto NfeResultMsg para armazenar o resultado
        NfeResultMsg result = new NfeResultMsg();
        // Converte a string XML do lote compactado em um objeto Element
        Element element = convertStringToElement(nfeDadosMsgZip.getNfeDadosMsgZip());
        // Define o elemento extra do resultado com base no elemento convertido
        result.setExtraElement(element);
        return result;
    }

    /**
     * Método auxiliar que converte uma string XML em um objeto Element.
     *
     * @param xmlString A string XML a ser convertida.
     * @return Element O objeto Element resultante da conversão.
     * @throws Exception Se ocorrer um erro durante a conversão.
     */
    private Element convertStringToElement(String xmlString) throws Exception {
        // Cria um DocumentBuilderFactory e um DocumentBuilder para analisar a string XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Analisa a string XML e cria um objeto Document
        Document document = builder.parse(new java.io.ByteArrayInputStream(xmlString.getBytes()));
        // Retorna o elemento raiz do documento
        return document.getDocumentElement();
    }
}
