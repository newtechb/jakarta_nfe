package org.newtechb.nfe.wsdl.NFeAutorizacao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Classe que representa a mensagem de dados compactada da NF-e.
 * <p>
 * Esta classe é usada para encapsular os dados de um lote compactado de NF-e em
 * uma estrutura XML. Utiliza as anotações de JAXB para definir como os dados
 * são serializados e desserializados para XML.
 * </p>
 *
 * <p>
 * A classe é anotada com {@link XmlRootElement} para indicar que esta é a raiz
 * do documento XML e {@link XmlAccessorType} para definir a estratégia de
 * acesso aos campos da classe.
 * </p>
 *
 * <p>
 * O autor desta classe é bajinho.
 * </p>
 *
 * @see XmlElement
 */
@XmlRootElement(name = "nfeDadosMsgZip")
@XmlAccessorType(XmlAccessType.FIELD)
public class NfeDadosMsgZip {

    /**
     * Dados do lote compactado de NF-e.
     * <p>
     * Este campo é anotado com {@link XmlElement} para indicar que é um
     * elemento XML requerido e deve ser incluído na serialização.
     * </p>
     */
    @XmlElement(required = true)
    private String nfeDadosMsgZip;

    /**
     * Obtém os dados do lote compactado de NF-e.
     *
     * @return {@link String} Os dados do lote compactado de NF-e.
     */
    public String getNfeDadosMsgZip() {
        return nfeDadosMsgZip;
    }

    /**
     * Define os dados do lote compactado de NF-e.
     *
     * @param nfeDadosMsgZip Os dados do lote compactado de NF-e a serem
     * definidos.
     */
    public void setNfeDadosMsgZip(String nfeDadosMsgZip) {
        this.nfeDadosMsgZip = nfeDadosMsgZip;
    }
}
