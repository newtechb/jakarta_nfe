/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.services;

/**
 *
 * @author bajinho
 */
public class ConsultarCadastroContribuinteService {
    /**
     * Para ilustrar como a estrutura XML funcionaria para um serviço
     * ConsultarCadastroContribuinteService, vamos considerar um cenário similar
     * ao NfeConsultaCadastro. XML de Envio (Exemplo Hipotético):
     *
     * <ConsultarCadastroContribuinte versao="2.00">
     * <tpAmb>1</tpAmb>
     * <UF>SP</UF>
     * <CNPJ>12345678901234</CNPJ>
     * </ConsultarCadastroContribuinte>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● UF: Sigla da Unidade
     * Federativa do contribuinte. ● CNPJ: CNPJ do contribuinte a ser
     * consultado. XML de Recebimento (Exemplo Hipotético):
     *
     * <retConsultarCadastroContribuinte versao="2.00">
     * <tpAmb>1</tpAmb>
     * <cStat>111</cStat>
     * <xMotivo>Consulta cadastro realizada com sucesso</xMotivo>
     * <infCons>
     * <UF>SP</UF>
     * <CNPJ>12345678901234</CNPJ>
     * <IE>1234567890123</IE>
     * <razaoSocial>Nome da Empresa</razaoSocial>
     * <!-- Outros dados cadastrais -->
     * </infCons>
     * </retConsultarCadastroContribuinte>
     * Onde: ● versao: Versão do leiaute da mensagem. ● tpAmb: Identificação do
     * ambiente (1 - Produção, 2 - Homologação). ● cStat: Código do status da
     * resposta (111 - Consulta cadastro realizada com sucesso, de acordo com a
     * tabela 5-22 do documento "Manual de Orientação ao Contribuinte - MOC -
     * versão 7.0 - NF-e e NFC-e.pdf"). ● xMotivo: Descrição literal do status
     * da resposta. ● infCons: Grupo com as informações do contribuinte. ○ UF,
     * CNPJ, IE: Dados de identificação do contribuinte. ○ razaoSocial: Razão
     * social do contribuinte. ○
     * <!-- Outros dados cadastrais -->: Outros dados cadastrais retornados pela
     * consulta.
     */
}
