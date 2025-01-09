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
 *
 * @autor bajinho
 */
public class EmitirServiceSteps {

    private EmitirService emitirService;
    private LoteNFe loteNFe;
    private List<NFe> nfeList;
    private BancoDeDados bancoDeDados;

    @Given("o usuário está autenticado no sistema")
    public void theUserAuthenticatedInSystem() {
        bancoDeDados = mock(BancoDeDados.class);
        emitirService = new EmitirService(bancoDeDados);
    }

    @And("o usuário possui um lote de NF-e válido")
    public void theUserHasAValidBatchOfNFe() {
        loteNFe = new LoteNFe();
        nfeList = List.of(new NFe("12345"), new NFe("67890"));
        loteNFe.setNfes(nfeList);
    }

    @When("o usuário envia o lote de NF-e para o serviço EmitirService")
    public void theUserSendsTheBatchOfNFeToEmitirService() {
        emitirService.enviarLote(loteNFe);
    }

    @Then("o sistema deve processar o lote")
    public void theSystemShouldProcessTheBatch() {
        for (NFe nfe : nfeList) {
            assertEquals("Autorizado o uso da NF-e", nfe.getStatus());
        }
    }

    @Then("retornar o status {string} para cada NF-e do lote")
    public void returnTheStatusForEachNFeInTheBatch(String status) {
        for (NFe nfe : nfeList) {
            assertEquals(status, nfe.getStatus());
        }
    }

    @Then("gerar um protocolo de autorização para cada NF-e")
    public void generateAnAuthorizationProtocolForEachNFe() {
        for (NFe nfe : nfeList) {
            assertNotNull(nfe.getProtocolo());
        }
    }

    @Then("armazenar os dados da NF-e e do protocolo no banco de dados")
    public void storeTheNFeDataAndProtocolInTheDatabase() {
        for (NFe nfe : nfeList) {
            verify(bancoDeDados, times(1)).salvar(nfe);
        }
    }
}
