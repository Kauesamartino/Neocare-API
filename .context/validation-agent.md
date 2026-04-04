# Agente: Validação Pós-Implementação

Responsável por verificar a conformidade de cada fluxo implementado com os critérios de avaliação do challenge.
Executar **obrigatoriamente após cada fluxo concluído** — nenhuma entrega é válida sem passar por este agente.

---

## Por Que Este Agente Existe

Cada ocorrência não detectada representa penalidade real na avaliação:

| Violação | Penalidade |
|---|---|
| Violação evidente de SOLID (método gigante, responsabilidade múltipla) | -10 pts/ocorrência |
| Código com repetições desnecessárias (DRY) | -5 pts/ocorrência |
| Código com problemas de legibilidade (Clean Code) | -5 pts/ocorrência |
| Comentário no lugar de refatoração clara | -3 pts/ocorrência |
| Funcionalidade com comportamento inesperado ou erro evidente | -5 pts/funcionalidade |
| Página que não carrega ou link quebrado | -5 pts/página |
| Apresentação incompleta ou incoerente com a proposta | -15 pts |

---

## Checklist 1 — Arquitetura & SOLID

- [ ] Nenhuma classe viola SRP (mais de uma razão para mudar)?
- [ ] Controllers não contêm lógica de negócio (delegam ao Use Case)?
- [ ] Use Cases não importam Entity JPA nem fazem persistência direta?
- [ ] Domain model sem anotações JPA ou Spring?
- [ ] Interfaces de repositório em `domain/repository/` e implementações em `infrastructure/repository/`?
- [ ] Toda dependência injetada via construtor (sem `@Autowired` em campo)?
- [ ] Nenhum `new ConcreteClass()` dentro de classes de negócio (use cases, domain)?

---

## Checklist 2 — Clean Code & DRY

- [ ] Nenhum método ultrapassa 20 linhas?
- [ ] Nenhuma classe ultrapassa 200 linhas?
- [ ] Nenhuma string ou número mágico duplicado (extraídos em constante)?
- [ ] Nenhum bloco de código duplicado entre classes (extrair método ou serviço compartilhado)?
- [ ] Fragmentos Thymeleaf reutilizáveis em `layout/` — sem HTML duplicado entre templates?
- [ ] Nomes de variáveis, métodos e classes revelam intenção sem necessidade de comentários?
- [ ] Zero `System.out.println` — logs via SLF4J?
- [ ] Zero comentários explicando código ruim — refatorar ao invés de comentar?

---

## Checklist 3 — Frontend (Thymeleaf) — 30 pts

- [ ] Todas as páginas carregam sem erro 500 ou 404?
- [ ] Todos os links da navbar e do sistema testados?
- [ ] Formulários disparam validações Bean Validation corretamente?
- [ ] Mensagens de erro exibidas ao usuário (field errors + global errors)?
- [ ] Mensagens de sucesso após ação bem-sucedida (flash attributes)?
- [ ] CSRF token presente em todos os formulários POST?
- [ ] Nenhum link hardcoded — todos usam `th:href="@{...}"`?
- [ ] Nenhum `action` hardcoded — todos usam `th:action="@{...}"`?
- [ ] Páginas de erro 403 e 404 configuradas e exibidas corretamente?
- [ ] Navbar exibe opções diferentes para `ROLE_USER` e `ROLE_ADMIN`?

---

## Checklist 4 — Flyway — 20 pts

- [ ] Toda alteração de schema está em uma nova migration `V{n}__descricao.sql`?
- [ ] Nenhuma migration existente foi modificada (imutabilidade)?
- [ ] Migrations executam sem erro em banco limpo?
- [ ] Nomenclatura do arquivo segue o padrão `V{numero}__nome_descritivo.sql`?
- [ ] Dados de seed (insert) em migrations separadas das de schema (create/alter)?
- [ ] `spring.jpa.hibernate.ddl-auto=none` — Flyway é o único responsável pelo schema?

---

## Checklist 5 — Spring Security — 30 pts

- [ ] Pelo menos dois perfis de usuário implementados (`ROLE_USER` e `ROLE_ADMIN`)?
- [ ] Rotas protegidas por role (ex: `/admin/**` bloqueado para `ROLE_USER`)?
- [ ] Rotas públicas acessíveis sem autenticação (login, CSS, JS)?
- [ ] Acesso negado redireciona para página 403 (não stacktrace)?
- [ ] Form Login configurado para as views Thymeleaf?
- [ ] JWT configurado para a API REST (`/api/**`)?
- [ ] Logout invalida sessão e redireciona para login?
- [ ] Senhas armazenadas com BCrypt — nunca em plain text?

---

## Checklist 6 — Funcionalidades Completas — 20 pts

- [ ] Pelo menos dois fluxos completos implementados (não simples CRUD)?
- [ ] Validações de formulário funcionando (campo obrigatório, formato, range)?
- [ ] Fluxo funciona end-to-end: formulário → use case → persistência → feedback ao usuário?
- [ ] Comportamento de erro tratado e exibido (ex: CPF já cadastrado, medição inválida)?
- [ ] Nenhum endpoint ou rota retorna stacktrace ao usuário final?

---

## Checklist 7 — Coerência com a Proposta Neocare

- [ ] As funcionalidades implementadas fazem sentido no contexto de saúde/monitoramento?
- [ ] Terminologia nas views e mensagens é consistente com o domínio Neocare?
- [ ] Fluxos cobrem casos reais do sistema (ex: registrar medição vital, visualizar alertas)?
- [ ] Nenhuma tela ou rota está vazia, com dados mockados hardcoded ou sem funcionalidade?

---

## Resultado da Validação

Ao final do checklist, reportar:

```
### Validação do Fluxo: [Nome do Fluxo]

Conformidades: X/Y itens

Problemas encontrados:
- [ ] [descrição do problema] — Penalidade estimada: -X pts
- [ ] ...

Ação necessária antes de prosseguir:
- Corrigir: [lista de itens bloqueantes]
```

**Nenhum fluxo avança para o próximo sem todos os itens bloqueantes resolvidos.**
