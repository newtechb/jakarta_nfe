/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

/**
 * RegistrarEvento: Permite o registro de eventos relacionados à NF-e, como a
 * Confirmação da Operação pelo Destinatário, Ciência da Operação,
 * Desconhecimento da Operação, Operação não Realizada, além de eventos como
 * Carta de Correção e EPEC (Evento Prévio de Emissão em Contingência). Esse
 * serviço é crucial para garantir a rastreabilidade e a transparência das
 * operações.
 *
 * @author bajinho
 */
public class RegistrarEventoService {
    /**
     * XML de Envio (Exemplo Hipotético):
     *
     * <RegistrarEvento versao="1.00">
     * <tpAmb>1</tpAmb>
     * <idLote>1</idLote>
     * <evento>
     * <infEvento Id="ID11011012345678901234567890123456789012345678901">
     * <cOrgao>35</cOrgao>
     * <tpAmb>1</tpAmb>
     * <CNPJ>12345678901234</CNPJ>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * <dhEvento>2023-10-26T14:38:00-03:00</dhEvento>
     * <tpEvento>110110</tpEvento>
     * <nSeqEvento>1</nSeqEvento>
     * <verEvento>1.00</verEvento>
     * <detEvento versao="1.00">
     * <descEvento>Carta de Correção</descEvento>
     * <xCorrecao>Correção da descrição do produto</xCorrecao>
     * </detEvento>
     * </infEvento>
     * </evento>
     * </RegistrarEvento>
     *
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● idLote: Identificador do lote
     * de eventos. ● evento: Informações do evento. ○ infEvento: Grupo com as
     * informações do evento. ■ Id: Identificador único do evento. ■ cOrgao:
     * Código do órgão de registro do evento. ■ tpAmb: Identificação do
     * ambiente. ■ CNPJ: CNPJ do autor do evento. ■ chNFe: Chave de acesso da
     * NF-e. ■ dhEvento: Data e hora do evento. ■ tpEvento: Código do tipo de
     * evento (exemplo: 110110 - Carta de Correção). Consulte a Tabela 3-1 do
     * documento "Manual de Orientação ao Contribuinte - MOC - versão 7.0 - NF-e
     * e NFC-e.pdf" para outros códigos. ■ nSeqEvento: Número sequencial do
     * evento para o mesmo tipo de evento. ■ verEvento: Versão do leiaute do
     * evento. ■ detEvento: Detalhes do evento. ● descEvento: Descrição do
     * evento. ●
     * <!-- Informações específicas do evento -->: Demais informações
     * específicas do evento, conforme o tipo de evento. XML de Recebimento
     * (Exemplo Hipotético):
     *
     * <retRegistrarEvento versao="1.00">
     * <tpAmb>1</tpAmb>
     * <cStat>135</cStat>
     * <xMotivo>Evento registrado e vinculado a NF-e</xMotivo>
     * <infEvento>
     * <tpEvento>110110</tpEvento>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * <dhRegEvento>2023-10-26T14:39:00-03:00</dhRegEvento>
     * <nProt>123456789012345</nProt>
     * </infEvento>
     * </retRegistrarEvento>
     *
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● cStat: Código do status da
     * resposta (exemplo: 135 - Evento registrado e vinculado à NF-e). ●
     * xMotivo: Descrição literal do status da resposta. ● infEvento:
     * Informações do evento registrado. ○ tpEvento: Código do tipo de evento. ○
     * chNFe: Chave de acesso da NF-e. ○ dhRegEvento: Data e hora do registro do
     * evento. ○ nProt: Número do protocolo de registro do evento.
     */
}
