/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.service;

/**
 *
 * @author bajinho
 */
public class CartaCorrecaoTeste {

    public static void main(String[] args) {

        try {
            // Inicia As Configurações - ver https://github.com/Samuel-Oliveira/Java_NFe/wiki/1-:-Configuracoes
            ConfiguracoesNfe config = Config.iniciaConfiguracoes();

            //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
            //Para isso Foi criado o Objeto Cancela
            Evento cce = new Evento();
            //Informe a chave da Nota a ser feita a CArta de Correção
            cce.setChave("XXX");
            //Informe o CNPJ do emitente
            cce.setCnpj("XXX");
            //Informe o Texto da Carta de Correção
            cce.setMotivo("Teste de Carta de Correção");
            //Informe a data da Carta de Correção
            cce.setDataEvento(LocalDateTime.now());
            //Informe a sequencia do Evento
            cce.setSequencia(1);

            // Monta o Evento
            TEnvEvento envEvento = CartaCorrecaoUtil.montaCCe(cce,config);

            //Envia a CCe
            TRetEnvEvento retorno = Nfe.cce(config, envEvento, true);

            //Valida o Retorno do Carta de Correção
            RetornoUtil.validaCartaCorrecao(retorno);

            //Resultado
            System.out.println();
            retorno.getRetEvento().forEach( resultado -> {
                System.out.println("# Chave: " + resultado.getInfEvento().getChNFe());
                System.out.println("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
                System.out.println("# Protocolo: " + resultado.getInfEvento().getNProt());
            });

            //Cria ProcEvento da CCe
            String proc = CartaCorrecaoUtil.criaProcEventoCCe(config, envEvento, retorno);
            System.out.println();
            System.out.println("# ProcEvento : " + proc);

        } catch (Exception e) {
            System.err.println();
            System.err.println(e.getMessage());
        }

    }

}
