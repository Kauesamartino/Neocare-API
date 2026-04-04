# Agente: Java Best Practices

Responsável pelas regras fundamentais de qualidade de código Java do projeto Neocare API.
Aplicar **em toda tarefa sem exceção** — violações geram penalidades diretas na avaliação.

---

## Checklist de Revisão Obrigatória

Após gerar ou modificar qualquer código Java, verificar todos os itens:

### Java 17+
- [ ] Usar `record` para DTOs de resposta e objetos de valor imutáveis
- [ ] Usar `sealed classes` quando modelar hierarquias fechadas de domínio
- [ ] Usar `switch expressions` (arrow syntax) em lugar de `switch statements` com `break`
- [ ] Usar `var` quando o tipo é óbvio pelo lado direito da atribuição
- [ ] `instanceof` pattern matching: `if (obj instanceof MinhaClasse mc) { ... }`

### Tipagem e Nulabilidade
- [ ] Nunca retornar `null` diretamente; usar `Optional<T>` em repositórios e use cases
- [ ] Nunca receber parâmetro `null` sem validação; usar `Objects.requireNonNull` ou Bean Validation
- [ ] Tipos primitivos em domínio → preferir wrappers somente quando nulidade tem significado de negócio

### Nomenclatura (Clean Code)
- [ ] Classes: `PascalCase`, substantivo que descreve responsabilidade (`RegistrarMedicaoUseCase`, não `MedicaoService2`)
- [ ] Métodos: `camelCase`, verbo + objeto (`buscarPorCpf`, `registrarMedicaoVital`)
- [ ] Constantes: `UPPER_SNAKE_CASE`
- [ ] Pacotes: `lowercase`, sem underscores
- [ ] Variáveis: nomes que revelam intenção; nunca `x`, `temp`, `data`, `obj`

### Métodos e Funções
- [ ] Máximo **20 linhas** por método; extrair métodos privados se ultrapassar
- [ ] Máximo **3 parâmetros** por método; agrupar em objeto/record se ultrapassar
- [ ] Um método faz **uma única coisa** (SRP no nível de método)
- [ ] Sem flags booleanas como parâmetro (ex: `processar(true)` → criar dois métodos)
- [ ] Sem números mágicos; usar constantes nomeadas

### Organização de Classes
- [ ] Máximo **200 linhas** por classe; se ultrapassar, revisar responsabilidades
- [ ] Ordem de membros: constantes → campos → construtores → métodos públicos → métodos privados
- [ ] Nunca misturar lógica de negócio com lógica de infraestrutura dentro de uma mesma classe

### Tratamento de Exceções
- [ ] Nunca `catch (Exception e)` genérico sem relançar como domínio/aplicação
- [ ] Nunca engolir exceções (`catch (Exception e) {}`)
- [ ] Usar exceções de domínio (`ValidacaoDominioException`) para regras de negócio
- [ ] Logar exceções com SLF4J nível appropriado (`log.error`, `log.warn`)
- [ ] Sem `e.printStackTrace()` no código

### Optional e Streams
- [ ] `Optional` somente em retorno de métodos — nunca como parâmetro ou campo
- [ ] Streams: sem efeitos colaterais dentro de `map`/`filter`; usar `forEach` somente para saída
- [ ] Preferir `findFirst()` + `orElseThrow()` com mensagem clara

### Injeção de Dependência
- [ ] **Obrigatório:** injeção via construtor
- [ ] **Proibido:** `@Autowired` em campo
- [ ] **Proibido:** `@Autowired` em setter (sem razão explícita)
- [ ] Marcar dependências como `final` quando injetadas via construtor

### Comentários
- [ ] **Zero comentários para explicar código ruim** (refatorar ao invés de comentar)
- [ ] Comentários permitidos: Javadoc em interfaces públicas e justificativas de decisões não óbvias
- [ ] TODO/FIXME são temporários; não podem entrar em um fluxo finalizado

---

## Anti-Padrões Proibidos

| Anti-Padrão | Alternativa |
|---|---|
| `System.out.println` | `log.info / log.debug` via SLF4J |
| `@Autowired` em campo | Injeção via construtor |
| Retornar `null` | Retornar `Optional` ou lançar exceção |
| `catch (Exception e) {}` | Re-lançar ou logar |
| Classe utilitária com estado | Converter em serviço injetável |
| Herança para reutilização de comportamento | Composição |
| `instanceof` sem pattern matching (Java 16+) | `instanceof MinhaClasse obj` |
| Strings mágicas duplicadas | Constante ou enum |

---

## Organização de Pacotes (Clean Architecture)

```
domain/
  model/          ← Entidades de domínio puras (sem anotações JPA)
  repository/     ← Interfaces dos repositórios (contratos)
  exception/      ← Exceções de domínio
  enums/          ← Enums do domínio

application/
  usecase/        ← Use Cases por agregado (ex: usuario/, medicao/)
  exception/      ← Exceções de aplicação

infrastructure/
  entity/         ← Entidades JPA (mapeamento banco)
  repository/     ← Implementações JPA dos repositórios de domínio
  security/       ← JWT, UserDetails, Filters
  config/         ← Beans de configuração Spring
  services/       ← Serviços de infraestrutura (e-mail, etc.)

interfaces/
  controller/     ← REST Controllers
  web/            ← MVC Controllers (Thymeleaf)
  dto/            ← DTOs de entrada e saída
  mapper/         ← Mapeamentos domain ↔ DTO ↔ Entity
  handler/        ← GlobalExceptionHandler REST
```

**Regra de dependência:** as setas apontam sempre para dentro.
`interfaces → application → domain ← infrastructure`
Nunca domain ou application deve importar de infrastructure ou interfaces.
