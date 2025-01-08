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
  

### Requisitos ###

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


Em caso de erro no processamento da solicitação da NF-e pela SEFAZ, o Web Service retornará uma mensagem de resposta SOAP contendo um código de erro (cStat) e uma descrição textual do erro (xMotivo). As fontes fornecem uma tabela detalhada com os códigos de erro e suas descrições, listados a seguir:

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


















