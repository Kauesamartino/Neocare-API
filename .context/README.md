# Agent Playbooks — Neocare

Playbooks para os agentes de IA que operam neste workspace. Cada agente tem responsabilidades exclusivas e regras que garantem a integridade da arquitetura, qualidade do código e conformidade com os critérios de avaliação.

> **Regra de ouro:** Toda implementação passa obrigatoriamente pelo agente de **Validação Pós-Implementação** antes de ser considerada concluída.

---

## Critérios de Avaliação (Challenge)

### Pontuação por entregas

| Requisito | Pontos |
|---|---|
| Frontend com Thymeleaf | 30 |
| Flyway (controle de versão do banco) | 20 |
| Spring Security (2+ perfis, proteção de rotas) | 30 |
| Funcionalidades completas (2+ fluxos, validações) | 20 |
| **Total** | **100** |

### Tabela de Penalidades

| Violação | Penalidade |
|---|---|
| Violação evidente de princípios SOLID (métodos gigantes, responsabilidades múltiplas) | -10 pts/ocorrência |
| Código com repetições desnecessárias (violação de DRY) | -5 pts/ocorrência |
| Código com problemas de legibilidade (violação de Clean Code) | -5 pts/ocorrência |
| Comentários no lugar de refatorações claras | -3 pts/ocorrência |
| Funcionalidade com comportamento inesperado ou erro evidente | -5 pts/funcionalidade |
| Página que não carrega ou link quebrado | -5 pts/página |
| Apresentação incompleta ou incoerente com a proposta | -15 pts |

---

## Arquitetura do Projeto (Clean Architecture)

```
interfaces/          ← Controllers MVC (Thymeleaf) + REST Controllers + DTOs + Mappers
application/         ← Use Cases (regras de aplicação, orquestração)
domain/              ← Entidades de domínio, interfaces de repositório, exceções de domínio
infrastructure/      ← JPA Entities, Repositories, Security, Services, Config
```

### Pacotes Thymeleaf (novos)

```
interfaces/
  web/               ← Controllers MVC para views Thymeleaf
  dto/               ← Reutilizar ou criar Form DTOs para binding Thymeleaf
resources/
  templates/         ← Templates HTML com Thymeleaf
    layout/          ← Fragmentos de layout (header, footer, navbar)
    auth/            ← Login, registro
    dashboard/       ← Dashboard por perfil
    usuario/         ← Telas de gerenciamento de usuário
    medicao/         ← Telas de medições
    alerta/          ← Telas de alertas
  static/
    css/             ← Estilos customizados
    js/              ← Scripts frontend
```

---

## Agentes

| Agente | Arquivo | Quando usar |
|---|---|---|
| Java Best Practices | [`java-best-practices.md`](java-best-practices.md) | **Toda tarefa** — Java 17+, SOLID, Clean Code, nomenclatura |
| SOLID & Clean Arch | [`solid-cleanarch-agent.md`](solid-cleanarch-agent.md) | Ao criar/refatorar qualquer classe ou fluxo |
| Thymeleaf & MVC | [`thymeleaf-agent.md`](thymeleaf-agent.md) | Ao criar ou modificar qualquer view, controller MVC ou formulário |
| API Contract | [`api-contract-agent.md`](api-contract-agent.md) | Antes de expor novos endpoints REST |
| Persistence & Data | [`persistence-agent.md`](persistence-agent.md) | Ao criar entidades, migrations Flyway ou queries |
| Validação Pós-Implementação | [`validation-agent.md`](validation-agent.md) | **Obrigatório após cada fluxo implementado** |

---

## Fluxo de Implementação Obrigatório

```
1. [SOLID & Clean Arch] → Desenhar a solução respeitando camadas
2. [Agente especializado] → Implementar (Thymeleaf / API / Persistence)
3. [Java Best Practices] → Revisar código gerado
4. [Validação Pós-Implementação] → Validar contra penalidades e requisitos
```

---

## Stack

| Preocupação | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.x |
| Frontend (Web) | Thymeleaf + Spring MVC |
| Persistência | Spring Data JPA / Hibernate |
| Banco de Dados | PostgreSQL |
| Migrations | Flyway |
| Segurança | Spring Security (JWT para API, Form Login para Web) |
| Build | Maven |
| Documentação API | SpringDoc OpenAPI |

---

## Perfis de Usuário (Spring Security)

| Role | Acesso Web | Acesso API |
|---|---|---|
| `ROLE_USER` | Dashboard pessoal, visualizar suas medições e alertas | GET/PUT próprios dados |
| `ROLE_ADMIN` | Tudo + gerenciamento de usuários, listagem global | Todos os endpoints |

---

## Regras Globais

- **Injeção de Dependência:** sempre via construtor; nunca `@Autowired` em campo.
- **DTOs imutáveis:** usar `record` para DTOs de resposta REST; usar classes com validações para form DTOs Thymeleaf.
- **Fail Fast:** validar entradas no nível de controller/DTO com Bean Validation (`@Valid`).
- **Sem StackTrace na resposta:** usar `GlobalExceptionHandler` para REST; usar `model.addAttribute("error", ...)` para views.
- **Logs:** SLF4J com nível adequado; nunca `System.out.println`.
- **DRY:** extrair fragmentos Thymeleaf reutilizáveis; extrair métodos privados quando lógica se repete.
- **Dúvida técnica:** PARAR e reportar ao usuário antes de prosseguir.