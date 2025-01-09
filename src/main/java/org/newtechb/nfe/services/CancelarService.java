/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

/**
 *
 * @author bajinho
 */
public class CancelarService {
    /**
     * Para exemplificar como a estrutura XML funcionaria para um serviço
     * CancelarService, vamos considerar um cenário similar ao NfeCancelamento.
     * XML de Envio (Exemplo Hipotético):
     *
     * <CancelarNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * <nProt>123456789012345</nProt>
     * <xJust>Justificativa do cancelamento</xJust>
     * </CancelarNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● chNFe: Chave de acesso da
     * NF-e a ser cancelada. ● nProt: Número do protocolo de autorização da
     * NF-e. ● xJust: Justificativa do cancelamento. XML de Recebimento (Exemplo
     * Hipotético):
     *
     * <retCancelarNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <cStat>101</cStat>
     * <xMotivo>Cancelamento de NF-e homologado</xMotivo>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * <nProt>123456789012346</nProt>
     * </retCancelarNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● cStat: Código do status da
     * resposta (101 - Cancelamento de NF-e homologado). ● xMotivo: Descrição
     * literal do status da resposta. ● chNFe: Chave de acesso da NF-e
     * cancelada. ● nProt: Número do protocolo de cancelamento.
     */
}
