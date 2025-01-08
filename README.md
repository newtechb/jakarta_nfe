# Jakarta-NFe [![MIT License](https://img.shields.io/github/license/Samuel-Oliveira/Java_NFe.svg) ](https://github.com/Samuel-Oliveira/Java_NFe/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/br.com.swconsultoria/java-nfe.svg?label=Maven%20Central)](https://search.maven.org/artifact/br.com.swconsultoria/java-nfe/4.00.35/jar)
Projto jakartaEE para consumo do WebService de NFe/NFCe
# ![JakartaEE](https://jakarta.ee/images/jakarta/jakarta-ee-logo-color.svg)
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
  
### Requisitos de Software ###

Para um projeto emissor de NF-e utilizando Jakarta EE e Jakarta XML Web Services para a comunicação com a SEFAZ, diversos padrões e requisitos podem ser considerados **até a camada de serviços**, visando garantir a segurança, a conformidade legal e a eficiência do sistema. 

**1. Camada de Negócio:**

* Implementação da lógica de negócio relacionada à emissão de NF-e, incluindo:
    * Validação das regras de negócio específicas da NF-e, como:
        * Campos obrigatórios.
        * Formatos de dados.
        * Regras de cálculo de impostos.
    * Cálculo dos impostos e valores da NF-e.
    * Geração do XML da NF-e, de acordo com o schema oficial da SEFAZ.
    * Assinatura digital do XML da NF-e utilizando o certificado digital da empresa, via JCA.
    * Comunicação com a camada de serviços para envio da NF-e para a SEFAZ.
    * Processamento das respostas da SEFAZ, como autorização, rejeição ou erros.
    * Geração do DANFE (Documento Auxiliar da Nota Fiscal Eletrônica) em formato PDF.
    * Armazenamento das informações da NF-e e dos eventos relacionados (autorização, cancelamento, etc.).

*   **Tecnologias:**
    *   **EJB (Enterprise JavaBeans):** Para encapsular a lógica de negócio em componentes reutilizáveis e gerenciados pelo servidor de aplicação.
        *   **Stateless Session Beans:** Para lógica de negócio sem estado, ideal para operações como validação e cálculo.
        *   **Message Driven Beans:** Para processamento assíncrono de mensagens, útil para integrar com sistemas externos ou gerenciar filas de processamento.
    *   **CDI (Contexts and Dependency Injection):** Para gerenciar as dependências entre os componentes da aplicação, facilitando a testabilidade e a reutilização.
    *   **JPA (Jakarta Persistence API):** Para persistir as informações da NF-e em um banco de dados relacional, abstraindo o acesso aos dados.
    *   **JCA (Java Cryptography Architecture):** Para realizar a assinatura digital do XML da NF-e utilizando o certificado digital da empresa.
    *   **Bibliotecas de geração de PDF:** Como iText ou Apache PDFBox, para gerar o DANFE em formato PDF.

**2. Camada de Serviços:**

* **Jakarta XML Web Services** será a tecnologia central para esta camada, responsável pela comunicação com os Web Services da SEFAZ.
* Implementação de serviços para cada operação da NF-e, como:
    * `EmitirNFe`: Envia o XML da NF-e para a SEFAZ e processa a resposta.
    * `CancelarNFe`: Envia a solicitação de cancelamento e processa a resposta.
    * `InutilizarNumeracao`: Envia a solicitação de inutilização e processa a resposta.
    * `ConsultarStatusNFe`: Consulta a situação da NF-e na SEFAZ.
    * `ConsultarCadastroContribuinte`: Consulta dados cadastrais na SEFAZ.

*   **Tecnologias:**
    *   **Jakarta XML Web Services (JAX-WS):** Para criar e consumir os Web Services para comunicação com a SEFAZ.
        *   **@WebService:** Anotação para definir a classe como um Web Service.
        *   **@WebMethod:** Anotação para definir os métodos expostos como operações do Web Service.
    *   **JAXB (Jakarta XML Binding):** Para mapear as classes Java para XML e vice-versa, facilitando a comunicação com os Web Services da SEFAZ.

**Padrões e requisitos para todas as camadas:**

* **Segurança:**
    * Autenticação e autorização para controlar o acesso ao sistema.
    * Comunicação segura HTTPS com a SEFAZ.
    * Armazenamento seguro das chaves e certificados digitais.
    * Implementação de mecanismos para prevenir ataques e proteger dados sensíveis.
* **Gestão de Erros:**
    * Tratamento e registro de erros para facilitar a identificação e resolução de problemas.
    * Mensagens de erro claras e informativas para o usuário.
* **Logging:**
    * Registro de eventos importantes do sistema para auditoria e análise.
* **Testes:**
    * Criação de testes unitários e de integração para garantir a qualidade do código e a funcionalidade do sistema.

**Observações importantes:**

* Esta estrutura até a camada de serviços visa preparar o sistema para a comunicação com a SEFAZ. A implementação da camada de acesso a dados e a integração com outras partes do sistema dependerá de requisitos específicos.
* A legislação e as especificações da NF-e estão sujeitas a alterações, portanto, mantenha-se atualizado.
* Lembre-se que a escolha das tecnologias e a forma de utilizá-las dependerá da arquitetura e dos requisitos específicos do seu projeto.


### Requisitos SEFAZ ###

**Funcionalidades Essenciais:**
    
* Emissão de NFC-e: Em conformidade com a legislação nacional e estadual, seguindo os padrões definidos nos Manuais, Notas Técnicas e Esquemas XML NF-e.**
* Utilização do Ambiente de Homologação: Permitir testes e treinamentos com emissão de documentos sem validade jurídica antes da utilização do ambiente de produção para emissão de documentos fiscais válidos.**
* Cadastro Detalhado:  Cadastrar clientes (pessoa física ou jurídica), emitentes de NFC-e (com dados como CNPJ, Razão Social, Nome Fantasia e endereço), e produtos. O sistema deve permitir alterações nesses cadastros.**
* Teste de Cadastro de Produtos: Implementar funcionalidade para testar o cadastro de produtos, idealmente de forma automática, para evitar rejeições por informações incorretas ou faltantes.**
* Venda Rápida:  Facilitar a venda sem necessidade de cadastrar o consumidor final, permitindo a inclusão opcional de CPF/CNPJ ou identificação de estrangeiro.**
* Identificação do Destinatário:  Permitir vendas sem identificação do destinatário quando permitido pela legislação, respeitando as validações das Notas Técnicas e Manuais.**
* Cálculo Automático de Tributos:  Preencher automaticamente os cálculos de tributos para agilizar a venda e emissão da NFC-e.**
* Transmissão do XML da NFC-e:  Transmitir o arquivo XML da NFC-e para a Secretaria de Fazenda Estadual, assinado digitalmente conforme o Manual de Orientação do Contribuinte (MOC).**
* Suporte a Certificados Digitais:  Aceitar certificados de assinatura digital padrão ICP-Brasil, tipo A1 e/ou A3. Recomenda-se a inclusão de controles para verificar a validade dos certificados e gerar alertas de vencimento.**
* Tempo de Transmissão e Autorização:  Respeitar o tempo limite definido no MOC para transmissão e autorização da NFC-e.**
* Histórico e Status das Notas:  Registrar o status de cada NFC-e (autorizada, cancelada, inutilizada) e o motivo da rejeição para as notas rejeitadas. Permitir consulta a esse histórico.**
* Impressão do DANFE NFC-e:  Gerar a impressão do DANFE NFC-e nas formas completa e resumida, com QR Code, em conformidade com a legislação e as especificações técnicas. Utilizar impressora comum, exceto matricial.**
* Backup:  Permitir backup dos dados do sistema, incluindo NFC-e, clientes e produtos.**
* Envio da NFC-e por E-mail:  Possibilitar o envio do arquivo XML e do DANFE NFC-e para o e-mail do consumidor.**
* Utilização do CSC:  Implementar o uso do Código de Segurança do Contribuinte (CSC) para o cálculo do hash do QR Code e permitir o pré-cadastramento e alteração do CSC.**

**Contingência Off-line:**

* Emissão em Contingência:  Permitir a emissão de NFC-e em contingência off-line apenas em situações de impossibilidade de autorização em tempo real, com a devida identificação no DANFE.**
* Transmissão Após Resolução de Problemas:  Transmitir as NFC-e emitidas em contingência após o restabelecimento da comunicação com a SEFAZ.**
* Gestão de Notas Pendentes:  Manter uma fila de "NFC-e pendente de retorno" para as notas emitidas em contingência e realizar a consulta da situação da nota após o retorno à normalidade. Cancelar a NFC-e se autorizada ou inutilizar a numeração se não existir na SEFAZ.**
    
**Funcionalidades Adicionais:**

* Cancelamento e Inutilização:  Permitir o cancelamento de NFC-e e a inutilização de numeração, exceto para notas emitidas em contingência.**
* Proibição de Controle de Vendas sem NFC-e:  Bloquear qualquer funcionalidade que permita controlar vendas sem a emissão da NFC-e ou outro documento fiscal.**
* Consulta de Notas Emitidas:  Permitir a consulta das NFC-e emitidas, com a opção de reimprimir o DANFE NFC-e.**
* Correção de Erros:  Permitir a correção de erros que geraram rejeição da NFC-e e a retransmissão para autorização. Implementar validações locais para evitar rejeições pela SEFAZ.**
* Integração com Sistemas Gerenciais:  Facilitar a integração com sistemas gerenciais da empresa.**
* Integração com TEF:  Possibilitar a integração com sistemas de pagamento por cartão (TEF).**
* Exportação de Arquivos XML:  Permitir a exportação dos arquivos XML da NFC-e, tanto o documento fiscal quanto a resposta da SEFAZ.**
* Exportação e Importação de XML:  Permitir a exportação e importação de arquivos XML para possibilitar a transmissão de NFC-e emitidas em contingência a partir de outro local.**

**Boas Práticas:**

* Evitar Uso Indevido de Web Services:  Implementar mecanismos para evitar o uso indevido dos Web Services da SEFAZ, como o reenvio em loop de NFC-e rejeitadas.**
* Definir Tempo de Espera:  Estabelecer um tempo limite (timeout) para o retorno do processo de autorização, considerando o negócio, a infraestrutura de comunicação e a localidade.**
* Utilizar Pedido de Resposta Síncrono:  Solicitar o Pedido de Resposta Síncrono para lotes com apenas uma NFC-e.**
* Compactar Mensagens:  Compactar as mensagens enviadas à SEFAZ para reduzir o tempo de resposta.**
* Manter a Integridade das Datas:  Utilizar a data e hora corretas para emissão da NFC-e em contingência e para entrada em contingência.**

### O processo de envio da NF-e envolve os seguintes passos: ###

1. O aplicativo do contribuinte gera um arquivo XML da NF-e.
2. O arquivo XML é assinado digitalmente com um certificado digital.
3. O aplicativo do contribuinte envia o arquivo XML assinado para o Web Service da SEFAZ, utilizando uma mensagem SOAP.
4. O Web Service da SEFAZ processa a solicitação e retorna uma mensagem de resposta.


**Para gerar um arquivo XML da NF-e, você precisa seguir as especificações detalhadas no Manual de Orientação ao Contribuinte (MOC).**
**O MOC define o leiaute da NF-e, incluindo a estrutura, os campos obrigatórios e opcionais, e as regras de validação.**

**O processo de geração do arquivo XML envolve:**

* **Criar a estrutura XML com base no Schema XML (XSD) da NF-e.** O Schema XML define a estrutura e os elementos do documento XML. O MOC fornece o Schema XML para a versão da NF-e que você está utilizando.
* **Preencher os campos obrigatórios e opcionais da NF-e com as informações da operação comercial.**
* **Assinar digitalmente o arquivo XML com um certificado digital válido.**
* **Validar o arquivo XML contra o Schema XML da NF-e para garantir a conformidade com as regras estabelecidas.**

**O MOC também fornece exemplos de arquivos XML e orientações sobre como otimizar a montagem do arquivo para reduzir o tamanho final.** Algumas dicas para otimizar o tamanho do arquivo XML incluem:

* **Não incluir tags de campos com conteúdo zero ou vazio, exceto para os campos obrigatórios.**
* **Não incluir espaços no início ou no final de campos numéricos e alfanuméricos.**
* **Não incluir comentários, anotações ou documentação no arquivo XML.**
* **Não incluir caracteres de formatação no arquivo XML.**
* **Não incluir prefixos no namespace das tags da NF-e.**

**Após gerar o arquivo XML da NF-e, você pode enviá-lo para o Web Service da SEFAZ para autorização.** 

**A assinatura digital do arquivo XML da NF-e é um passo crucial para garantir a** 
**autenticidade**, **integridade** e **autoria** do documento. O processo utiliza um 
certificado digital, que funciona como uma identidade digital para a empresa emissora da NF-e. 

**Veja como funciona a assinatura digital do arquivo XML da NF-e:**

* **Obtenção do certificado digital:** A empresa precisa obter um certificado digital emitido por uma Autoridade Certificadora (AC) credenciada pela Infraestrutura de Chaves Públicas Brasileira (ICP-Brasil). O certificado pode ser do tipo A1 ou A3.
* **Assinatura do arquivo XML:** A assinatura digital utiliza a chave privada do certificado digital para gerar uma assinatura criptográfica única para o arquivo XML. Essa assinatura é anexada ao arquivo XML, vinculando-o indissociavelmente à empresa emissora. 
* **Verificação da assinatura:** Ao receber o arquivo XML, a SEFAZ utiliza a chave pública do certificado digital da empresa para verificar a assinatura. Essa verificação confirma que:
    * **Autenticidade:** O arquivo XML foi realmente emitido pela empresa detentora do certificado digital.
    * **Integridade:** O arquivo XML não foi alterado após a assinatura. 
    * **Autoria:** A empresa emissora assume a responsabilidade pelo conteúdo do arquivo XML.

**O Manual de Orientação ao Contribuinte (MOC) define os padrões técnicos para a assinatura digital da NF-e, incluindo:**

* **Padrão de assinatura:** XML Digital Signature, no formato Enveloped.
* **Algoritmo de assinatura:** RSA-SHA1.
* **Transformações:** Enveloped e C14N. 

**A assinatura digital é um componente fundamental da NF-e, garantindo a segurança e a validade jurídica do documento fiscal eletrônico.**


O envio do arquivo XML assinado para o Web Service da SEFAZ é realizado através de uma mensagem SOAP (Simple Object Access Protocol), seguindo o padrão de comunicação estabelecido no Manual de Orientação ao Contribuinte (MOC). O aplicativo do contribuinte age como um cliente que inicia a comunicação com o Web Service da SEFAZ.

**O processo de envio pode ser resumido nos seguintes passos:**

1. **Construção da mensagem SOAP:** O aplicativo do contribuinte constrói uma mensagem SOAP contendo o arquivo XML da NF-e assinado digitalmente. Essa mensagem inclui:
    * Um cabeçalho SOAP (`SOAP Header`) com informações de controle, como a versão do leiaute da mensagem e o código da UF.
    * Um corpo SOAP (`SOAP Body`) contendo a estrutura XML da NF-e (`enviNFe`) e os dados da NF-e, incluindo a assinatura digital.
2. **Conexão segura:** O aplicativo do contribuinte estabelece uma conexão segura com o Web Service da SEFAZ utilizando o protocolo HTTPS (Hypertext Transfer Protocol Secure). Essa conexão garante a confidencialidade e integridade dos dados transmitidos.
3. **Autenticação:** A autenticação mútua é utilizada para verificar a identidade do aplicativo do contribuinte e do servidor da SEFAZ. Essa autenticação é realizada através de certificados digitais. O certificado digital do contribuinte contém o CNPJ da empresa e é utilizado para assinar digitalmente a mensagem SOAP, garantindo a autoria da solicitação.
4. **Envio da mensagem SOAP:** O aplicativo do contribuinte envia a mensagem SOAP para o Web Service da SEFAZ. Essa mensagem é transmitida através do endpoint (URL) do Web Service específico para a operação desejada, como autorização de NF-e, cancelamento de NF-e, inutilização de numeração, etc..
5. **Processamento da resposta:** O Web Service da SEFAZ processa a mensagem SOAP e retorna uma resposta ao aplicativo do contribuinte. Essa resposta pode conter:
    * Um recibo de processamento, no caso de serviços assíncronos, indicando que a solicitação foi recebida e será processada posteriormente.
    * O resultado do processamento, no caso de serviços síncronos, como a autorização de uso da NF-e ou uma mensagem de erro. 

**É importante destacar que os Web Services da SEFAZ podem ter diferentes métodos e parâmetros, dependendo do serviço solicitado.** O MOC define os schemas XML (XSD) e os leiautes das mensagens para cada Web Service, garantindo a interoperabilidade entre os sistemas.

O processamento da solicitação pelo Web Service da SEFAZ e a geração da mensagem de resposta são etapas cruciais no processo de comunicação da NF-e. Ao receber a mensagem SOAP do contribuinte, o Web Service realiza uma série de validações e processamentos antes de retornar uma resposta. 

**O fluxo de processamento pode ser resumido nos seguintes passos:**

1. **Validação da mensagem SOAP:** O Web Service verifica se a mensagem SOAP está bem formada e se atende aos padrões estabelecidos, como a versão do SOAP, o namespace, o estilo de codificação e a estrutura do cabeçalho e do corpo da mensagem.
2. **Validação do certificado digital:** O certificado digital utilizado para assinar a mensagem SOAP é validado para garantir a autenticidade da solicitação e a identidade do contribuinte.
3. **Validação das informações de controle:** As informações de controle contidas no cabeçalho SOAP são validadas, como a versão do leiaute da mensagem, o código da UF e o tipo de ambiente (produção ou homologação).
4. **Validação do Schema XML da NF-e:** O arquivo XML da NF-e é validado contra o Schema XML (XSD) correspondente à versão da NF-e, garantindo a conformidade com a estrutura e as regras de validação definidas no MOC. 
5. **Validação das regras de negócio:** As informações contidas na NF-e são validadas de acordo com as regras de negócio específicas para cada tipo de operação, como a emissão da NF-e, o cancelamento, a inutilização de numeração, etc.
6. **Processamento da solicitação:** Após todas as validações, o Web Service processa a solicitação de acordo com o serviço solicitado. Isso pode envolver a autorização da NF-e, o registro do cancelamento, a inutilização da numeração, a consulta de status, etc.
7. **Geração da mensagem de resposta:** O Web Service gera uma mensagem de resposta em formato SOAP, contendo o resultado do processamento. Essa mensagem pode incluir:
    * **Em caso de sucesso:**
        * Protocolo de autorização da NF-e, no caso de autorização.
        * Protocolo de cancelamento, no caso de cancelamento.
        * Protocolo de inutilização, no caso de inutilização.
        * Informações sobre o status da NF-e, no caso de consulta de status.
        * Informações sobre o status do serviço, no caso de consulta de status do serviço.
        * Dados do cadastro do contribuinte, no caso de consulta de cadastro.
    * **Em caso de erro:**
        * Código e descrição do erro ocorrido, permitindo que o contribuinte identifique e corrija o problema.

**A mensagem de resposta é enviada de volta ao aplicativo do contribuinte, finalizando o processo de comunicação.** O aplicativo do contribuinte é responsável por processar a mensagem de resposta e tomar as ações necessárias, como armazenar o protocolo de autorização, exibir a mensagem de erro ao usuário, etc.

As siglas **GR**, **FR**, **ER**, **DR**, **CR** e **PR**  são utilizadas no Manual de Orientação ao Contribuinte (MOC) para identificar os campos de retorno das diversas operações da NF-e (Nota Fiscal Eletrônica). Cada sigla corresponde a um grupo específico de campos e representa a primeira parte do nome do campo no arquivo XML de resposta da SEFAZ. 

A seguir, a descrição de cada sigla:

* **GR:** Refere-se aos campos de retorno da operação de **consulta de cadastro** de contribuinte. Por exemplo, o campo **GR01 retConsCad** representa a *tag* raiz da resposta da consulta de cadastro.
* **FR:** Refere-se aos campos de retorno da operação de **consulta de status do serviço** de autorização da NF-e. Por exemplo, o campo **FR01 retConsStatServ** representa a *tag* raiz da resposta da consulta de status do serviço.
* **ER:** Refere-se aos campos de retorno da operação de **consulta de status da NF-e**. Por exemplo, o campo **ER01 retConsSitNFe** representa a *tag* raiz da resposta da consulta de situação da NF-e.
* **DR:** Refere-se aos campos de retorno da operação de **inutilização de numeração de NF-e**. Por exemplo, o campo **DR01 retInutNFe** representa a *tag* raiz da resposta do pedido de inutilização.
* **CR:** Refere-se aos campos de retorno da operação de **cancelamento de NF-e**. Por exemplo, o campo **CR01 retCancNFe** representa a *tag* raiz da resposta do pedido de cancelamento da NF-e.
* **PR:** Refere-se aos campos que compõem o **protocolo de processamento da NF-e**. Este grupo de campos está presente na resposta da consulta de processamento do lote de NF-e (retConsReciNFe) e fornece informações sobre o processamento individual de cada NF-e do lote. Por exemplo, o campo **PR01 protNFe** representa a *tag* raiz do protocolo de recebimento da NF-e.

Essas siglas facilitam a identificação dos campos e a interpretação da resposta da SEFAZ. Ao consultar a documentação do MOC, você poderá encontrar a descrição completa de cada campo e o seu significado. 

Em caso de processamento com sucesso da autorização da NF-e, a SEFAZ retornará um **Protocolo de Autorização de Uso**, que é um arquivo XML contendo informações importantes sobre a autorização.  O leiaute do Protocolo de Autorização de Uso é definido no Manual de Orientação ao Contribuinte - MOC e é composto pelas seguintes informações:

```xml
<protNFe versao="4.00">
  <infProt Id="ID39180308307757000165550010000000011234567890">
    <tpAmb>1</tpAmb>
    <verAplic>SVRS202108161010</verAplic>
    <chNFe>39180308307757000165550010000000011234567890</chNFe>
    <dhRecbto>2023-04-20T10:12:15-03:00</dhRecbto>
    <nProt>39180308307757000165550010000000011234567890</nProt>
    <digVal>eZzqWZ+kY8g6InZxMhM8sL8BoA=</digVal>
    <cStat>100</cStat>
    <xMotivo>Autorizado o uso da NF-e</xMotivo>
  </infProt>
</protNFe>
```

**Explicação dos campos:**

*   **protNFe:** *Tag* raiz do Protocolo de autorização.
    *   **versao:** Versão do leiaute do protocolo (4.00 neste exemplo).
*   **infProt:** Informações do protocolo de resposta.
    *   **Id:** Identificador da *Tag* a ser assinada. Contém o literal "ID" seguido do número do protocolo.
    *   **tpAmb:** Identificação do Ambiente.
        *   **1:** Produção.
    *   **verAplic:** Versão do aplicativo da SEFAZ que processou a NF-e.
    *   **chNFe:** Chave de acesso da NF-e.
    *   **dhRecbto:** Data e hora de recebimento da NF-e pela SEFAZ.
    *   **nProt:** Número do Protocolo de Autorização da NF-e.
    *   **digVal:** *Digest Value* da NF-e processada.
    *   **cStat:** Código do status da resposta.
        *   **100:** Autorizado o uso da NF-e.
    *   **xMotivo:** Descrição literal do status da resposta.

**Observações:**

*   A estrutura do XML de retorno pode variar de acordo com a versão do leiaute utilizada pela SEFAZ.
*   A mensagem de retorno é assinada digitalmente pela SEFAZ, garantindo a autenticidade e integridade das informações.

* **PR01 protNFe:** TAG raiz do Protocolo de recebimento da NFe.
* **PR02 versao:** Versão do leiaute das informações de Protocolo.
* **PR03 infProt:** Informações do Protocolo de resposta. TAG a ser assinada.
* **PR04 Id:** Identificador da TAG a ser assinada, somente precisa ser informado se a UF assinar a resposta. Em caso de assinatura da resposta pela SEFAZ, preencher o campo com o Número do Protocolo, precedido com o literal “ID”.
* **PR05 tpAmb:** Identificação do Ambiente: 1=Produção/2=Homologação.
* **PR06 verAplic:** Versão do Aplicativo que processou o Lote. A versão deve ser iniciada com a sigla da UF nos casos de WS próprio ou a sigla SVAN ou SVRS nos demais casos.
* **PR07 chNFe:** Chave de Acesso da NF-e.
* **PR08 dhRecbto:** Preenchido com a data e hora do processamento (informado também no caso de rejeição). Formato: “AAAA-MM-DDThh:mm:ssTZD” (UTC – Universal Coordinated Time).
* **PR09 nProt:** Número do Protocolo da NF-e. O número do protocolo é gerado pela SEFAZ para identificar a transação de autorização de uso da NF-e.
* **PR10 digVal:** Digest Value da NF-e processada.
* **PR11 cStat:** Código do status da resposta. 100 - Uso autorizado. **Este código indica que a NF-e foi autorizada com sucesso.**
* **PR12 xMotivo:** Descrição literal do status da resposta para a NF-e.

**O Protocolo de Autorização de Uso é um documento importante, pois comprova que a NF-e foi autorizada pela SEFAZ e garante a validade jurídica da operação.** Este protocolo deve ser armazenado pelo emitente da NF-e, juntamente com o arquivo XML da NF-e, para fins de comprovação e auditoria.

Vale ressaltar que a estrutura do Protocolo de Autorização de Uso pode variar slightly entre as diferentes versões do Manual de Orientação ao Contribuinte - MOC.

Em caso de processamento com sucesso do cancelamento da NF-e, a SEFAZ retornará um **Protocolo de Cancelamento**, que é um arquivo XML contendo informações importantes sobre o cancelamento. O leiaute do Protocolo de Cancelamento é definido no Manual de Orientação ao Contribuinte - MOC e é composto pelas seguintes informações:

```xml
<retCancNFe versao="2.00" xmlns="http://www.portal.fazenda.gov.br/nfe">
    <infCanc Id="ID12345678901234567890123456789012345678901234">
        <tpAmb>1</tpAmb>
        <verAplic>SVRS202108161010</verAplic>
        <cStat>101</cStat>
        <xMotivo>Cancelamento de NF-e homologado</xMotivo>
        <cUF>35</cUF>
        <chNFe>12345678901234567890123456789012345678901234</chNFe>
        <dhRecbto>2023-04-20T11:00:00-03:00</dhRecbto>
        <nProt>1234567890123456</nProt>
    </infCanc>
</retCancNFe>
```

**Explicação dos Campos:**

* **retCancNFe:** *Tag* raiz da mensagem de retorno do cancelamento da NF-e.
    * **versao:** Versão do leiaute da mensagem de retorno (2.00 neste exemplo).
* **infCanc:**  Dados da resposta do cancelamento.
    * **Id:** Identificador da *tag* a ser assinada, contendo o literal "ID" seguido do número do protocolo.
    * **tpAmb:** Identificação do ambiente.
        * **1:** Produção.
    * **verAplic:** Versão do aplicativo da SEFAZ que processou o cancelamento.
    * **cStat:** Código do status da resposta.
        * **101:** Cancelamento de NF-e homologado.
    * **xMotivo:** Descrição literal do status da resposta.
    * **cUF:** Código da UF que atendeu a solicitação de cancelamento.
    * **chNFe:** Chave de acesso da NF-e cancelada.
    * **dhRecbto:** Data e hora de processamento do cancelamento.
    * **nProt:** Número do Protocolo de Cancelamento.

**Observações:**

* A estrutura do XML de retorno pode variar de acordo com a versão do leiaute utilizada pela SEFAZ.
* A mensagem de retorno é assinada digitalmente pela SEFAZ, garantindo a autenticidade e integridade das informações.
* É importante consultar a documentação oficial do Manual de Orientação ao Contribuinte (MOC) para obter a descrição completa dos campos e as regras de validação aplicáveis à versão do leiaute em uso. 

* **CR01 retCancNFe:** TAG raiz da Resposta do Pedido de Cancelamento.
* **CR02 versao:** Versão do leiaute.
* **CR03 infCanc:** Informações do Protocolo de Cancelamento. TAG a ser assinada.
* **CR04 Id:** Identificador da TAG a ser assinada, somente precisa ser informado se a UF assinar a resposta. Em caso de assinatura da resposta pela SEFAZ, preencher o campo com o Número do Protocolo, precedido com o literal "ID".
* **CR05 tpAmb:** Identificação do Ambiente: 1 - Produção / 2 - Homologação.
* **CR06 verAplic:** Versão do Aplicativo que recebeu o Lote.
* **CR07 cStat:** Código do status da resposta. **101 - Cancelamento de NF-e homologado.** Este código indica que a NF-e foi cancelada com sucesso.
* **CR08 xMotivo:** Descrição literal do status da resposta.
* **CR08a cUF:** Código da UF que atendeu a solicitação.
* **CR09 chNFe:** Chave de Acesso da NF-e.
* **CR10 dhRecbto:** Data e hora de processamento. Formato = AAAA-MM-DDTHH:MM:SS. Preenchido com data e hora da homologação do Pedido.
* **CR11 nProt:** Número do Protocolo de Cancelamento. O controle de numeração de Protocolo será único para todos os serviços.
* **CR12 Signature:** Assinatura XML do grupo identificado pelo atributo "Id". A decisão de assinar a mensagem fica a critério da UF interessada.

**O Protocolo de Cancelamento é um documento importante, pois comprova que a NF-e foi cancelada junto à SEFAZ.** Este protocolo deve ser armazenado pelo emitente da NF-e, juntamente com o arquivo XML da NF-e e o Protocolo de Autorização de Uso, para fins de comprovação e auditoria.

Em caso de processamento com sucesso da inutilização de uma faixa de numeração de NF-e, a SEFAZ retornará um **Protocolo de Inutilização**, que é um arquivo XML contendo informações importantes sobre a inutilização. O leiaute do Protocolo de Inutilização é definido no Manual de Orientação ao Contribuinte - MOC e é composto pelas seguintes informações:

```xml
<retInutNFe versao="4.00" xmlns="http://www.portal.fazenda.gov.br/nfe">
  <infInut Id="ID12345678901234567890123456789012345678901234">
    <tpAmb>1</tpAmb>
    <verAplic>SVRS202108161010</verAplic>
    <cStat>102</cStat>
    <xMotivo>Inutilização de número homologado</xMotivo>
    <cUF>35</cUF>
    <ano>2023</ano>
    <CNPJ>12345678901234</CNPJ>
    <mod>55</mod>
    <serie>0</serie>
    <nNFIni>1</nNFIni>
    <nNFFin>10</nNFFin>
    <dhRecbto>2023-04-20T12:00:00-03:00</dhRecbto>
    <nProt>1234567890123456</nProt>
  </infInut>
</retInutNFe>

```

**Explicação dos campos:**

*   **retInutNFe:** *Tag* raiz da resposta do pedido de inutilização.
    *   **versao:** Versão do leiaute da mensagem de retorno (4.00 neste exemplo).
*   **infInut:** Dados da resposta da inutilização.
    *   **Id:** Identificador da *tag* a ser assinada, contendo o literal "ID" seguido do número do protocolo.
    *   **tpAmb:** Identificação do ambiente.
        *   **1:** Produção.
    *   **verAplic:** Versão do aplicativo da SEFAZ que processou a inutilização.
    *   **cStat:** Código do status da resposta.
        *   **102:** Inutilização de número homologado.
    *   **xMotivo:** Descrição literal do status da resposta.
    *   **cUF:** Código da UF que atendeu a solicitação de inutilização.
    *   **ano:** Ano da inutilização.
    *   **CNPJ:** CNPJ do emitente da NF-e.
    *   **mod:** Modelo da NF-e (55 para NF-e, 65 para NFC-e).
    *   **serie:** Série da NF-e.
    *   **nNFIni:** Número da primeira NF-e da faixa a ser inutilizada.
    *   **nNFFin:** Número da última NF-e da faixa a ser inutilizada.
    *   **dhRecbto:** Data e hora de processamento da inutilização.
    *   **nProt:** Número do Protocolo de Inutilização.

**Observações:**

*   A estrutura do XML de retorno pode variar de acordo com a versão do leiaute utilizada pela SEFAZ.
*   A mensagem de retorno é assinada digitalmente pela SEFAZ, garantindo a autenticidade e integridade das informações.
*   É importante consultar a documentação oficial do Manual de Orientação ao Contribuinte (MOC) para obter a descrição completa dos campos e as regras de validação aplicáveis à versão do leiaute em uso. 

* **DR01 retInutNFe:** TAG raiz da Resposta do Pedido de Inutilização.
* **DR02 versao:** Versão do leiaute.
* **DR03 infInut:** Dados da resposta - TAG a ser assinada.
* **DR04 Id:** Identificador da TAG a ser assinada, somente precisa ser informado se a UF assinar a resposta. Em caso de assinatura da resposta pela SEFAZ preencher o campo com o Número do Protocolo, precedido com o literal "ID". 
* **DR05 tpAmb:** Identificação do Ambiente: 1 - Produção / 2 - Homologação.
* **DR06 verAplic:** Versão do Aplicativo que processou o pedido de inutilização.
* **DR07 cStat:** Código do status da resposta. **102 - Inutilização de número homologado**. Este código indica que a faixa de numeração foi inutilizada com sucesso.
* **DR08 xMotivo:** Descrição literal do status da resposta.
* **DR09 cUF:** Código da UF que atendeu a solicitação.
* **DR10 ano:** Ano de inutilização da numeração.
* **DR11 CNPJ:** CNPJ do emitente da NF-e.
* **DR12 mod:** Modelo da NF-e.
* **DR13 serie:** Série da NF-e.
* **DR14 nNFIni:** Número da primeira NF-e da faixa a ser inutilizada.
* **DR15 nNFFin:** Número da última NF-e da faixa a ser inutilizada.
* **DR16 dhRecbto:** Data e hora de processamento da inutilização. Formato = AAAA-MM-DDTHH:MM:SS.
* **DR17 nProt:** Número do Protocolo de Inutilização. O controle de numeração de Protocolo será único para todos os serviços.
* **DR18 Signature:** Assinatura XML do grupo identificado pelo atributo "Id". A decisão de assinar a mensagem fica a critério da UF interessada.

**O Protocolo de Inutilização é um documento importante, pois comprova que a faixa de numeração de NF-e foi inutilizada junto à SEFAZ.** Este protocolo deve ser armazenado pelo emitente da NF-e para fins de comprovação e auditoria.

Em caso de processamento com sucesso da consulta de status da NF-e, a SEFAZ retornará um arquivo XML contendo informações sobre a situação atual da NF-e na base de dados. O leiaute da resposta da consulta de status é definido no Manual de Orientação ao Contribuinte - MOC e varia de acordo com a versão. 

```xml
<retConsSitNFe versao="4.00" xmlns="http://www.portal.fazenda.gov.br/nfe">
  <tpAmb>1</tpAmb>
  <verAplic>SVRS202108161010</verAplic>
  <cStat>100</cStat>
  <xMotivo>Autorizado o uso da NF-e</xMotivo>
  <cUF>35</cUF>
  <chNFe>35230412345678901234567890123456789012345678</chNFe>
  <dhRecbto>2023-04-20T13:00:00-03:00</dhRecbto>
  <protNFe versao="4.00">
    <infProt Id="ID35230412345678901234567890123456789012345678">
      <tpAmb>1</tpAmb>
      <verAplic>SVRS202108161010</verAplic>
      <chNFe>35230412345678901234567890123456789012345678</chNFe>
      <dhRecbto>2023-04-20T13:00:00-03:00</dhRecbto>
      <nProt>35230412345678901234567890123456789012345678</nProt>
      <digVal>XYZ/abcdefghijklmnopqrstuvwxWZ=</digVal>
      <cStat>100</cStat>
      <xMotivo>Autorizado o uso da NF-e</xMotivo>
    </infProt>
  </protNFe>
</retConsSitNFe>
```

**Explicação dos campos:**

* **retConsSitNFe:** *Tag* raiz da resposta da consulta de situação da NF-e.
    * **versao:** Versão do leiaute da mensagem de retorno (4.00 neste exemplo).
* **tpAmb:** Identificação do ambiente.
    * **1:** Produção.
* **verAplic:** Versão do aplicativo da SEFAZ que processou a consulta.
* **cStat:** Código do status da resposta.
    * **100:** Autorizado o uso da NF-e.
* **xMotivo:** Descrição literal do status da resposta.
* **cUF:** Código da UF que atendeu a solicitação.
* **chNFe:** Chave de acesso da NF-e consultada.
* **dhRecbto:** Data e hora de processamento da consulta.
* **protNFe:** Protocolo de autorização ou denegação de uso da NF-e. (Informações sobre a estrutura do XML `protNFe` foram fornecidas em uma resposta anterior da conversa.)

**Observações:**

* A estrutura do XML de retorno pode variar de acordo com a versão do leiaute utilizada pela SEFAZ.
* A mensagem de retorno é assinada digitalmente pela SEFAZ, garantindo a autenticidade e integridade das informações.
* É importante consultar a documentação oficial do Manual de Orientação ao Contribuinte (MOC) para obter a descrição completa dos campos e as regras de validação aplicáveis à versão do leiaute em uso.
* No caso de localização da NF-e, o retorno deve conter o código de status (`cStat`) com os valores "100 - Autorizado o Uso", "101 - Cancelamento de NF-e Homologado" ou "110 - Uso Denegado".
* Na resposta do *Web Service* de Consulta de Situação da Nota Fiscal, devem ser retornados unicamente os eventos de Cancelamento, Carta de Correção e EPEC. 


A estrutura da resposta pode conter os seguintes elementos, entre outros:

* **ER01 retConsSitNFe:** TAG raiz da Resposta da Consulta de Situação da NF-e.
* **ER02 versao:** Versão do leiaute.
* **ER03 tpAmb:** Identificação do Ambiente: 1 – Produção / 2 – Homologação.
* **ER04 verAplic:** Versão do Aplicativo que processou a consulta.
* **ER05 cStat:** Código do status da resposta.
    * **100 - Uso autorizado:** A NF-e foi autorizada e está válida.
    * **101 - Cancelamento de NF-e homologado:** A NF-e foi cancelada com sucesso.
    * **110 - Uso Denegado:** A autorização de uso da NF-e foi denegada pela SEFAZ.
    * **124 - EPEC Autorizado:** Existe apenas um Evento Prévio de Emissão em Contingência (EPEC) autorizado para a chave de acesso.
* **ER06 xMotivo:** Descrição literal do status da resposta.
* **ER07 cUF:** Código da UF que atendeu a solicitação.
* **ER08 protNFe:** Protocolo de autorização ou denegação de uso do NF-e. 
* **ER09 retCancNFe:** Protocolo de homologação de cancelamento de NF-e. 
* **ER10 procEventoNFe:** Informação do evento e respectivo Protocolo de registro de Evento.

O elemento **procEventoNFe** será retornado apenas se houver eventos vinculados à NF-e, como cancelamento, carta de correção ou EPEC. No caso de haver apenas o EPEC autorizado, a resposta conterá o código de status **124** e o elemento **procEventoNFe** com as informações do EPEC.

É importante observar que a resposta da consulta de status pode variar de acordo com a situação da NF-e e a versão do MOC. Consulte a documentação oficial para obter informações mais detalhadas sobre a estrutura da resposta.

Em caso de processamento com sucesso da consulta de status do serviço, a SEFAZ retornará um arquivo XML contendo informações sobre a situação atual do serviço de autorização de NF-e. O leiaute da resposta da consulta de status do serviço é definido no Manual de Orientação ao Contribuinte - MOC e é composto pelas seguintes informações:

```xml
<retConsStatServ versao="4.00" xmlns="http://www.portal.fazenda.gov.br/nfe">
  <tpAmb>1</tpAmb>
  <verAplic>SVRS202108161010</verAplic>
  <cStat>107</cStat>
  <xMotivo>Serviço em Operação</xMotivo>
  <cUF>35</cUF>
  <dhRecbto>2023-04-20T14:00:00-03:00</dhRecbto>
  <tMed>1</tMed>
  <dhRetorno>2023-04-20T14:02:00</dhRetorno> 
  <xObs>Manutenção programada</xObs>
</retConsStatServ>
```

**Explicação dos campos:**

*   **retConsStatServ:** *Tag* raiz da resposta da consulta de status do serviço.
    *   **versao:** Versão do leiaute da mensagem de retorno (4.00 neste exemplo).
*   **tpAmb:** Identificação do ambiente.
    *   **1:** Produção.
*   **verAplic:** Versão do aplicativo da SEFAZ que processou a consulta.
*   **cStat:** Código do status da resposta.
    *   **107:** Serviço em Operação.
*   **xMotivo:** Descrição literal do status da resposta.
*   **cUF:** Código da UF que atendeu a solicitação.
*   **dhRecbto:** Data e hora de processamento da consulta.
*   **tMed:** Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos.
*   **dhRetorno:** Data e hora previstas para o retorno do Web Service, no formato AAA-MM-DDTHH:MM:SS.
*   **xObs:** Informações adicionais para o contribuinte.

**Observações:**

*   A estrutura do XML de retorno pode variar de acordo com a versão do leiaute utilizada pela SEFAZ.
*   A mensagem de retorno é assinada digitalmente pela SEFAZ, garantindo a autenticidade e integridade das informações.
*   É importante consultar a documentação oficial do Manual de Orientação ao Contribuinte (MOC) para obter a descrição completa dos campos e as regras de validação aplicáveis à versão do leiaute em uso.

O processamento do pedido de consulta de status do serviço pode resultar em uma mensagem de erro ou retornar a situação atual do servidor de processamento, com os códigos de situação **"107 - Serviço em Operação", "108 - Serviço Paralisado Temporariamente" e "109 - Serviço Paralisado sem Previsão"**. A critério da UF, o campo "xObs" pode ser utilizado para fornecer maiores informações ao contribuinte, como por exemplo: "manutenção programada", "modificação de versão do aplicativo", "previsão de retorno", etc.

* **FR01 retConsStatServ:** TAG raiz da Resposta da Consulta de Status do Serviço.
* **FR02 versao:** Versão do leiaute.
* **FR03 tpAmb:** Identificação do Ambiente: 1=Produção/2=Homologação.
* **FR04 verAplic:** Versão do Aplicativo que processou a consulta. A versão deve ser iniciada com a sigla da UF nos casos de WS próprio ou a sigla SVAN ou SVRS nos demais casos.
* **FR05 cStat:** Código do status da resposta (conforme item 4.4.1 do documento MOC – Anexo I – Leiaute NF-e/NFC-e). 
   * **107 - Serviço em Operação:** O serviço de autorização de NF-e está em operação normal.
   * **108 - Serviço Paralisado Momentaneamente (curto prazo):** O serviço de autorização de NF-e está paralisado momentaneamente, com previsão de retorno em curto prazo.
   * **109 - Serviço Paralisado sem Previsão:** O serviço de autorização de NF-e está paralisado sem previsão de retorno.
* **FR06 xMotivo:** Descrição literal do status da resposta.
* **FR07 cUF:** Código da UF que atendeu a solicitação.
* **FR08 dhRecbto:** Data e hora do processamento. Formato: “AAAA-MM-DDThh:mm:ssTZD” (UTC – Universal Coordinated Time).
* **FR09 tMed:** Tempo médio de resposta do serviço (em segundos) dos últimos 5 minutos. 
* **FR10 dhRetorno:** Data e hora previstas para o retorno do Web Service, no formato AAA-MM-DDTHH:MM:SS.
* **FR11 xObs:** Informações adicionais para o Contribuinte.

A critério da UF, o campo **xObs** pode ser utilizado para fornecer maiores informações ao contribuinte, como por exemplo: “manutenção programada”, “modificação de versão do aplicativo”, “previsão de retorno”, etc..

É importante observar que a estrutura da resposta da consulta de status do serviço pode variar slightly entre as diferentes versões do Manual de Orientação ao Contribuinte - MOC.

Em caso de processamento com sucesso da consulta de cadastro de um contribuinte, a SEFAZ retornará um arquivo XML contendo informações sobre a situação cadastral atual do contribuinte no cadastro de contribuintes do ICMS. O leiaute da resposta da consulta de cadastro é definido no Manual de Orientação ao Contribuinte - MOC.

```xml
<retConsCad versao="2.00" xmlns="http://www.portal.fazenda.gov.br/nfe">
  <infCons>
    <verAplic>SVRS202108161010</verAplic>
    <cStat>111</cStat>
    <xMotivo>Consulta cadastro com uma ocorrência</xMotivo>
    <UF>35</UF>
    <CNPJ>12345678901234</CNPJ>
    <dhCons>2023-04-20T15:00:00-03:00</dhCons>
    <cUF>35</cUF>
    <infCad>
      <IE>1234567890123</IE>
      <CNPJ>12345678901234</CNPJ>
      <UF>SP</UF>
      <cSit>1</cSit>
      <xNome>RAZAO SOCIAL DO CONTRIBUINTE</xNome>
      <xFant>NOME FANTASIA</xFant>
      <xRegApur>SIMPLES NACIONAL</xRegApur>
      <CNAE>1234567</CNAE>
      <dIniAtiv>2020-01-01</dIniAtiv>
      <dUltSit>2023-04-20</dUltSit>
      <ender>
        <xLgr>RUA DO CONTRIBUINTE</xLgr>
        <nro>123</nro>
        <xCpl>SALA 456</xCpl>
        <xBairro>BAIRRO DO CONTRIBUINTE</xBairro>
        <cMun>3512345</cMun>
        <xMun>CIDADE DO CONTRIBUINTE</xMun>
        <CEP>12345678</CEP>
        <cPais>1058</cPais>
        <xPais>BRASIL</xPais>
        <fone>123456789</fone>
      </ender>
      <IEUnica>1234567890123</IEUnica>
    </infCad>
  </infCons>
</retConsCad>

```

**Explicação dos campos:**

*   **retConsCad:** *Tag* raiz da resposta da consulta de cadastro.
    *   **versao:** Versão do leiaute da mensagem (2.00 neste exemplo).
*   **infCons:** Grupo com as informações da consulta.
    *   **verAplic:** Versão do aplicativo que processou a consulta.
    *   **cStat:** Código do status da resposta.
        *   **111:** Consulta cadastro com uma ocorrência.
        *   **112:** Consulta cadastro com mais de uma ocorrência (ex: mais de um estabelecimento para o argumento de pesquisa).
    *   **xMotivo:** Descrição do *status* da resposta.
    *   **UF:** Sigla da UF consultada.
    *   **CNPJ:** CNPJ consultado.
    *   **dhCons:** Data e hora de processamento da consulta.
    *   **cUF:** Código da UF que atendeu à solicitação.
    *   **infCad:** Grupo com os dados da situação cadastral.
        *   **IE:** Inscrição estadual do contribuinte.
        *   **CNPJ:** CNPJ do contribuinte.
        *   **UF:** Sigla da UF de localização do contribuinte.
        *   **cSit:** Situação do contribuinte:
            *   **0:** Não habilitado.
            *   **1:** Habilitado.
        *   **xNome:** Razão Social ou Nome do Contribuinte.
        *   **xFant:** Nome Fantasia.
        *   **xRegApur:** Regime de Apuração do ICMS do Contribuinte.
        *   **CNAE:** CNAE principal do contribuinte.
        *   **dIniAtiv:** Data de Início da Atividade do Contribuinte.
        *   **dUltSit:** Data da última modificação da situação cadastral.
        *   **ender:** Grupo de informações do endereço.
            *   **xLgr:** Logradouro.
            *   **nro:** Número.
            *   **xCpl:** Complemento.
            *   **xBairro:** Bairro.
            *   **cMun:** Código do município, conforme tabela do IBGE.
            *   **xMun:** Nome do município.
            *   **CEP:** CEP.
            *   **cPais:** Código do país, conforme tabela do BACEN.
            *   **xPais:** Nome do país.
            *   **fone:** Telefone.
        *   **IEUnica:** Inscrição estadual única, caso o contribuinte possua.

**Observações:**

*   A estrutura do XML de retorno pode variar de acordo com a versão do leiaute utilizada pela SEFAZ.
*   A mensagem de retorno é assinada digitalmente pela SEFAZ.
*   É importante consultar a documentação oficial do Manual de Orientação ao Contribuinte (MOC) para obter a descrição completa dos campos e as regras de validação aplicáveis à versão do leiaute em uso.
*   Na consulta de cadastro, o *Web Service* valida o argumento de pesquisa informado (CNPJ ou CPF ou IE) e retorna a situação cadastral atual do contribuinte no cadastro de contribuintes do ICMS.
*   As regras de validação, como a verificação de dígitos de controle e a validação da inscrição estadual, são detalhadas no documento MOC - Anexo I - Leiaute e Regras de Validação da NF-e e da NFC-e.

Existem diversas regras de validação para a consulta de cadastro, como:

*   Verificar se o CNPJ solicitante é emissor de NF-e.
*   Verificar o dígito de controle do CNPJ ou se o campo está preenchido com zeros.
*   Verificar se o CNPJ consultado está cadastrado como contribuinte na UF.
*   Verificar o dígito de controle da IE ou se o campo está preenchido com zeros.
*   Verificar se a IE consultada está cadastrada como contribuinte na UF.
*   Verificar se a UF permite consulta por CPF.
*   Verificar o dígito de controle do CPF ou se o campo está preenchido com zeros.
*   Verificar se o CPF consultado está cadastrado como contribuinte na UF.
*   Verificar se o ambiente da NF-e é o mesmo do *Web Service*.
*   Verificar se o código da UF consultada é o mesmo da UF do *Web Service*.

Em caso de rejeição, o código e a mensagem de erro serão retornados no XML, de acordo com a tabela de códigos de erros do Manual de Integração do Contribuinte.

A estrutura da resposta da consulta de cadastro com sucesso pode conter os seguintes elementos:

*   **GR01 retConsCad:** TAG raiz da Resposta da Consulta ao Cadastro.
*   **GR02 versao:** Versão do leiaute.
*   **GR03 infCons:** Dados da consulta.
*   **GR04 verAplic:** Versão do Aplicativo que processou a consulta.
*   **GR05 cStat:** Código do status da resposta.
    *   **111 - Consulta cadastro com uma ocorrência:** Indica que a consulta retornou apenas um resultado.
    *   **112 - Consulta cadastro com mais de uma ocorrência:** Indica que a consulta retornou mais de um resultado, por exemplo, quando o contribuinte possui diversos estabelecimentos e inscrição estadual única.
*   **GR06 xMotivo:** Descrição literal do status da resposta.
*   **GR06a UF:** Sigla da UF consultada.
*   **GR06b IE:** Inscrição estadual consultada.
*   **GR06c CNPJ:** CNPJ consultado.
*   **GR06d CPF:** CPF consultado.
*   **GR06e dhCons:** Data e hora de processamento da consulta. Formato = AAAA-MM-DDTHH:MM:SS
*   **GR06f cUF:** Código da UF que atendeu a solicitação.
*   **GR07 infCad:** Grupo com os dados da situação cadastral. Este grupo só estará presente se a consulta for realizada com sucesso (cStat=111) e pode ter múltiplas ocorrências.

Dentro do grupo **GR07 infCad**, podem estar presentes as seguintes informações sobre o contribuinte:

*   **GR08 IE:** Inscrição estadual do contribuinte.
*   **GR09 CNPJ:** CNPJ do contribuinte.
*   **GR10 CPF:** CPF do contribuinte, caso seja uma pessoa física com IE.
*   **GR11 UF:** Sigla da UF de localização do contribuinte.
*   **GR12 cSit:** Situação do contribuinte: 0 - não habilitado; 1 - habilitado.
*   **GR12a indCredNFe:** Indicador de contribuinte credenciado a emitir NF-e.
    *   0=Não credenciado para emissão da NF-e;
    *   1=Credenciado;
    *   2=Credenciado com obrigatoriedade para todas as operações;
    *   3=Credenciado com obrigatoriedade parcial;
    *   4=a SEFAZ não fornece a informação.
*   **GR12b indCredCTe:** Indicador de contribuinte credenciado a emitir CT-e.
    *   0=Não credenciado para emissão da CT-e;
    *   1=Credenciado;
    *   2=Credenciado com obrigatoriedade para todas as operações;
    *   3=Credenciado com obrigatoriedade parcial;
    *   4=a SEFAZ não fornece a informação.
*   **GR13 xNome:** Razão Social ou nome do Contribuinte.
*   **GR13a xFant:** Nome Fantasia.
*   **GR14 xRegApur:** Regime de Apuração do ICMS do Contribuinte.
*   **GR15 CNAE:** CNAE principal do contribuinte.
*   **GR16 dIniAtiv:** Data de Início da Atividade do Contribuinte.
*   **GR17 dUltSit:** Data da última modificação da situação cadastral do contribuinte.
*   **GR18 dBaixa:** Data de ocorrência da baixa do contribuinte.
*   **GR20 IEUnica:** IE única, este campo será informado quando o contribuinte possuir IE única.
*   **GR21 IEAtual:** IE atual (em caso de IE antiga consultada).
*   **GR22 ender:** Grupo com informações sobre o endereço do contribuinte.

É importante observar que a estrutura da resposta da consulta de cadastro pode variar entre as diferentes versões do Manual de Orientação ao Contribuinte - MOC. Consulte a documentação oficial para obter informações mais detalhadas sobre a estrutura da resposta. 

Em caso de erro no processamento da solicitação da NF-e pela SEFAZ, o Web Service retornará uma mensagem de resposta SOAP contendo um código de erro (cStat) e uma descrição textual do erro (xMotivo). As fontes fornecem uma tabela detalhada com os códigos de erro e suas descrições, listados a seguir:

```xml
<retConsCad versao="2.00" xmlns="http://www.portal.fazenda.gov.br/nfe">
  <infCons>
    <verAplic>SVRS202108161010</verAplic>
    <cStat>258</cStat>
    <xMotivo>Rejeição: CNPJ da consulta inválido</xMotivo>
    <UF>35</UF>
    <CNPJ>12345678901234</CNPJ>
    <dhCons>2023-04-20T15:00:00-03:00</dhCons>
    <cUF>35</cUF>
  </infCons>
</retConsCad>
```

**Explicação dos campos:**

*   **retConsCad:** *Tag* raiz da resposta da consulta de cadastro.
    *   **versao:** Versão do leiaute da mensagem (2.00 neste exemplo).
*   **infCons:** Grupo com as informações da consulta.
    *   **verAplic:** Versão do aplicativo que processou a consulta.
    *   **cStat:** Código do status da resposta.
        *   **258:** Rejeição: CNPJ da consulta inválido.
    *   **xMotivo:** Descrição do *status* da resposta.
    *   **UF:** Sigla da UF consultada.
    *   **CNPJ:** CNPJ consultado.
    *   **dhCons:** Data e hora de processamento da consulta.
    *   **cUF:** Código da UF que atendeu à solicitação.

Observe que, neste caso de erro, **o grupo `infCad` com os dados do cadastro não está presente** porque a consulta não foi processada com sucesso.
    
**Observações:**

*   A estrutura do XML de erro pode variar de acordo com a versão do leiaute e o Web Service utilizado.
*   A mensagem de erro é assinada digitalmente pela SEFAZ.
*   Para interpretar o código de erro (`cStat`) e a descrição do erro (`xMotivo`), consulte a documentação oficial do Manual de Orientação ao Contribuinte (MOC) e o Manual de Integração do Contribuinte.

Lembre-se que as regras de validação e os códigos de erro podem ser diferentes entre as versões dos leiautes e Web Services. Consulte sempre a documentação oficial para obter informações precisas.

**Códigos de Rejeição (cStat):**

* **204:** Rejeição: Duplicidade de NF-e 
* **207:** Rejeição: CNPJ do emitente inválido
* **208:** Rejeição: CNPJ do destinatário inválido
* **209:** Rejeição: IE do emitente inválida 
* **210:** Rejeição: IE do destinatário inválida
* **211:** Rejeição: IE do substituto inválida
* **212:** Rejeição: Data de emissão NF-e posterior a data de recebimento 
* **213:** Rejeição: CNPJ-Base do Emitente difere do CNPJ-Base do Certificado Digital
* **214:** Rejeição: Tamanho da mensagem excedeu o limite estabelecido
* **215:** Rejeição: Falha no schema XML
* **217:** Rejeição: NF-e não consta na base de dados da SEFAZ
* **218:** Rejeição: NF-e já está cancelada na base de dados da SEFAZ 
* **220:** Rejeição: NF-e autorizada há mais de 7 dias (168 horas)
* **222:** Rejeição: Protocolo de Autorização de Uso difere do cadastrado
* **223:** Rejeição: CNPJ/CPF do transmissor do lote difere do CNPJ/CPF do transmissor da consulta
* **226:** Rejeição: Código da UF do Emitente diverge da UF autorizadora
* **227:** Rejeição: Erro na Chave de Acesso – Literal NFe inexistente
* **228:** Rejeição: Data de Emissão muito atrasada
* **229:** Rejeição: IE do emitente não informada
* **235:** Rejeição: Inscrição SUFRAMA inválida
* **236:** Rejeição: Chave de Acesso com dígito verificador inválido
* **237:** Rejeição: CPF do destinatário inválido
* **238:** Rejeição: Cabeçalho - Versão do arquivo XML superior a Versão vigente
* **239:** Rejeição: Cabeçalho - Versão do arquivo XML não suportada
* **240:** Rejeição: Cancelamento/Inutilização – Irregularidade Fiscal do Emitente
* **241:** Rejeição: Um número da faixa já foi utilizado 
* **243:** Rejeição: XML Mal Formado
* **247:** Rejeição: Sigla da UF do Emitente diverge da UF autorizadora
* **248:** Rejeição: UF do Recibo diverge da UF autorizadora 
* **249:** Rejeição: UF da Chave de Acesso diverge da UF autorizadora
* **251:** Rejeição: UF/Município destinatário não pertence a SUFRAMA
* **252:** Rejeição: Ambiente informado diverge do Ambiente de recebimento
* **266:** Rejeição: Série utilizada não permitida no Web Service
* **273:** Rejeição: Código Município do Emitente: difere da UF do emitente
* **274:** Rejeição: Código Município do Destinatário: dígito inválido
* **275:** Rejeição: Código Município do Destinatário: difere da UF do Destinatário
* **289:** Rejeição: Código da UF informada diverge da UF solicitada
* **290:** Rejeição: Certificado Assinatura inválido
* **291:** Rejeição: Certificado Assinatura Data Validade
* **292:** Rejeição: Certificado Assinatura sem CNPJ
* **293:** Rejeição: Certificado Assinatura - erro Cadeia de Certificação
* **294:** Rejeição: Certificado Assinatura revogado
* **295:** Rejeição: Certificado Assinatura difere ICP-Brasil
* **296:** Rejeição: Certificado Assinatura erro no acesso a LCR
* **297:** Rejeição: Assinatura difere do calculado
* **298:** Rejeição: Assinatura difere do padrão do Projeto
* **299:** Rejeição: XML da área de cabeçalho com codificação diferente de UTF-8
* **402:** Rejeição: XML da área de dados com codificação diferente de UTF-8
* **403:** Rejeição: O grupo de informações da NF-e avulsa é de uso exclusivo do Fisco
* **404:** Rejeição: Uso de prefixo de namespace não permitido
* **409:** Rejeição: Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header
* **410:** Rejeição: UF informada no campo cUF não é atendida pelo Web Service
* **411:** Rejeição: Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header
* **417:** Rejeição: Total do ICMS superior ao valor limite estabelecido
* **450:** Rejeição: Modelo da NF-e diferente de 55
* **451:** Rejeição: Processo de emissão informado inválido
* **453:** Rejeição: Ano de inutilização não pode ser superior ao Ano atual
* **454:** Rejeição: Ano de inutilização não pode ser inferior a 2006
* **455:** Rejeição: Órgão Autor do evento difere da UF da Chave de Acesso
* **464:** Rejeição: Código de Hash no QR-Code difere do calculado
* **466:** Rejeição: Evento com Tipo de Autor incompatível
* **478:** Rejeição: Local da entrega não informado para faturamento direto de veículos novos
* **479:** Rejeição: Emissor em situação irregular perante o fisco
* **480:** Rejeição: CNPJ da Chave de acesso da NF-e informada diverge do CNPJ do emitente
* **481:** Rejeição: UF da Chave de acesso diverge do código da UF informada
* **482:** Rejeição: AA da Chave de acesso inválida
* **483:** Rejeição: MM da chave de acesso inválido
* **484:** Rejeição: DV da Chave de acesso inválida
* **485:** Rejeição: Chave de acesso já existe no cadastro de DPEC
* **486:** Rejeição: DPEC não localizada para o número de registro de DPEC informado
* **487:** Rejeição: Nenhuma DPEC localizada para a chave de acesso informada
* **490:** Rejeição: CPF informado inválido (DV ou zeros)
* **494:** Rejeição: Chave de Acesso Inexistente (chNFe:999...999]
* **501:** Rejeição: Prazo de cancelamento superior ao previsto na Legislação
* **502:** Rejeição: Erro na Chave de Acesso - Campo ID não corresponde à concatenação dos campos correspondentes
* **503:** Rejeição: Série utilizada fora da faixa permitida no SCAN (900-999)
* **507:** Rejeição: O CNPJ do destinatário/remetente não deve ser informado em operação com o exterior
* **508:** Rejeição: O CPF do destinatário/remetente não deve ser informado em operação com o exterior
* **509:** Rejeição: O CNPJ com conteúdo nulo só é válido em operação com exterior
* **510:** Rejeição: Operação com Exterior e Código País destinatário é 1058 (Brasil) ou não informado
* **511:** Rejeição: Não é de Operação com Exterior e Código País destinatário difere de 1058 (Brasil)
* **512:** Rejeição: CNPJ do Local de Retirada inválido
* **514:** Rejeição: CNPJ do Local de Entrega inválido
* **515:** Rejeição: Código Município do Local de Entrega deve ser 9999999 para UF entrega = EX
* **526:** Rejeição: Consulta a uma Chave de Acesso muito antiga
* **540:** Rejeição: CPF do Local de Retirada inválido
* **541:** Rejeição: CPF do Local de Entrega inválido
* **542:** Rejeição: CNPJ do Transportador inválido
* **543:** Rejeição: CPF do Transportador inválido
* **544:** Rejeição: IE do Transportador inválido
* **553:** Rejeição: Tipo autorizador do recibo diverge do Órgão Autorizador.
* **556:** Rejeição: Justificativa de entrada em contingência não deve ser informada para tipo de emissão normal.
* **558:** Rejeição: Data de entrada em contingência posterior a data de emissão.
* **559:** Rejeição: UF do Transportador não informado
* **560:** Rejeição: CNPJ base do emitente difere do CNPJ base da primeira NF-e do lote recebido
* **561:** Rejeição: Mês de Emissão informado na Chave de Acesso difere do Mês de Emissão da NF-e
* **563:** Rejeição:  Já existe pedido de Inutilização com a mesma faixa de inutilização
* **574:** Rejeição: Autor do evento diverge do emissor da NF-e
* **577:** Rejeição: A data do evento não pode ser menor que a data de emissão da NF-e
* **578:** Rejeição: A data do evento não pode ser maior que a data do processamento
* **591:** Rejeição: Informado CSOSN para emissor que não e do Simples Nacional (CRT diferente de 1)
* **594:** Rejeição: O número de sequencia do evento informado é maior que o permitido
* **595:** Rejeição: Obrig.atória a informação da justificativa do evento.
* **613:** Rejeição: Chave de Acesso difere da existente em BD
* **614:** Rejeição: Chave de Acesso inválida (Código UF inválido)
* **615:** Rejeição: Chave de Acesso inválida (Ano < 06 ou Ano maior que Ano corrente)
* **616:** Rejeição: Chave de Acesso inválida (Mês < 1 ou Mês > 12)
* **617:** Rejeição: Chave de Acesso inválida (CNPJ/CPF zerado ou dígito inválido)
* **618:** Rejeição: Chave de Acesso inválida (modelo diferente de 55 e 65)
* **619:** Rejeição: Chave de Acesso inválida (número NF = 0)
* **628:** Rejeição: Total da NF superior ao valor limite estabelecido pela SEFAZ [Limite]
* **659:** Rejeição: Ano-Mês da Data de Emissão diverge do Ano_Mês da Chave de Acesso
* **704:** Rejeicao: NFC-e com Data-Hora de emissão atrasada
* **778:** Rejeicao: Informado NCM inexistente
* **784:** Rejeição: NFC-e não permite o evento de Carta de Correção
* **811:** Rejeição: Pedido de Prorrogação deferido impede o cancelamento da NF-e 
* **869:** Rejeicao: Valor do troco incorreto
* **920:** Rejeição: Tipo de emissão da NF-e a ser cancelada deve ser normal
* **999:** Rejeição: Erro não catalogado (informar a mensagem de erro capturado no tratamento da exceção)

**Códigos de Denegação de Uso (cStat):**

* **301:** Uso Denegado: Irregularidade fiscal do emitente 
* **302:** Uso Denegado: Irregularidade fiscal do destinatário 

**Códigos de Status do Serviço (cStat):**

* **105:** Lote em processamento
* **106:** Lote não localizado
* **107:** Serviço em Operação
* **108:** Serviço Paralisado Momentaneamente (curto prazo)
* **109:** Serviço Paralisado sem Previsão 

É fundamental que o aplicativo do contribuinte seja capaz de interpretar esses códigos de erro e tomar as ações necessárias, como exibir mensagens informativas ao usuário, corrigir as informações da NF-e e reenviar a solicitação. As descrições textuais (xMotivo) fornecem informações mais detalhadas sobre a causa do erro, auxiliando na identificação e correção do problema.
















