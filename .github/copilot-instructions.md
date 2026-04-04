# Copilot Instructions — Neocare API

Sistema de monitoramento de estresse. Stack: Java 17, Spring Boot 3.x, Thymeleaf, Spring Security, Flyway, PostgreSQL.

---

## Fluxo Obrigatório em Toda Tarefa

```
1. [SOLID & Clean Arch]          → Definir a solução respeitando camadas
2. [Agente especializado]         → Implementar (Thymeleaf / API Contract / Persistence)
3. [Java Best Practices]          → Revisar o código gerado
4. [Validação Pós-Implementação]  → Validar contra penalidades e requisitos
```

Consultar o agente correspondente em `.context/` antes de implementar qualquer coisa.

---

## Agentes

| Agente | Arquivo | Quando usar |
|---|---|---|
| Java Best Practices | [`.context/java-best-practices.md`](../.context/java-best-practices.md) | **Toda tarefa** |
| SOLID & Clean Arch | [`.context/solid-cleanarch-agent.md`](../.context/solid-cleanarch-agent.md) | Ao criar/refatorar qualquer classe ou fluxo |
| Thymeleaf & MVC | [`.context/thymeleaf-agent.md`](../.context/thymeleaf-agent.md) | Ao criar views, controllers MVC ou formulários |
| API Contract | [`.context/api-contract-agent.md`](../.context/api-contract-agent.md) | Antes de expor ou alterar endpoints REST |
| Persistence & Data | [`.context/persistence-agent.md`](../.context/persistence-agent.md) | Ao criar entidades, migrations Flyway ou queries |
| Validação Pós-Implementação | [`.context/validation-agent.md`](../.context/validation-agent.md) | **Obrigatório após cada fluxo implementado** |

---

## Arquitetura (Clean Architecture)

```
interfaces/     ← Controllers MVC/REST, DTOs, Mappers, Handlers
application/    ← Use Cases (orquestração, regras de aplicação)
domain/         ← Modelos de domínio puros, interfaces de repositório, exceções
infrastructure/ ← JPA Entities, Repositories, Security, Config, Services
```

**Regra de dependência inviolável:**
`interfaces → application → domain ← infrastructure`
Domain nunca importa de infrastructure ou interfaces.

---

## Regras Globais

- Injeção de dependência **sempre via construtor**; `@Autowired` em campo é proibido.
- `record` para DTOs de resposta REST; classes mutáveis com getters/setters para form DTOs Thymeleaf.
- `@Valid` obrigatório em todo `@RequestBody` e `@ModelAttribute`.
- Sem `System.out.println` — usar SLF4J.
- Sem stacktrace na resposta ao usuário — `GlobalExceptionHandler` para REST; `model.addAttribute` para views.
- Sem comentários explicando código ruim — refatorar.
- Dúvida técnica sem cobertura nas regras → **PARAR e reportar ao usuário**.

---

## Perfis de Usuário

| Role | Acesso Web (Thymeleaf) | Acesso API REST |
|---|---|---|
| `ROLE_USER` | Dashboard pessoal, suas medições e alertas | GET/PUT próprios dados |
| `ROLE_ADMIN` | Tudo + gerenciamento de usuários e listagem global | Todos os endpoints |

---

## Critérios de Avaliação

| Requisito | Peso |
|---|---|
| Frontend Thymeleaf | 30 pts |
| Spring Security (2+ perfis, proteção de rotas) | 30 pts |
| Flyway | 20 pts |
| Funcionalidades completas (2+ fluxos, validações) | 20 pts |

### Penalidades

| Violação | Penalidade |
|---|---|
| Violação evidente de SOLID | -10 pts/ocorrência |
| Repetição de código (DRY) | -5 pts/ocorrência |
| Legibilidade (Clean Code) | -5 pts/ocorrência |
| Comentário no lugar de refatoração | -3 pts/ocorrência |
| Funcionalidade com erro evidente | -5 pts/funcionalidade |
| Página que não carrega / link quebrado | -5 pts/página |
| Apresentação incompleta ou incoerente | -15 pts |

---

Playbook completo: [`.context/README.md`](../.context/README.md)
