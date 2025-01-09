Feature: Emissão de NF-e em ambiente de produção

  Scenario: Emitir NF-e
    Given o usuário está autenticado no sistema
    And o usuário possui um lote de NF-e válido
    When o usuário envia o lote de NF-e para o serviço EmitirService
    Then o sistema deve processar o lote
    And retornar o status "Autorizado o uso da NF-e" para cada NF-e do lote
    And gerar um protocolo de autorização para cada NF-e
    And armazenar os dados da NF-e e do protocolo no banco de dados
