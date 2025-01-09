/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

/**
 * ConsultarDistribuicaoDFe: Permite a consulta de NF-e e outros documentos
 * fiscais eletrônicos de interesse do contribuinte. Essa consulta pode ser
 * feita por diferentes critérios, como chave de acesso, CNPJ/CPF do interessado
 * ou número sequencial único (NSU).
 *
 * @author bajinho
 */
public class ConsultarDistribuicaoDFeService {
    /**
     * Para ilustrar como a estrutura XML funcionaria para um serviço
     * ConsultarDistribuicaoDFeService, vamos considerar um cenário similar ao
     * NFeDistribuicaoDFe. XML de Envio (Exemplo Hipotético):
     *
     * <ConsultarDistribuicaoDFe versao="1.01">
     * <tpAmb>1</tpAmb>
     * <cUFAutor>SP</cUFAutor>
     * <CNPJ>12345678901234</CNPJ>
     * <ultNSU>12345</ultNSU>
     * </ConsultarDistribuicaoDFe>
     * Onde: ● versao: Versão do leiaute da mensagem . ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação) . ● cUFAutor: Código da UF do
     * autor do documento fiscal . ● CNPJ: CNPJ do interessado no DF-e . ●
     * ultNSU: Último NSU (Número Sequencial Único) recebido pelo ator. Caso
     * seja a primeira consulta, informar 0 . XML de Recebimento (Exemplo
     * Hipotético):
     *
     * <retConsultarDistribuicaoDFe versao="1.01">
     * <tpAmb>1</tpAmb>
     * <cStat>139</cStat>
     * <xMotivo>Lote processado com sucesso</xMotivo>
     * <loteDistDFeInt>
     * <docZip>
     * <![CDATA[Conteúdo compactado do lote de DF-e]]>
     * </docZip>
     * </loteDistDFeInt>
     * </retConsultarDistribuicaoDFe>
     * Onde: ● versao: Versão do leiaute da mensagem . ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação) . ● cStat: Código do status da
     * resposta . ● xMotivo: Descrição literal do status da resposta . ●
     * loteDistDFeInt: Conjunto de informações resumidas e documentos fiscais
     * eletrônicos de interesse da pessoa física ou empresa . ○ docZip:
     * Informação resumida ou documento fiscal eletrônico de interesse da pessoa
     * física ou empresa. O conteúdo desta tag estará compactado no padrão gzip.
     * O tipo do campo é base64Binary .
     */
}
