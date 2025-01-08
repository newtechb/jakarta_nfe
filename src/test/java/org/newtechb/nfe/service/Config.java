/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.newtechb.nfe.service;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConstantesUtil;

/**
 *
 * @author bajinho
 */
public class Config {

    public ConfiguracoesNfe iniciaConfigurações() throws NfeException {
        Certificado certificado = https
        ://github.com/Samuel-Oliveira/Java_Certificado/wiki"
        ://github.com/Samuel-Oliveira/Java_Certificado/wiki
		
	return ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO, certificado, "C:\\SRA\\Nfe\\Schemas");
    }

    public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException {
        // Certificado Arquivo, Parametros: -Caminho Certificado, - Senha
        Certificado certificado = CertificadoService.certificadoPfx(""
                + "/certificado.pfx", "123456");

        ConfiguracoesIniciaisNfe config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.GO, ConstantesUtil.AMBIENTE.HOMOLOGACAO, certificado, "C:\\SRA\\Nfe\\Schemas");

        String ip = "192.168.0.1";
        String porta = "3128";
        String usuario = "";
        String senha = "";

        config.setProxy(ip, porta, usuario, senha);

        config.setContigenciaSCAN(true);

        return config;
    }

}
