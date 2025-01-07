/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.jakarta.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bajinho
 */
@XmlRootElement(name = "nfeDadosMsgZip")
@XmlAccessorType(XmlAccessType.FIELD)
public class NfeDadosMsgZip {
    
    @XmlElement(required = true)
    private String nfeDadosMsgZip;

    public String getNfeDadosMsgZip() {
        return nfeDadosMsgZip;
    }

    public void setNfeDadosMsgZip(String nfeDadosMsgZip) {
        this.nfeDadosMsgZip = nfeDadosMsgZip;
    }
}
