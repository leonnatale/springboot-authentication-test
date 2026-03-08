## Sobre

O projeto é um estudo de Spring Boot com Java, utilizando autenticação JWT. Este projeto serve como um teste para o entendimento e prática das tecnologias envolvidas, incluindo Spring Security, JPA, Flyway para migrações de banco de dados e validação de dados. A aplicação fornece uma API REST simples para gerenciamento de uma entidade chamada Person, com funcionalidades básicas de criação e consulta, "protegidas" por autenticação baseada em tokens JWT.

## Tecnologias Utilizadas

- **Java**: Versão 21
- **Spring Boot**: Versão 4.0.3
- **Banco de Dados**: MySQL
- **Autenticação**: JWT (JSON Web Tokens) com biblioteca Auth0
- **ORM**: Spring Data JPA
- **Migrações**: Flyway
- **Validação**: Bean Validation
- **Build Tool**: Gradle
- **Outros**: Lombok para redução de boilerplate

## Configuração (Variáveis de ambiente)

1. **Banco de Dados**:
   - Configure as variáveis de ambiente:
     - `DB_HOST`: Host do banco (ex: localhost:3306)
     - `DB_NAME`: Nome do banco de dados
     - `DB_USER`: Usuário do banco
     - `DB_PASSWORD`: Senha do usuário

2. **JWT**:
   - Configure as variáveis de ambiente para o JWT:
     - `SECRET_SALT`: Chave secreta para assinatura do token
     - `SECRET_ISSUER`: Emissor do token

## Estrutura

- `configuration/`: Configurações, incluindo segurança
- `controller/`: Controladores REST
- `dto/`: Objetos de transferência de dados
- `filter/`: Filtros, incluindo autenticação
- `group/`: Grupos de validação
- `model/`: Modelos de dados
- `repositories/`: Repositórios JPA
- `service/`: Serviços da aplicação

## API Endpoints

A API fornece os seguintes endpoints:

- `GET /people/me`: Retorna os dados da pessoa autenticada
- `GET /people/me/token`: Gera um novo token JWT para a pessoa autenticada
- `GET /people/{id}`: Retorna os dados de uma pessoa pelo ID
- `POST /people`: Cria uma nova pessoa

### Autenticação

Este projeto foi desenvolvido como um estudo para entender o funcionamento do JWT (JSON Web Tokens) no contexto do Spring Security. A autenticação pode ser feita de duas maneiras simplificadas para fins educacionais:

- **Com JWT**: Inclua o header `Authorization: Bearer <token>` nas requisições. O token é gerado via endpoint `/people/me/token` e contém informações do usuário de forma segura e assinada.
- **Com ID do usuário**: Para simplificar o estudo, também é possível autenticar apenas com o ID do usuário no header `Authorization: (id)`, sem necessidade de credenciais adicionais.

> [!NOTE]
> Em um projeto real, a autenticação seria baseada em credenciais válidas (como username e senha), com geração e validação adequada de tokens JWT, seguindo boas práticas de segurança. Este projeto é apenas para o meu entendimento do Spring Security, ou seja, apenas um estudo!