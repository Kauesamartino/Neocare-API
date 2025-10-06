# Neocare - Sistema de Gestão de Dados para Monitoramento de Estresse

Neocare é uma aplicação para gestão de uma plataforma de monitoramento de estresse, desenvolvida com Spring Boot e seguindo os princípios da Arquitetura Limpa (Clean Architecture). O sistema permite o gerenciamento de usuários, validações de dados e integração com banco de dados PostgreSQL.

## 📋 Funcionalidades

### Gestão de Usuários
- Cadastro de novos usuários
- Edição de dados cadastrais
- Consulta de usuários por CPF
- Listagem de todos os usuários ativos
- Ativação/desativação de usuários

### Validações de Domínio
- Validação de CPF
- Validação de formato de e-mail e telefone
- Validação de idade mínima
- Validação de peso e altura

## 🏗️ Arquitetura

O projeto segue os princípios da Arquitetura Limpa (Clean Architecture), organizando o código em camadas:

- **Domain**: Entidades e regras de negócio
- **Application**: Casos de uso da aplicação
- **Infrastructure**: Persistência, integração e API REST
- **Interfaces**: Contratos entre camadas e DTOs

## 🛠️ Tecnologias Utilizadas

- **Spring Boot**: Framework Java para aplicações modulares e escaláveis
- **PostgreSQL**: Banco de dados relacional
- **JPA/Hibernate**: Persistência de dados
- **Flyway**: Migração de banco de dados
- **Spring Doc**: Documentação automática da API (OpenAPI 3)
- **Maven**: Gerenciamento de dependências e build
- **JUnit**: Testes automatizados

## 🚀 Executando a Aplicação

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.8.1 ou superior
- Docker ou PostgreSQL

### Configuração do Banco de Dados
1. Crie um banco de dados PostgreSQL chamado `neocare`
2. Configure as credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/neocare
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=validate
```

### Executando em Modo de Desenvolvimento
```shell
./mvnw spring-boot:run
```

> **Nota:** A documentação da API estará disponível em http://localhost:8080/swagger-ui.html

### Empacotando a Aplicação
```shell
./mvnw package
```

Isso produz o arquivo `neocare-api.jar` no diretório `target/`.

### Executando a Aplicação Empacotada
```shell
java -jar target/neocare-api.jar
```

## 📡 API REST

### Endpoints de Usuário
- `POST /usuarios` - Criar novo usuário
- `PUT /usuarios/{id}` - Atualizar usuário existente
- `GET /usuarios/{cpf}` - Buscar usuário por CPF
- `GET /usuarios` - Listar todos os usuários ativos
- `DELETE /usuarios/{id}` - Desativar usuário
- `PATCH /usuarios/{id}/ativar` - Reativar usuário

## 📄 Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para detalhes.

---

Desenvolvido por Kauesamartino.
