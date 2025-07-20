# Challenge Alura + ONE - Fórum

Este projeto é uma implementação de uma API RESTful para um fórum, desenvolvida como parte do desafio Alura + ONE. A aplicação permite o gerenciamento de tópicos, incluindo operações de criação, leitura, atualização e "exclusão lógica" (soft delete), e agora conta com autenticação e autorização via JWT.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.3 (Spring Boot Starter Parent)**
* **Spring Data JPA**
* **Spring Boot Starter Validation**
* **Spring Boot Starter Web**
* **Spring Security** (Autenticação e Autorização)
* **JWT (JSON Web Tokens)**
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
* **Autenticação e Autorização**: Implementa um sistema de login com Spring Security e JWT para proteger os endpoints da API.

## Estrutura do Banco de Dados

O projeto utiliza Flyway para gerenciar as migrações do banco de dados.

* `V1__create-table-topicos.sql`: Cria a tabela `topicos` com campos como `id`, `titulo`, `mensagem`, `criado_em`, `estado`, `autor` e `curso`.
* `V2__alter-table-topicos-add-ativo.sql`: Adiciona a coluna `ativo` do tipo `boolean` à tabela `topicos`, define o valor inicial como `true` e a torna `NOT NULL`.
* `V3__create-table-usuarios.sql`: Cria a tabela `usuarios` para armazenar informações de login e senha.
* `V4__insert-into-usuarios.sql`: Insere um usuário de exemplo (`teste` com senha criptografada).
* `V5__create-table-roles.sql`: Cria a tabela `role` para definir os perfis de usuário (ex: `ROLE_USER`, `ROLE_ADMIN`).
* `V6__create-table-users-roles.sql`: Cria a tabela de junção `usuarios_roles` para mapear usuários a seus respectivos papéis.
* `V7__insert-into-default-roles.sql`: Insere os papéis padrão (`ROLE_USER`, `ROLE_ADMIN`) na tabela `role`.
* `V8__alter-table-usuarios-add-account-status-columns.sql`: Adiciona colunas para controle de status da conta do usuário (expiração, bloqueio, etc.).
* `V9__insert-into-usuarios-add-roles.sql`: Associa o usuário de exemplo (`teste`) ao papel `ROLE_USER`.

## Configuração e Execução

### Pré-requisitos

* Java 21 ou superior
* Maven
* PostgreSQL
* Chaves RSA (pública e privada) para JWT.

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

### Configuração JWT

As chaves JWT são carregadas a partir de arquivos `private_key.pem` e `public_key.pem` localizados em `src/main/resources`. Certifique-se de que esses arquivos existam e contenham as chaves RSA válidas.

```properties
jwt.private.key=classpath:private_key.pem
jwt.public.key=classpath:public_key.pem