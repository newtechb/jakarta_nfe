/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.jakarta.services;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.newtechb.jakarta.wsdl.NfeDadosMsg;
import org.newtechb.jakarta.wsdl.NfeDadosMsgZip;
import org.newtechb.jakarta.wsdl.NfeResultMsg;

/**
 *
 * @author bajinho
 */
@WebService
public interface NFeAutorizacaoService {

    @WebMethod
    NfeResultMsg nfeAutorizacaoLote(NfeDadosMsg nfeDadosMsg) throws Exception;

    @WebMethod
    NfeResultMsg nfeAutorizacaoLoteZip(NfeDadosMsgZip nfeDadosMsgZip) throws Exception;
}
