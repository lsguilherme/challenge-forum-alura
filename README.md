# Challenge Alura + ONE - Fórum

Este projeto é uma implementação de uma API RESTful para um fórum, desenvolvida como parte do desafio Alura + ONE. A aplicação permite o gerenciamento de tópicos, incluindo operações de criação, leitura, atualização e "exclusão lógica" (soft delete).

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.3 (Spring Boot Starter Parent)**
* **Spring Data JPA**
* **Spring Boot Starter Validation**
* **Spring Boot Starter Web**
* **PostgreSQL** (Banco de dados)
* **Flyway** (Migrações de Banco de Dados)
* **MapStruct** (Mapeamento de DTOs para Entidades)

## Funcionalidades

* **Criação de Tópicos**: Permite a criação de novos tópicos com título, mensagem, autor e curso.
* **Listagem de Tópicos**: Consulta paginada de todos os tópicos ativos, ordenados por data de criação.
* **Busca por ID**: Recupera um tópico específico pelo seu ID.
* **Atualização de Tópicos**: Permite a atualização de informações de um tópico existente.
* **Exclusão Lógica (Soft Delete)**: Os tópicos são desativados em vez de serem fisicamente removidos do banco de dados, mantendo o histórico.
* **Validação de Entrada**: Utiliza Jakarta Validation para validar os dados de entrada das requisições.
* **Tratamento Global de Exceções**: Captura e trata exceções de forma centralizada para retornar respostas de erro padronizadas.

## Estrutura do Banco de Dados

O projeto utiliza Flyway para gerenciar as migrações do banco de dados.

* `V1__create-table-topicos.sql`: Cria a tabela `topicos` com campos como `id`, `titulo`, `mensagem`, `criado_em`, `estado`, `autor` e `curso`.
* `V2__alter-table-topicos-add-ativo.sql`: Adiciona a coluna `ativo` do tipo `boolean` à tabela `topicos`, define o valor inicial como `true` e a torna `NOT NULL`.

## Configuração e Execução

### Pré-requisitos

* Java 21 ou superior
* Maven
* PostgreSQL

### Configuração do Banco de Dados

1.  Certifique-se de ter uma instância do PostgreSQL em execução.
2.  Crie um banco de dados chamado `forum_alura`.
3.  Configure as credenciais do banco de dados no arquivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/forum_alura
    spring.datasource.username=postgres
    spring.datasource.password=123456
    spring.datasource.driver-class-name=org.postgresql.Driver
    ```
    *Altere `username` e `password` conforme sua configuração local.*

### Executando a Aplicação

1.  Navegue até o diretório raiz do projeto.
2.  Execute o comando Maven para construir e rodar a aplicação:

    ```bash
    mvn spring-boot:run
    ```
    A aplicação estará acessível em `http://localhost:8080`.

## Endpoints da API

A API está disponível no caminho `/topicos`.

* **`POST /topicos`**
    * **Descrição**: Cria um novo tópico.
    * **Corpo da Requisição**:
        ```json
        {
            "titulo": "Título do Tópico",
            "mensagem": "Mensagem detalhada do tópico.",
            "autor": "Nome do Autor",
            "curso": "Nome do Curso"
        }
        ```
    * **Resposta**: `200 OK` com o `TopicoResponseDto` do tópico criado.

* **`GET /topicos/{id}`**
    * **Descrição**: Busca um tópico pelo seu ID.
    * **Resposta**: `200 OK` com o `TopicoResponseDto`.
    * **Erro**: `404 Not Found` se o tópico não for encontrado ou estiver inativo.

* **`GET /topicos`**
    * **Descrição**: Lista todos os tópicos ativos com paginação.
    * **Parâmetros de Consulta**:
        * `page`: Número da página (padrão: 0).
        * `size`: Quantidade de itens por página (padrão: 20).
        * `sort`: Campo para ordenação e direção (ex: `criadoEm,asc`). O padrão é `criadoEm,asc`.
    * **Resposta**: `200 OK` com uma página de `TopicoResponseDto`.

* **`PUT /topicos/{id}`**
    * **Descrição**: Atualiza um tópico existente pelo ID.
    * **Corpo da Requisição**:
        ```json
        {
            "titulo": "Novo Título",
            "mensagem": "Nova Mensagem",
            "autor": "Novo Autor",
            "curso": "Novo Curso"
        }
        ```
    * **Resposta**: `200 OK` com o `TopicoResponseDto` atualizado.
    * **Erro**: `404 Not Found` se o tópico não for encontrado.

* **`DELETE /topicos/{id}`**
    * **Descrição**: Desativa (soft delete) um tópico pelo seu ID.
    * **Resposta**: `204 No Content` se a operação for bem-sucedida.
    * **Erro**: `404 Not Found` se o tópico não for encontrado ou já estiver inativo.