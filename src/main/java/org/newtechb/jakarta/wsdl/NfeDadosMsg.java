/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.jakarta.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAnyElement;
import org.w3c.dom.Element;

/**
 *
 * @author bajinho
 */
@XmlRootElement(name = "nfeDadosMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class NfeDadosMsg {
    
    @XmlAnyElement(lax = true)
    private Element extraElement;

    public Element getExtraElement() {
        return extraElement;
    }

    public void setExtraElement(Element extraElement) {
        this.extraElement = extraElement;
    }
}
