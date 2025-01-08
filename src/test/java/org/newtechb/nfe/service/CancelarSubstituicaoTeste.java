/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.service;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.schema.envEventoCancSubst.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.CancelamentoSubstituicaoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;

import java.time.LocalDateTime;

/**
 *
 * @author bajinho
 */
public class CancelarSubstituicaoTeste {

    public static void main(String[] args) {
        try {
            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            ConfiguracoesNfe config = Config.iniciaConfiguracoes();

            //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
            //Para isso Foi criado o Objeto Cancela
            Evento cancela = new Evento();
            //Informe a chave da Nota a ser Cancelada
            cancela.setChave("XXX");
            //Informe a chave da Nota a Substituta
            cancela.setChaveSusbstituta("XXX");
            //Informe o protocolo da Nota a ser Cancelada
            cancela.setProtocolo("XXX");
            //Informe o CNPJ do emitente
            cancela.setCnpj("XXX");
            //Informe o Motivo do Cancelamento
            cancela.setMotivo("Teste de Cancelamento");
            //Informe a data do Cancelamento
            cancela.setDataEvento(LocalDateTime.now());

            //Monta o Evento de Cancelamento
            TEnvEvento enviEvento = CancelamentoSubstituicaoUtil.montaCancelamento(cancela, config);

            //Envia o Evento de Cancelamento
            TRetEnvEvento retorno = Nfe.cancelarSubstituicaoNfe(config, enviEvento, true);

            //Valida o Retorno do Cancelamento
            RetornoUtil.validaCancelamentoSubstituicao(retorno);

            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach( resultado -> {
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });

            //Cria ProcEvento de Cacnelamento
            String proc = CancelamentoSubstituicaoUtil.criaProcEventoCancelamento(config, enviEvento, retorno.getRetEvento().get(0));
            System.out.println();
            System.out.println("# ProcEvento : " + proc);

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: "+e.getMessage());
        }
    }
}
