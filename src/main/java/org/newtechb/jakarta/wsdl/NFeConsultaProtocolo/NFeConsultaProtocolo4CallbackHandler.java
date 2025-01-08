/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.jakarta.wsdl.NFeConsultaProtocolo;


import org.newtechb.jakarta.wsdl.NFeAutorizacao.NfeResultMsg;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.Response;


/**
 * Classe de callback NFeConsultaProtocolo4CallbackHandler para JAX-WS.
 * Os usuários podem estender esta classe e implementar seus próprios métodos
 * receiveResult e receiveError.
 *
 * @author bajinho
 */
public abstract class NFeConsultaProtocolo4CallbackHandler implements AsyncHandler<NfeResultMsg> {
    protected Object clientData;

    /**
     * O usuário pode passar qualquer objeto que precisa ser acessado uma vez que a chamada
     * do serviço web não bloqueante seja concluída e o método apropriado deste callback seja chamado.
     * @param clientData Objeto mecanismo pelo qual o usuário pode passar dados do usuário
     * que estarão disponíveis no momento em que este callback for chamado.
     */
    public NFeConsultaProtocolo4CallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Use este construtor se não quiser definir nenhum clientData.
     */
    public NFeConsultaProtocolo4CallbackHandler() {
        this.clientData = null;
    }

    /**
     * Obtém os dados do cliente.
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * Método chamado quando a resposta da operação nfeConsultaNF é recebida.
     * Substitua este método para tratar a resposta normal da operação nfeConsultaNF.
     * 
     * @param result A resposta recebida da operação assíncrona.
     */
    public void receiveResultnfeConsultaNF(NfeResultMsg result) {
        // Implementar tratamento da resposta aqui
    }

    /**
     * Método chamado em caso de falha na operação nfeConsultaNF.
     * Substitua este método para tratar a resposta de erro da operação nfeConsultaNF.
     * 
     * @param e A exceção gerada durante a operação.
     */
    public void receiveErrornfeConsultaNF(Exception e) {
        // Implementar tratamento de erro aqui
    }

    /**
     * Método do AsyncHandler chamado quando a resposta da operação é recebida.
     * 
     * @param response A resposta da operação assíncrona.
     */
    @Override
    public void handleResponse(Response<NfeResultMsg> response) {
        try {
            NfeResultMsg result = response.get();
            receiveResultnfeConsultaNF(result);
        } catch (Exception e) {
            receiveErrornfeConsultaNF(e);
        }
    }
}
