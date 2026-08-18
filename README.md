# 🎙️ My Finances - AI Voice Assistant (DIO Spring AI Challenge)

Este projeto foi desenvolvido como resolução do desafio **"Evoluindo uma API com Spring AI e Tool Calling"** da [Digital Innovation One (DIO)](https://www.dio.me/).

Trata-se de uma API de gestão financeira (Orçamento Pessoal) onde a principal forma de interação do usuário não é clicando em botões, mas enviando **mensagens de voz (Áudio)**. A aplicação utiliza o ecosistema do **Spring AI 2.0** para ouvir o comando, interpretar a intenção do usuário usando o modelo Gemini da Google, executar as lógicas de negócio no banco de dados e devolver uma resposta também em formato de **áudio sintético**.

---

## 🎯 O Que o Projeto Faz?
O fluxo principal funciona assim:
1. O usuário envia um arquivo de áudio (`.mp3`) para a rota `/ai`. Exemplo: *"Gastei 50 reais de gasolina hoje."*
2. O modelo Multimodal do Google Gemini recebe o áudio diretamente (sem necessidade de um serviço separado de Speech-to-Text).
3. O Gemini entende a intenção e utiliza o recurso de **Tool Calling** para acionar automaticamente as funções Java internas da nossa API.
4. O sistema cria a transação de despesa, receita ou transferência no banco de dados.
5. A inteligência artificial gera um texto de confirmação.
6. A API conecta-se com o serviço da **ElevenLabs (Text-to-Speech)** para transformar a resposta de texto em voz.
7. A requisição retorna um novo arquivo `.mp3` para o usuário com a confirmação ("*Sua despesa de 50 reais foi registrada com sucesso!*").

---

## ✨ Melhorias Implementadas (Evoluindo o Projeto Base)
Além de completar o roteiro principal proposto pelo expert, esta solução foi robustamente evoluída nas seguintes frentes:

1. **Clean Architecture (Hexagonal)**: A lógica foi inteiramente desacoplada. Os Controllers não conhecem detalhes do Spring AI. Foram criados **UseCases** específicos e **Adapters** para garantir que a Regra de Negócio não seja poluída por frameworks externos.
2. **Atualização para Spring AI 2.0 (Modern Tool Calling)**: Migração dos antigos Beans do tipo `Function` (agora desencorajados na documentação) para o moderno ecossistema usando a anotação `@Tool` em métodos específicos injetados dinamicamente no `ChatClient`.
3. **Filtros Dinâmicos Profissionais (JPA Specifications)**: A rota de `GET /transactions` foi evoluída para suportar pesquisas dinâmicas complexas utilizando a *Criteria API* e `JpaSpecificationExecutor`. Agora é possível filtrar requisições via *Query Params* como `?type=INCOME`, `?categoryId=UUID` e `?date=YYYY-MM-DD` de forma segura.
4. **Tratamento de Exceções Globais**: Criação de `AIExceptionHandler` e exceções de domínio como `AIException` para interceptar falhas na IA e retornar um JSON formatado amigável, abandonando as antigas *stack traces* brutas de erro 500.
5. **Mapeamento de Domínio Blindado**: Correção de bugs sensíveis de NPE (Null Pointer Exceptions) no relacionamento polimórfico entre Contas (Recebedor vs Pagador), ajustando as entidades JPA e arquivos do Flyway.
6. **API Playground Customizado**: Substituição do clássico Swagger/OpenAPI por uma documentação interativa "Faça-você-mesmo" construída puramente em HTML/CSS/JS com estilo Neo-Brutalista. Permite o disparo de endpoints via Fetch API diretamente pelo navegador (incluindo o envio de Multipart Files e injeção do player de áudio na tela).

---

## 🛠️ Tecnologias Utilizadas
* **Java 17+**
* **Spring Boot 4.x**
* **Spring AI 2.0.0** (ChatClient, Tool Calling, Multimodal, Text-to-Speech)
* **Google Gemini API** (`gemini-1.5-flash`)
* **ElevenLabs API** (Síntese de voz)
* **PostgreSQL** (Banco de Dados Relacional)
* **Flyway** (Migrations / Versionamento de Banco)
* **Docker / Docker Compose** (`spring-boot-docker-compose`)
* **Spring Data JPA** (com *Criteria API* e *Specifications*)

---

## 🚀 Como Executar a Aplicação

### 1. Pré-requisitos
* Ter o **Docker Desktop** (ou Docker Engine) rodando na sua máquina.
* Ter uma chave de API do **Google AI Studio** (Gemini).
* Ter uma chave de API da **ElevenLabs**.

### 2. Configurando as Variáveis
Vá até o arquivo `src/main/resources/application.properties` (ou configure no ambiente do seu sistema) e insira as chaves reais:
```properties
spring.ai.google.genai.api-key=SUA_CHAVE_AQUI
spring.ai.elevenlabs.api-key=SUA_CHAVE_AQUI
```

### 3. Subindo o Projeto
Não é necessário iniciar o banco manualmente. O projeto utiliza o `spring-boot-docker-compose`. 
Basta rodar a aplicação pela sua IDE (IntelliJ, Eclipse, VS Code) ou via Maven:
```bash
./mvnw spring-boot:run
```
O Spring irá automaticamente instanciar um container do PostgreSQL baseado no arquivo `compose.yaml` e executar as migrations do Flyway.

---

## 🧪 Como Testar o Fluxo Principal

### 1. Testando pelo API Playground (Recomendado)
A aplicação conta com uma interface interativa customizada para documentação e disparo de endpoints!
Basta rodar a aplicação e acessar no seu navegador:
👉 **`http://localhost:8080/`**

Lá você encontrará todos os módulos da API (Contas, Bancos, Categorias, Transações e Assistente IA). Para testar a IA:
1. Abra o módulo **Voice Assistant** e expanda o endpoint `POST /ai`.
2. Selecione um arquivo `.mp3` gravado com a sua voz (ex: *"Gastei 50 reais com supermercado"*).
3. Clique em **Execute Request**.
4. O *Playground* renderizará um Player de Áudio nativo para você escutar a resposta da IA na hora!

### 2. Testando pelo HTTP Client do IntelliJ (ou Postman)
Para testar o *Voice Assistant* via cliente HTTP, utilize requisições do tipo **Multipart Form-Data**. Grave um pequeno áudio (Ex: `teste.mp3`) falando: *"Depositei 100 reais na minha conta."*

Se for testar pelo **IntelliJ HTTP Client**, crie um arquivo `.http` e rode:
```http
POST http://localhost:8080/ai
Content-Type: multipart/form-data; boundary=WebAppBoundary

--WebAppBoundary
Content-Disposition: form-data; name="file"; filename="teste.mp3"
Content-Type: audio/mpeg

< ./teste.mp3
--WebAppBoundary--
```
Se for pelo **Postman**:
1. Crie um `POST` para `http://localhost:8080/ai`.
2. Na aba **Body**, selecione `form-data`.
3. Adicione uma key chamada `file`, mude o tipo de texto para **File**, e anexe o seu `.mp3`.
4. Envie. Você deverá receber um áudio `.mp3` de volta!

### 2. Testando a Rota de Transações com Filtros Avançados
Para consultar como a IA modificou o seu banco, use a rota evoluída de filtros:
```http
GET http://localhost:8080/transactions
GET http://localhost:8080/transactions?type=EXPENSE
GET http://localhost:8080/transactions?type=INCOME&date=2026-08-17
```

---

## 📚 O Que Eu Aprendi Durante o Desafio
Este desafio foi um verdadeiro divisor de águas. Os principais aprendizados foram:
* **Tool Calling na Prática:** A inteligência artificial não é só um "papagaio de chat". Entender como amarrar funções determinísticas (Java) com o não-determinismo da IA (LLM) permite a construção de Agentes Autônomos incrivelmente poderosos.
* **Spring AI Abstraction:** Aprendi a trocar e combinar os modelos de empresas diferentes (Gemini para o cérebro/reconhecimento e ElevenLabs para a fala) usando as interfaces unificadas do Spring AI, sem precisar baixar dependências diferentes para cada empresa.
* **Mapeamento Spring vs Banco:** Reforcei conhecimentos dolorosos porém cruciais sobre as exigências de compatibilidade entre atributos de entidade (JPA `@Column`) e a nomenclatura real das colunas criadas no banco de dados (`Flyway`).
* **JPA Specifications:** Trocar as centenas de Queries em formato String para utilizar a Criteria API e as `Specifications` dinâmicas me mostrou como escalar buscas de APIs grandes profissionalmente.

---
*Desafio concluído com sucesso, com muitos bugs desbravados e noites intensas de aprendizado!* 🚀
