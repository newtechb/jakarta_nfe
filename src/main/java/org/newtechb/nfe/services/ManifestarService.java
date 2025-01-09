/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

/**
 * Emitir uma nota e manifestar sobre ela são duas etapas distintas no processo
 * da Nota Fiscal Eletrônica (NF-e) e da Nota Fiscal de Consumidor Eletrônica
 * (NFC-e).
 *
 **Emitir uma nota** refere-se ao processo de criação e envio do documento
 * fiscal eletrônico para a Secretaria de Fazenda (SEFAZ) para autorização de
 * uso. Isso inclui a geração do arquivo XML da NF-e/NFC-e com todas as
 * informações da operação, a assinatura digital do emitente e o envio para a
 * SEFAZ. * A emissão pode ser feita de forma normal, quando todos os sistemas
 * estão operacionais, ou em contingência, caso haja algum problema técnico que
 * impeça a comunicação com a SEFAZ. Existem diversas modalidades de
 * contingência, cada uma com suas regras específicas, como o uso de Formulário
 * de Segurança (FS) ou a Declaração Prévia de Emissão em Contingência (DPEC).
 *
 **Manifestar sobre uma nota**, por outro lado, é uma ação realizada pelo
 * destinatário da NF-e/NFC-e para registrar sua posição em relação à operação.
 * As opções de manifestação são:
 *
 *   **Confirmação da Operação:** O destinatário confirma que a operação ocorreu
 * e recebeu a mercadoria conforme as informações na NF-e/NFC-e. Essa ação
 * impede o cancelamento da nota pelo emitente. **Ciência da Operação:** O
 * destinatário declara ter ciência da operação, mas ainda não possui
 * informações suficientes para manifestar-se de forma conclusiva.
 * **Desconhecimento da Operação:** O destinatário declara que desconhece a
 * operação descrita na NF-e/NFC-e. **Operação não Realizada:** O destinatário
 * confirma sua participação na operação, mas declara que ela não se efetivou
 * conforme as informações na NF-e/NFC-e.
 *
 * A manifestação do destinatário é um evento importante no processo da
 * NF-e/NFC-e, pois garante maior segurança e transparência nas operações, além
 * de auxiliar na gestão fiscal das empresas. A obrigatoriedade de manifestação
 * varia de acordo com a legislação de cada estado e com o tipo de operação. A
 * manifestação pode ser feita por meio de Web Services, consulta no Portal
 * Nacional da NF-e ou pelo programa Manifestador.
 *
 * @author bajinho
 */
public class ManifestarService {
    /**
     * XML de Envio (Exemplo Hipotético):
     *
     * <ManifestarNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * <tpEvento>210200</tpEvento>
     * <xJust>Manifestação do Destinatário</xJust>
     * <!-- Outras informações da manifestação -->
     * </ManifestarNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● chNFe: Chave de acesso da
     * NF-e. ● tpEvento: Código do tipo de evento. No exemplo, 210200
     * corresponde à "Confirmação da Operação". Outros códigos são: 210210
     * (Ciência da Emissão), 210220 (Desconhecimento da Operação) e 210240
     * (Operação não Realizada). ● xJust: Justificativa da manifestação. ●
     * <!-- Outras informações da manifestação -->: Outras informações podem ser
     * incluídas, conforme o tipo de evento e a implementação específica do
     * serviço. XML de Recebimento (Exemplo Hipotético):
     *
     * <retManifestarNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <cStat>135</cStat>
     * <xMotivo>Manifestação registrada com sucesso</xMotivo>
     * <nProt>123456789012345</nProt>
     * <!-- Outras informações do retorno -->
     * </retManifestarNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● cStat: Código do status da
     * resposta (135 - Evento registrado e vinculado à NF-e). ● xMotivo:
     * Descrição literal do status da resposta. ● nProt: Número do protocolo de
     * registro do evento. ●
     * <!-- Outras informações do retorno -->: Outras informações podem ser
     * retornadas, como a data e hora do registro, informações sobre o evento
     * etc.
     */
}
