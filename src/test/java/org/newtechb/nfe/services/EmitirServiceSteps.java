package org.newtechb.nfe.services;

import io.cucumber.java.en.And;
import org.newtechb.nfe.utils.LoteNFe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.mockito.Mockito.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.newtechb.nfe.NFe;
import org.newtechb.nfe.utils.BancoDeDados;

/**
 * Definições de passos para testes BDD com Cucumber relacionados à emissão de
 * NF-e. Esta classe contém as definições de passos utilizadas no arquivo de
 * feature para testar o processo de emissão de NF-e em um ambiente de produção.
 *
 * 09-01-2025
 *
 * @autor bajinho
 */
public class EmitirServiceSteps {

    /**
     * Serviço responsável por lidar com a emissão de NF-e.
     */
    private EmitirService emitirService;

    /**
     * Lote de NF-e a ser processado.
     */
    private LoteNFe loteNFe;

    /**
     * Lista de NF-e no lote.
     */
    private List<NFe> nfeList;

    /**
     * Banco de dados mockado para armazenar os dados das NF-e e os protocolos.
     */
    private BancoDeDados bancoDeDados;

    /**
     * Passo Given para autenticar o usuário no sistema. Este passo inicializa o
     * banco de dados mockado e o EmitirService com o mock.
     */
    @Given("o usuário está autenticado no sistema")
    public void theUserAuthenticatedInSystem() {
        bancoDeDados = mock(BancoDeDados.class);
        emitirService = new EmitirService(bancoDeDados);
    }

    /**
     * Passo And para configurar um lote válido de NF-e. Este passo cria um lote
     * de NF-e com dados fictícios.
     */
    @And("o usuário possui um lote de NF-e válido")
    public void theUserHasAValidBatchOfNFe() {
        loteNFe = new LoteNFe();
        nfeList = List.of(new NFe("12345"), new NFe("67890"));
        loteNFe.setNfes(nfeList);
    }

    /**
     * Passo When para enviar o lote de NF-e para o EmitirService. Este passo
     * processa o lote de NF-e.
     */
    @When("o usuário envia o lote de NF-e para o serviço EmitirService")
    public void theUserSendsTheBatchOfNFeToEmitirService() {
        emitirService.enviarLote(loteNFe);
    }

    /**
     * Passo Then para verificar se o sistema processa o lote. Este passo
     * verifica se cada NF-e no lote tem o status "Autorizado o uso da NF-e".
     */
    @Then("o sistema deve processar o lote")
    public void theSystemShouldProcessTheBatch() {
        for (NFe nfe : nfeList) {
            assertEquals("Autorizado o uso da NF-e", nfe.getStatus());
        }
    }

    /**
     * Passo Then para verificar o status de cada NF-e no lote. Este passo
     * verifica se o status de cada NF-e corresponde ao status esperado.
     *
     * @param status O status esperado de cada NF-e.
     */
    @Then("retornar o status {string} para cada NF-e do lote")
    public void returnTheStatusForEachNFeInTheBatch(String status) {
        for (NFe nfe : nfeList) {
            assertEquals(status, nfe.getStatus());
        }
    }

    /**
     * Passo Then para gerar um protocolo de autorização para cada NF-e. Este
     * passo verifica se cada NF-e tem um protocolo não nulo.
     */
    @Then("gerar um protocolo de autorização para cada NF-e")
    public void generateAnAuthorizationProtocolForEachNFe() {
        for (NFe nfe : nfeList) {
            assertNotNull(nfe.getProtocolo());
        }
    }

    /**
     * Passo Then para armazenar os dados da NF-e e do protocolo no banco de
     * dados. Este passo verifica se o método salvar é chamado para cada NF-e.
     */
    @Then("armazenar os dados da NF-e e do protocolo no banco de dados")
    public void storeTheNFeDataAndProtocolInTheDatabase() {
        for (NFe nfe : nfeList) {
            verify(bancoDeDados, times(1)).salvar(nfe);
        }
    }
}
