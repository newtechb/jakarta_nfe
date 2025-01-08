package org.newtechb.nfe;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.mock.MockCancelar;
import br.com.swconsultoria.nfe.mock.MockStatus;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.wsdl.NFeRecepcaoEvento.NFeRecepcaoEvento4Stub;
import br.com.swconsultoria.nfe.wsdl.NFeStatusServico4.NFeStatusServico4Stub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.newtechb.nfe.wsdl.NFeAutorizacao.NfeResultMsg;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
final class NfeTest {

    private NFeStatusServico4Stub stub;
    private NFeRecepcaoEvento4Stub recepcaoEventoStub;
    private NfeService nfeService;
    private static ConfiguracoesNfe configuracoesNfe;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        URI uri = Objects.requireNonNull(NfeTest.class.getClassLoader().getResource("NAO_UTILIZE.pfx")).toURI();
        Certificado certificado = CertificadoService.certificadoPfx(
                Paths.get(uri).toString(), "123456");

        configuracoesNfe = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO, certificado, "");

        configuracoesNfe.setEncode("UTF-8");
    }

    @BeforeEach
    public void setUp() {
        stub = mock(NFeStatusServico4Stub.class);
        recepcaoEventoStub = mock(NFeRecepcaoEvento4Stub.class);
        nfeService = new NfeService(stub, recepcaoEventoStub);
    }

    @Test
    void testeStatusServico() throws Exception {
        // Define o comportamento do método mockado
        doAnswer(new Answer<NfeResultMsg>() {
            @Override
            public NfeResultMsg answer(InvocationOnMock invocation) throws Exception {
                NFeStatusServico4Stub.NfeDadosMsg dados = invocation.getArgument(0);
                
                // Criar e configurar o elemento XML de resposta
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                Document document = factory.newDocumentBuilder().newDocument();
                Element element = document.createElement("retConsStatServ");
                element.setAttribute("cStat", StatusEnum.SERVICO_EM_OPERACAO.getCodigo());
                element.setAttribute("versao", ConstantesUtil.VERSAO.NFE);
                element.setAttribute("cUF", configuracoesNfe.getEstado().getCodigoUF());
                element.setAttribute("tpAmb", AmbienteEnum.HOMOLOGACAO.getCodigo());

                // Configurar a mensagem de resultado
                NfeResultMsg resultMsg = new NfeResultMsg();
                resultMsg.setExtraElement(element);
                
                return resultMsg;
            }
        }).when(stub).nfeStatusServicoNF(Mockito.any(NFeStatusServico4Stub.NfeDadosMsg.class));

        // Chama o método que está sendo testado
        TRetConsStatServ retorno = nfeService.statusServico(configuracoesNfe, DocumentoEnum.NFE);

        // Verificações das asserções
        assertEquals(StatusEnum.SERVICO_EM_OPERACAO.getCodigo(), retorno.getCStat());
        assertEquals(ConstantesUtil.VERSAO.NFE, retorno.getVersao());
        assertEquals(configuracoesNfe.getEstado().getCodigoUF(), retorno.getCUF());
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), retorno.getTpAmb());
    }

    @Test
    void testeCancelamento() throws Exception {
        // Define o comportamento do método mockado
        doAnswer(new Answer<NfeResultMsg>() {
            @Override
            public NfeResultMsg answer(InvocationOnMock invocation) throws Exception {
                NFeRecepcaoEvento4Stub.NfeDadosMsg dados = invocation.getArgument(0);

                // Criar e configurar o elemento XML de resposta
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                Document document = factory.newDocumentBuilder().newDocument();
                Element element = document.createElement("retEnvEvento");
                element.setAttribute("cStat", StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo());
                element.setAttribute("versao", ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO);
                element.setAttribute("cOrgao", configuracoesNfe.getEstado().getCodigoUF());
                element.setAttribute("tpAmb", AmbienteEnum.HOMOLOGACAO.getCodigo());

                Element infEvento = document.createElement("infEvento");
                infEvento.setAttribute("cStat", StatusEnum.EVENTO_VINCULADO.getCodigo());
                element.appendChild(infEvento);

                // Configurar a mensagem de resultado
                NfeResultMsg resultMsg = new NfeResultMsg();
                resultMsg.setExtraElement(element);

                return resultMsg;
            }
        }).when(recepcaoEventoStub).nfeRecepcaoEvento(Mockito.any(NFeRecepcaoEvento4Stub.NfeDadosMsg.class));

        // Chama o método que está sendo testado
        TRetEnvEvento retorno = nfeService.cancelarNfe(configuracoesNfe, MockCancelar.criaEventoCancelamento(configuracoesNfe), false, DocumentoEnum.NFE);

        // Verificações das asserções
        RetornoUtil.validaCancelamento(retorno);
        assertEquals(StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo(), retorno.getCStat());
        assertEquals(configuracoesNfe.getEstado().getCodigoUF(), retorno.getCOrgao());
        assertEquals(ConstantesUtil.VERSAO.EVENTO_CANCELAMENTO, retorno.getVersao());
        assertEquals(AmbienteEnum.HOMOLOGACAO.getCodigo(), retorno.getTpAmb());
        assertEquals(StatusEnum.EVENTO_VINCULADO.getCodigo(), retorno.getRetEvento().get(0).getInfEvento().getCStat());
    }
}
