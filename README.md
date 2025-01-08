# Jakarta-NFe [![MIT License](https://img.shields.io/github/license/Samuel-Oliveira/Java_NFe.svg) ](https://github.com/Samuel-Oliveira/Java_NFe/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/br.com.swconsultoria/java-nfe.svg?label=Maven%20Central)](https://search.maven.org/artifact/br.com.swconsultoria/java-nfe/4.00.35/jar)
Biblioteca Java para consumo do WebService de NFe/NFCe

## Dúvidas, Sugestões ou Consultoria
[![Java Brasil](https://discordapp.com/api/guilds/519583346066587676/widget.png?style=banner2)](https://discord.gg/ZXpqnaV)


Starter for Jakarta EE
Steps

**Gerar o Maven Wrapper:**

    mvn wrapper:wrapper

**Limpar e empacotar o projeto:**

    mvn clean package

**Limpar e instalar o projeto localmente:**

    mvn clean install

**Gerar documentação:**

    mvn javadoc:javadoc
  

## Requisitos ##

**Funcionalidades Essenciais:**
    
    ● Emissão de NFC-e: Em conformidade com a legislação nacional e estadual, seguindo os padrões
      definidos nos Manuais, Notas Técnicas e Esquemas XML NF-e.
    ● Utilização do Ambiente de Homologação: Permitir testes e treinamentos com emissão de 
      documentos sem validade jurídica antes da utilização do ambiente de produção para emissão 
      de documentos fiscais válidos.
    ● Cadastro Detalhado:  Cadastrar clientes (pessoa física ou jurídica), emitentes de NFC-e 
      (com dados como CNPJ, Razão Social, Nome Fantasia e endereço), e produtos. O sistema deve 
      permitir alterações nesses cadastros.
    ● Teste de Cadastro de Produtos: Implementar funcionalidade para testar o cadastro de 
      produtos, idealmente de forma automática, para evitar rejeições por informações incorretas 
      ou faltantes.
    ● Venda Rápida:  Facilitar a venda sem necessidade de cadastrar o consumidor final, 
      permitindo a inclusão opcional de CPF/CNPJ ou identificação de estrangeiro.
    ● Identificação do Destinatário:  Permitir vendas sem identificação do destinatário quando 
      permitido pela legislação, respeitando as validações das Notas Técnicas e Manuais.
    ● Cálculo Automático de Tributos:  Preencher automaticamente os cálculos de tributos para 
      agilizar a venda e emissão da NFC-e.
    ● Transmissão do XML da NFC-e:  Transmitir o arquivo XML da NFC-e para a Secretaria de 
      Fazenda Estadual, assinado digitalmente conforme o Manual de Orientação do Contribuinte 
      (MOC).
    ● Suporte a Certificados Digitais:  Aceitar certificados de assinatura digital padrão 
      ICP-Brasil, tipo A1 e/ou A3. Recomenda-se a inclusão de controles para verificar a validade
      dos certificados e gerar alertas de vencimento.
    ● Tempo de Transmissão e Autorização:  Respeitar o tempo limite definido no MOC para 
      transmissão e autorização da NFC-e.
    ● Histórico e Status das Notas:  Registrar o status de cada NFC-e (autorizada, cancelada, 
      inutilizada) e o motivo da rejeição para as notas rejeitadas. Permitir consulta a esse 
      histórico.
    ● Impressão do DANFE NFC-e:  Gerar a impressão do DANFE NFC-e nas formas completa e resumida,
      com QR Code, em conformidade com a legislação e as especificações técnicas. Utilizar 
      impressora comum, exceto matricial.
    ● Backup:  Permitir backup dos dados do sistema, incluindo NFC-e, clientes e produtos.
    ● Envio da NFC-e por E-mail:  Possibilitar o envio do arquivo XML e do DANFE NFC-e para o 
      e-mail do consumidor.
    ● Utilização do CSC:  Implementar o uso do Código de Segurança do Contribuinte (CSC) para 
      o cálculo do hash do QR Code e permitir o pré-cadastramento e alteração do CSC.

**Contingência Off-line:**

    ● Emissão em Contingência:  Permitir a emissão de NFC-e em contingência off-line apenas em 
      situações de impossibilidade de autorização em tempo real, com a devida identificação no 
      DANFE.
    ● Transmissão Após Resolução de Problemas:  Transmitir as NFC-e emitidas em contingência 
      após o restabelecimento da comunicação com a SEFAZ.
    ● Gestão de Notas Pendentes:  Manter uma fila de "NFC-e pendente de retorno" para as notas 
      emitidas em contingência e realizar a consulta da situação da nota após o retorno à 
      normalidade. Cancelar a NFC-e se autorizada ou inutilizar a numeração se não existir na 
      SEFAZ.
    
**Funcionalidades Adicionais:**

    ● Cancelamento e Inutilização:  Permitir o cancelamento de NFC-e e a inutilização de 
      numeração, exceto para notas emitidas em contingência.
    ● Proibição de Controle de Vendas sem NFC-e:  Bloquear qualquer funcionalidade que permita
      controlar vendas sem a emissão da NFC-e ou outro documento fiscal.
    ● Consulta de Notas Emitidas:  Permitir a consulta das NFC-e emitidas, com a opção de 
      reimprimir o DANFE NFC-e.
    ● Correção de Erros:  Permitir a correção de erros que geraram rejeição da NFC-e e a 
      retransmissão para autorização. Implementar validações locais para evitar rejeições pela
      SEFAZ.
    ● Integração com Sistemas Gerenciais:  Facilitar a integração com sistemas gerenciais da 
      empresa.
    ● Integração com TEF:  Possibilitar a integração com sistemas de pagamento por cartão (TEF).
    ● Exportação de Arquivos XML:  Permitir a exportação dos arquivos XML da NFC-e, tanto o 
      documento fiscal quanto a resposta da SEFAZ.
    ● Exportação e Importação de XML:  Permitir a exportação e importação de arquivos XML para
      possibilitar a transmissão de NFC-e emitidas em contingência a partir de outro local.

**Boas Práticas:**

    ● Evitar Uso Indevido de Web Services:  Implementar mecanismos para evitar o uso indevido 
      dos Web Services da SEFAZ, como o reenvio em loop de NFC-e rejeitadas.
    ● Definir Tempo de Espera:  Estabelecer um tempo limite (timeout) para o retorno do 
      processo de autorização, considerando o negócio, a infraestrutura de comunicação e a 
      localidade.
    ● Utilizar Pedido de Resposta Síncrono:  Solicitar o Pedido de Resposta Síncrono para lotes
      com apenas uma NFC-e.
    ● Compactar Mensagens:  Compactar as mensagens enviadas à SEFAZ para reduzir o tempo de 
      resposta.
    ● Manter a Integridade das Datas:  Utilizar a data e hora corretas para emissão da NFC-e em
      contingência e para entrada em contingência.

