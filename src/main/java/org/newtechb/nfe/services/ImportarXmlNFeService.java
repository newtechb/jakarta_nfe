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
public class ImportarXmlNFeService {
    /**
     * XML de Envio (Exemplo Hipotético):
     *
     * <ImportarXmlNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <xmlNFe>
     * <![CDATA[Conteúdo do XML da NF-e a ser importada]]>
     * </xmlNFe>
     * </ImportarXmlNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● xmlNFe: Conteúdo do XML da
     * NF-e a ser importada. O XML de recebimento poderia confirmar o sucesso da
     * importação e fornecer informações adicionais, como a chave de acesso da
     * NF-e importada. XML de Recebimento (Exemplo Hipotético):
     *
     * <retImportarXmlNFe versao="1.00">
     * <tpAmb>1</tpAmb>
     * <cStat>100</cStat>
     * <xMotivo>NF-e importada com sucesso</xMotivo>
     * <chNFe>35230312345678901234567890123456789012345678</chNFe>
     * </retImportarXmlNFe>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● cStat: Código do status da
     * resposta. ● xMotivo: Descrição literal do status da resposta. ● chNFe:
     * Chave de acesso da NF-e importada.
     */
}
