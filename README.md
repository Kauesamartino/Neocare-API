# Neocare API — Sistema de Monitoramento de Estresse

Neocare é uma plataforma completa de monitoramento de estresse e sinais vitais, desenvolvida com **Java 17**, **Spring Boot 3.5.6**, **Thymeleaf**, **Spring Security** (JWT + sessão) e **PostgreSQL**. O projeto segue os princípios da **Clean Architecture** com separação rigorosa de camadas.

## Para SP De DevOps e Cloud Computing
Acessar o arquivo [passo-a-passo-cloud.md](passo-a-passo-cloud.md) com as instruções

## 📋 Funcionalidades

### Gestão de Usuários
- Cadastro completo com dados pessoais, endereço e credenciais
- Edição de dados cadastrais
- Consulta por CPF ou username
- Listagem de usuários ativos
- Desativação de usuários (soft delete)ㅤ

### Medições de Estresse
- Registro de medições com **HRV** (variação de frequência cardíaca) e **GSR** (condutividade da pele)
- Geração automática de alertas com severidade (Alta, Média, Baixa) baseada nos valores detectados

### Medições de Sinais Vitais
- Registro de **BPM** (batimentos por minuto), **SpO2** (oxigenação), pressão arterial sistólica e diastólica

### Alertas
- Criação automática de alertas ao registrar medições de estresse fora dos limites
- Classificação por tipo e severidade
- Visualização por usuário ou global (admin)

### Dashboards (Thymeleaf)
- **Dashboard do Usuário**: medições pessoais de estresse, sinais vitais e alertas
- **Dashboard do Admin**: visão global de usuários cadastrados e alertas recentes

### Autenticação & Autorização
- Login via formulário (Thymeleaf, sessão) e via API REST (JWT Bearer token)
- Dois perfis: `ROLE_USER` (acesso ao próprio dashboard e dados) e `ROLE_ADMIN` (acesso total + gerenciamento de usuários)
- Senhas criptografadas com BCrypt
- Token JWT com expiração de 24 horas

### Validações de Domínio
- CPF (formato e dígitos verificadores)
- E-mail, telefone, CEP, UF
- Idade mínima, peso e altura

## 🏗️ Arquitetura (Clean Architecture)

```
interfaces/       ← Controllers MVC/REST, DTOs, Mappers, GlobalExceptionHandler
application/      ← Use Cases (orquestração e regras de aplicação)
domain/           ← Modelos de domínio puros, interfaces de repositório, exceções, enums
infrastructure/   ← JPA Entities, Repository Adapters, Security (JWT), Config, Services
```

**Regra de dependência:** `interfaces → application → domain ← infrastructure`
O domínio nunca importa de infraestrutura ou interfaces.

### Modelos de Domínio

| Modelo | Descrição |
|--------|-----------|
| `Usuario` | Dados pessoais, endereço embarcado, credenciais |
| `Credenciais` | Username, password (BCrypt), roles — implementa `UserDetails` |
| `Role` | `ROLE_ADMIN`, `ROLE_USER` (many-to-many com Credenciais) |
| `Endereco` | Value Object embarcado no Usuário |
| `Dispositivo` | Tipo de dispositivo vinculado a um usuário |
| `Medicao` | Base para medições (estresse e vital) |
| `MedicaoEstresse` | HRV (ms) e GSR (μS) |
| `MedicaoVital` | BPM, SpO2 (%), pressão sistólica/diastólica |
| `Alerta` | Tipo, severidade, valor detectado, mensagem |

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|------------|-----------|
| **Java 17** | Linguagem |
| **Spring Boot 3.5.6** | Framework principal |
| **Spring Security** | Autenticação (formulário + JWT) e autorização |
| **Thymeleaf** | Templates HTML para frontend web |
| **Spring Data JPA / Hibernate** | Persistência de dados |
| **PostgreSQL 17** | Banco de dados relacional |
| **Flyway** | Migração e versionamento do schema (8 migrations) |
| **JJWT 0.12.5** | Geração e validação de tokens JWT |
| **SpringDoc OpenAPI 2.7.0** | Documentação automática da API (Swagger) |
| **Maven** | Gerenciamento de dependências e build |
| **Docker** | Containerização (multi-stage build) |
| **JUnit + Spring Security Test** | Testes automatizados |

## 🚀 Executando a Aplicação

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.8.1 ou superior
- Docker (recomendado) ou PostgreSQL 17 instalado localmente

### Opção 1 — Docker Compose (recomendado)

```shell
cd src/main/docker
docker-compose up -d
```

Isso sobe automaticamente o PostgreSQL e a API na porta `8080`.

### Opção 2 — Execução local

1. Crie um banco PostgreSQL chamado `neocare`
2. Configure as credenciais em `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/neocare
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

3. Execute a aplicação:

```shell
./mvnw spring-boot:run
```

> A documentação da API estará disponível em http://localhost:8080/swagger-ui.html

### Empacotando a Aplicação

```shell
./mvnw package
java -jar target/Neocare-API-0.0.1-SNAPSHOT.jar
```

## 📡 API REST

### Autenticação

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| `POST` | `/api/auth/login` | Público | Login — retorna JWT token |

### Usuários

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| `POST` | `/usuarios` | Público | Cadastrar novo usuário |
| `GET` | `/usuarios` | USER / ADMIN | Listar todos os usuários ativos |
| `GET` | `/usuarios/{cpf}` | USER / ADMIN | Buscar usuário por CPF |
| `GET` | `/usuarios/username/{username}` | USER / ADMIN | Buscar usuário por username |
| `PUT` | `/usuarios` | USER / ADMIN | Atualizar dados do usuário |
| `DELETE` | `/usuarios/{cpf}` | ADMIN | Desativar usuário |

### Medições

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| `POST` | `/medicoes/medicao_estresse` | Público | Registrar medição de estresse (HRV + GSR) |
| `POST` | `/medicoes/medicao_vital` | Público | Registrar medição vital (BPM, SpO2, PA) |

### Formato de Erro Padrão

```json
{
  "statusCode": 400,
  "message": "Mensagem de erro",
  "timestamp": "2026-04-12T10:30:00",
  "path": "/usuarios"
}
```

## 🌐 Frontend Web (Thymeleaf)

| Rota | Acesso | Descrição |
|------|--------|-----------|
| `/auth/login` | Público | Página de login |
| `/auth/registro` | Público | Formulário de cadastro completo (dados pessoais, endereço, credenciais) |
| `/dashboard` | Autenticado | Dashboard do usuário (medições e alertas pessoais) ou admin (visão global) |
| `/` | Autenticado | Redireciona para `/dashboard` |

### Páginas de Erro
- `403` — Acesso negado
- `404` — Página não encontrada

## 🔒 Segurança

O sistema possui duas cadeias de filtro de segurança:

1. **Web (sessão)**: Form login para páginas Thymeleaf — cria sessão no servidor
2. **API (JWT stateless)**: Token Bearer para endpoints REST — sem estado no servidor

### CORS

Origens permitidas: `localhost`, `*.vercel.app`, `*.render.com`

## 🗄️ Migrações Flyway

| Versão | Descrição |
|--------|-----------|
| V1 | Criação das tabelas `credenciais`, `role` e junction `credenciais_role` |
| V2 | Criação da tabela `usuario` com endereço embarcado |
| V3 | Criação da tabela `dispositivo` |
| V4 | Criação da tabela `medicoes` (base) |
| V5 | Criação da tabela `medicoes_estresse` (HRV, GSR) |
| V6 | Criação da tabela `medicoes_vitais` (BPM, SpO2, PA) |
| V7 | Criação da tabela `alertas` |
| V8 | Seed de dispositivos padrão |

## 📁 Estrutura de Pastas

```
src/main/java/com/neocare/api/
├── domain/                    # Modelos puros, enums, exceções, contratos de repositório
│   ├── enums/
│   ├── exception/
│   ├── logging/
│   ├── model/
│   └── repository/
├── application/               # Use Cases
│   ├── exception/
│   └── usecase/
│       ├── alerta/
│       ├── dispositivo/
│       ├── medicao/
│       └── usuario/
├── infrastructure/            # Implementações técnicas
│   ├── api/rest/              # REST Adapters (Spring-specific)
│   ├── config/                # SecurityConfig, SpringDocConfig, Beans
│   ├── entity/                # JPA Entities
│   ├── logging/
│   ├── persistance/           # Repository Adapters (JPA → Domain)
│   ├── repository/            # Spring Data JPA Repositories
│   ├── security/              # JWT (JwtUtil, JwtAuthenticationFilter)
│   └── services/              # CustomUserDetailsService
└── interfaces/                # Contratos e DTOs
    ├── controller/            # Controllers puros (framework-agnostic)
    ├── dto/
    │   ├── input/
    │   └── output/
    ├── handler/               # GlobalExceptionHandler
    ├── mapper/
    └── web/                   # Thymeleaf Web Controllers
```

## 📄 Licença

Este projeto está licenciado sob a licença MIT — veja o arquivo LICENSE para detalhes.

---

Desenvolvido por Kauesamartino.
