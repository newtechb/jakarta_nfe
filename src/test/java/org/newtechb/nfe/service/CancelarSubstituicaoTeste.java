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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;

import java.time.LocalDateTime;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doAnswer;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author bajinho
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class CancelarSubstituicaoTeste {

    @Mock
    private ConfiguracoesNfe config;

    @Mock
    private CancelamentoSubstituicaoUtil cancelamentoSubstituicaoUtil;

    @Mock
    private Nfe nfe;

    @Mock
    private RetornoUtil retornoUtil;

    @InjectMocks
    private CancelarSubstituicaoTeste cancelarSubstituicaoTeste;

    @BeforeEach
    public void setUp() {
        config = Mockito.mock(ConfiguracoesNfe.class);
        cancelamentoSubstituicaoUtil = Mockito.mock(CancelamentoSubstituicaoUtil.class);
        nfe = Mockito.mock(Nfe.class);
        retornoUtil = Mockito.mock(RetornoUtil.class);
    }

    @Test
    void testeCancelarSubstituicao() throws Exception {
        // Cria um evento de cancelamento mockado
        Evento cancela = new Evento();
        cancela.setChave("XXX");
        cancela.setChaveSusbstituta("XXX");
        cancela.setProtocolo("XXX");
        cancela.setCnpj("XXX");
        cancela.setMotivo("Teste de Cancelamento");
        cancela.setDataEvento(LocalDateTime.now());

        // Mocka a resposta do método montaCancelamento
        TEnvEvento enviEvento = new TEnvEvento();
        doAnswer((Answer<TEnvEvento>) invocation -> enviEvento)
                .when(cancelamentoSubstituicaoUtil).montaCancelamento(any(Evento.class), Mockito.any(ConfiguracoesNfe.class));

        // Mocka a resposta do método cancelarSubstituicaoNfe
        TRetEnvEvento retorno = new TRetEnvEvento();
        doAnswer((Answer<TRetEnvEvento>) invocation -> retorno)
                .when(nfe).cancelarSubstituicaoNfe(any(ConfiguracoesNfe.class), any(TEnvEvento.class), any(Boolean.class));

        // Chama o método que está sendo testado
        cancelarSubstituicaoTeste.cancelarSubstituicao(config);

        // Verifica se os métodos foram chamados corretamente
        // (Adicione suas próprias verificações aqui conforme necessário)
    }
}
