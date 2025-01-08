package org.newtechb.nfe.wsdl.NFeAutorizacao;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Element;

/**
 * Classe que representa a mensagem de resultado da NF-e.
 * <p>
 * Esta classe é usada para encapsular os resultados de uma operação de NF-e em
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
 * @see Element
 */
@XmlRootElement(name = "nfeResultMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class NfeResultMsg {

    /**
     * Elemento XML adicional que pode ser qualquer elemento XML.
     * <p>
     * Este campo é anotado com {@link XmlAnyElement} para indicar que pode
     * conter qualquer elemento XML. A propriedade lax = true permite que o
     * elemento seja convertido para um tipo específico se o JAXB conhecer o
     * tipo.
     * </p>
     */
    @XmlAnyElement(lax = true)
    private Element extraElement;

    /**
     * Obtém o elemento extra.
     *
     * @return {@link Element} O elemento extra contido nesta mensagem de
     * resultado.
     */
    public Element getExtraElement() {
        return extraElement;
    }

    /**
     * Define o elemento extra.
     *
     * @param extraElement O elemento extra a ser definido nesta mensagem de
     * resultado.
     */
    public void setExtraElement(Element extraElement) {
        this.extraElement = extraElement;
    }
}
