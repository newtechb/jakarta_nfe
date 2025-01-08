/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.wsdl.NFeStatusServico4;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

/**
 *
 * @author bajinho
 */
@WebService(targetNamespace = "http://www.portalfiscal.inf.br/nfe/wsdl/NFeStatusServico4")
public interface NFeStatusServico4 {

    @WebMethod
    String nfeStatusServicoNF(@WebParam(name = "nfeDadosMsg") String nfeDadosMsg);
}
