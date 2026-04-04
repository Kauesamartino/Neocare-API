# Agente: API Contract

Responsável pelo design e integridade dos contratos da API REST do projeto Neocare API.
Consultar **antes de expor qualquer novo endpoint REST ou alterar um contrato existente**.

---

## Regras de Design REST

### Nomenclatura de Rotas
- [ ] Recursos no plural e em português do domínio (`/usuarios`, `/medicoes`, `/alertas`)
- [ ] Sem verbos na URL — o verbo HTTP já expressa a ação (`GET /usuarios`, não `GET /buscarUsuarios`)
- [ ] Sub-recursos aninhados quando há relação pai-filho (`/usuarios/{cpf}/medicoes`)
- [ ] Identificadores de recurso no path para operações sobre um item específico (`/usuarios/{cpf}`)

### HTTP Methods & Status Codes

| Operação | Método | Status de Sucesso | Erros comuns |
|---|---|---|---|
| Criar recurso | POST | 201 Created | 400, 409 |
| Buscar lista | GET | 200 OK | 403 |
| Buscar por ID | GET | 200 OK | 404 |
| Atualizar total | PUT | 200 OK | 400, 404 |
| Atualizar parcial | PATCH | 200 OK | 400, 404 |
| Remover | DELETE | 204 No Content | 404 |
| Autenticação | POST | 200 OK | 401 |

- [ ] Nunca retornar 200 para erros de negócio
- [ ] 404 somente quando o recurso buscado não existe (não para lista vazia — lista vazia é 200 `[]`)
- [ ] 403 quando o usuário está autenticado mas não tem permissão (não 401)
- [ ] 401 somente quando não autenticado

---

## DTOs de Contrato

### Request DTOs
- [ ] Anotações Bean Validation em todos os campos obrigatórios
- [ ] Usar `record` para payloads imutáveis de entrada
- [ ] Nomenclatura: `{Acao}{Recurso}Request` (ex: `RegistrarMedicaoVitalRequest`)

```java
public record RegistrarMedicaoVitalRequest(
    @NotNull(message = "Pressão sistólica é obrigatória")
    @Min(value = 60) @Max(value = 250)
    Integer pressaoSistolica,

    @NotNull(message = "Pressão diastólica é obrigatória")
    @Min(value = 40) @Max(value = 150)
    Integer pressaoDiastolica,

    @NotNull(message = "Frequência cardíaca é obrigatória")
    @Min(value = 30) @Max(value = 220)
    Integer frequenciaCardiaca
) {}
```

### Response DTOs
- [ ] Usar `record` para respostas imutáveis
- [ ] Nunca expor campos de senha, tokens ou dados internos de infraestrutura
- [ ] Nomenclatura: `{Recurso}Response` (ex: `UsuarioResponse`, `MedicaoVitalResponse`)
- [ ] Não expor IDs internos de banco quando há identificador de domínio (ex: CPF)

### Anti-padrões de DTO

| Anti-Padrão | Problema | Penalidade |
|---|---|---|
| Retornar Entity JPA diretamente na resposta | Acopla infraestrutura ao contrato | -10 pts |
| Campo `senha` ou `password` em qualquer response | Vazamento de dado sensível (OWASP) | -10 pts |
| DTO genérico reutilizado para request e response | Viola SRP, oculta contrato | -5 pts |
| Ignorar Bean Validation com `@Valid` ausente no controller | Validação nunca executada | -5 pts |

---

## Validação no Controller

```java
@PostMapping("/medicoes/vital")
public ResponseEntity<MedicaoVitalResponse> registrar(
        @Valid @RequestBody RegistrarMedicaoVitalRequest request) {
    // ...
}
```

- [ ] `@Valid` presente antes de todo `@RequestBody` e `@ModelAttribute`
- [ ] `BindingResult` não usado em REST — Spring lança `MethodArgumentNotValidException` automaticamente
- [ ] `GlobalExceptionHandler` captura `MethodArgumentNotValidException` e retorna 400 com detalhes dos campos

---

## GlobalExceptionHandler (REST)

Estrutura obrigatória de resposta de erro:

```java
// Resposta padronizada de erro
public record ErroResponse(
    int status,
    String mensagem,
    List<String> detalhes,
    LocalDateTime timestamp
) {}
```

- [ ] `@RestControllerAdvice` para capturar exceções globais
- [ ] `MethodArgumentNotValidException` → 400 com lista de erros de campo
- [ ] `ValidacaoDominioException` → 422 Unprocessable Entity
- [ ] `EntityNotFoundException` / recurso não encontrado → 404
- [ ] `AccessDeniedException` → 403
- [ ] `Exception` genérica → 500 com mensagem genérica (sem stacktrace)
- [ ] **Nunca** expor stacktrace ou detalhes de implementação na resposta

---

## Documentação OpenAPI (SpringDoc)

- [ ] `@Operation(summary = "...")` em cada endpoint
- [ ] `@Parameter(description = "...")` em path variables e query params
- [ ] `@ApiResponse` para cada código de status possível
- [ ] `@Tag(name = "...")` em cada Controller para agrupar no Swagger UI
- [ ] Schema de request e response gerado automaticamente pelos records

---

## Spring Security — Proteção de Rotas API

Toda rota REST deve ter uma regra explícita em `SecurityConfig`:

```java
.requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("USER", "ADMIN")
.requestMatchers(HttpMethod.DELETE, "/usuarios/{cpf}").hasRole("ADMIN")
.requestMatchers(HttpMethod.POST, "/medicoes/**").hasAnyRole("USER", "ADMIN")
```

- [ ] Nenhuma rota de negócio em `.anyRequest().permitAll()` acidentalmente
- [ ] Rotas de autenticação (`/api/auth/**`) explicitamente permitidas
- [ ] Rotas Swagger explicitamente permitidas apenas em perfil de desenvolvimento

---

## Checklist Pré-Entrega de Endpoint

- [ ] Rota segue padrão REST (verbo correto, recurso no plural)
- [ ] `@Valid` presente no parâmetro de request
- [ ] Status HTTP correto para sucesso e cada caso de erro
- [ ] Response não expõe dados de infraestrutura ou senha
- [ ] Rota protegida pela role adequada em `SecurityConfig`
- [ ] Documentada com `@Operation` e `@ApiResponse`
- [ ] `GlobalExceptionHandler` trata todos os erros possíveis deste endpoint
