/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

/**
 * ExportarXmlNFe e ImportarXmlNFe: Permite a exportação e importação de
 * arquivos XML da NF-e. Essa funcionalidade é útil para a transmissão de notas
 * fiscais emitidas em contingência off-line ou para a integração com outros
 * sistemas.
 *
 * @author bajinho
 */
public class ExportarXmlNFeService {
    /**
     * XML de Envio (Exemplo Hipotético):
     *
     * <ExportarXmlNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * </ExportarXmlNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● chNFe: Chave de acesso da
     * NF-e. O XML de recebimento conteria o XML da NF-e, possivelmente
     * encapsulado em uma tag específica, como nos exemplos anteriores. XML de
     * Recebimento (Exemplo Hipotético):
     *
     * <retExportarXmlNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <cStat>138</cStat>
     * <xMotivo>Exportação realizada com sucesso</xMotivo>
     * <xmlNFe>
     * <![CDATA[Conteúdo do XML da NF-e]]>
     * </xmlNFe>
     * </retExportarXmlNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● cStat: Código do status da
     * resposta (138 - XML da NF-e retornado com sucesso, de acordo com a tabela
     * 5.2). ● xMotivo: Descrição literal do status da resposta. ● xmlNFe: Tag
     * contendo o conteúdo do XML da NF-e.
     */
}
