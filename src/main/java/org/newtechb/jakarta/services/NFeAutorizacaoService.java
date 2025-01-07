package org.newtechb.jakarta.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.newtechb.jakarta.wsdl.NfeDadosMsg;
import org.newtechb.jakarta.wsdl.NfeDadosMsgZip;
import org.newtechb.jakarta.wsdl.NfeResultMsg;

/**
 * Interface para o serviço web de autorização de NF-e. Esta interface define os
 * métodos que serão expostos como operações SOAP para autorizar lotes de NF-e.
 *
 * <p>
 * A interface utiliza Jakarta XML Web Services (JAX-WS) para definir serviços
 * web SOAP.
 * </p>
 *
 * <p>
 * Cada método nesta interface é exposto como uma operação SOAP através da
 * anotação {@link WebMethod}.
 * </p>
 *
 * <p>
 * As classes {@link NfeDadosMsg}, {@link NfeDadosMsgZip} e {@link NfeResultMsg}
 * são utilizadas como parâmetros e retornos dos métodos, representando as
 * mensagens de dados e resultados da autorização de NF-e.
 * </p>
 *
 * <p>
 * O autor deste código é bajinho.
 * </p>
 *
 * @see NfeDadosMsg
 * @see NfeDadosMsgZip
 * @see NfeResultMsg
 */
@WebService
public interface NFeAutorizacaoService {

    /**
     * Método para autorizar um lote de NF-e.
     *
     * <p>
     * Esta operação SOAP recebe os dados de um lote de NF-e, processa a
     * autorização e retorna o resultado.
     * </p>
     *
     * @param nfeDadosMsg O objeto que contém os dados do lote de NF-e.
     * @return {@link NfeResultMsg} O resultado do processamento do lote de
     * NF-e.
     * @throws Exception Se ocorrer um erro durante o processamento.
     */
    @WebMethod
    NfeResultMsg nfeAutorizacaoLote(NfeDadosMsg nfeDadosMsg) throws Exception;

    /**
     * Método para autorizar um lote compactado de NF-e.
     *
     * <p>
     * Esta operação SOAP recebe os dados de um lote compactado de NF-e,
     * processa a autorização e retorna o resultado.
     * </p>
     *
     * @param nfeDadosMsgZip O objeto que contém os dados do lote compactado de
     * NF-e.
     * @return {@link NfeResultMsg} O resultado do processamento do lote
     * compactado de NF-e.
     * @throws Exception Se ocorrer um erro durante o processamento.
     */
    @WebMethod
    NfeResultMsg nfeAutorizacaoLoteZip(NfeDadosMsgZip nfeDadosMsgZip) throws Exception;
}
