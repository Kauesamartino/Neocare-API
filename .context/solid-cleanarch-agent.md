# Agente: SOLID & Clean Architecture

Responsável pela integridade arquitetural do projeto Neocare API.
Deve ser consultado **ao criar ou refatorar qualquer classe, serviço ou fluxo**.

---

## Princípios SOLID — Checklist

### S — Single Responsibility Principle
- [ ] Esta classe tem **uma única razão para mudar**?
- [ ] O nome da classe descreve sua única responsabilidade?
- [ ] Se a classe tem mais de uma "seção" lógica, extrair classes filhas

**Violações comuns que geram -10 pts:**
- Controller com lógica de negócio
- Use Case fazendo persistência diretamente
- Entity JPA com regras de validação de domínio

### O — Open/Closed Principle
- [ ] Para adicionar comportamento, estendo (interface/herança) ou configuro, sem modificar a classe existente?
- [ ] Uso de Strategy ou Template Method para comportamentos variáveis?

### L — Liskov Substitution Principle
- [ ] Qualquer implementação de uma interface pode substituir a interface sem quebrar o contrato?
- [ ] Subclasses não enfraquecem pré-condições nem fortalecem pós-condições?

### I — Interface Segregation Principle
- [ ] Interfaces são pequenas e focadas?
- [ ] Nenhum cliente implementa métodos que não usa?
- [ ] Interfaces de repositório separadas por agregado?

### D — Dependency Inversion Principle
- [ ] Classes de alto nível (use cases) dependem de **abstrações** (interfaces), não de implementações concretas?
- [ ] `domain/repository` contém somente interfaces; implementações estão em `infrastructure/repository`?
- [ ] Configuração de beans via `@Configuration` + `@Bean` (não `new` direto nas classes de negócio)?

---

## Clean Architecture — Regras de Camada

### Regra de Dependência (inviolável)
```
interfaces  →  application  →  domain
infrastructure  →  application  →  domain
```
- **domain** não importa NADA de outras camadas
- **application** importa somente domain
- **infrastructure** e **interfaces** importam application e domain
- Nunca `import com.neocare.api.infrastructure.*` dentro de `domain` ou `application`

### Verificações por camada

**domain/model/**
- [ ] Sem anotações JPA (`@Entity`, `@Column`, `@Table`)
- [ ] Sem anotações Spring (`@Component`, `@Service`, `@Repository`)
- [ ] Sem referências a `javax.persistence` ou `jakarta.persistence`
- [ ] Regras de negócio e invariantes dentro dos próprios objetos de domínio
- [ ] Validações no construtor ou setters com `ValidacaoDominioException`

**domain/repository/**
- [ ] Apenas interfaces; sem implementações
- [ ] Métodos com nomes de domínio, não de infraestrutura (`buscarPorCpf`, não `findByCpf`)

**application/usecase/**
- [ ] Um Use Case por caso de uso (classe única)
- [ ] Depende apenas de interfaces de repositório do domínio
- [ ] Sem lógica de serialização, mapeamento HTTP ou persistência JPA direta
- [ ] Recebe e retorna objetos de domínio (ou primitivos); nunca entidades JPA ou DTOs de interface

**infrastructure/**
- [ ] Entities JPA separadas dos modelos de domínio
- [ ] Repositories JPA implementam interfaces de `domain/repository/`
- [ ] SecurityConfig, JwtUtil, filtros isolados em `infrastructure/security/`
- [ ] Configurações Spring em `infrastructure/config/`

**interfaces/**
- [ ] Controllers delegam para Use Cases via método único; sem lógica de negócio
- [ ] DTOs específicos para cada contrato (request/response REST ou form Thymeleaf)
- [ ] Mappers convertem domain ↔ DTO; nenhuma conversão inline nos controllers
- [ ] GlobalExceptionHandler centraliza tratamento de erros

---

## Padrões de Design Aplicáveis

| Situação | Padrão |
|---|---|
| Criar objetos complexos de domínio | Builder ou Factory Method |
| Comportamento variável por tipo de usuário | Strategy |
| Operações com log/auditoria | Decorator |
| Notificações entre camadas | Domain Events (Observer) |
| Configuração de beans Spring | Factory Bean / `@Configuration` |

---

## Anti-Padrões Arquiteturais

| Anti-Padrão | Problema | Penalidade |
|---|---|---|
| "Fat Controller" — lógica de negócio no Controller | Viola SRP + Clean Arch | -10 pts |
| "Anemic Domain" — entidades somente com getters/setters | Viola DDD, sem invariantes | -10 pts |
| Importação de Entity JPA no Use Case | Viola regra de dependência | -10 pts |
| Módulo de domínio com `@Autowired` | Viola DIP + injeção de dependência | -10 pts |
| Use Case fazendo `entityRepository.save()` direto (sem abstração) | Viola DIP | -10 pts |
| Mapeamento DTO ↔ Entity espalhado em múltiplos controllers | Viola DRY + SRP | -5 pts |

---

## Fluxo para Novo Fluxo de Negócio

Seguir **sempre** nesta ordem:

```
1. Definir o caso de uso em application/usecase/{agregado}/
2. Definir ou verificar interface em domain/repository/ (se precisar de persistência)
3. Criar ou verificar Entity JPA em infrastructure/entity/
4. Implementar repository em infrastructure/repository/
5. Criar DTO(s) em interfaces/dto/
6. Criar Mapper em interfaces/mapper/
7. Criar Controller (REST ou MVC) em interfaces/controller/ ou interfaces/web/
8. Registrar beans em infrastructure/config/ se necessário
9. Executar Validação Pós-Implementação (validation-agent.md)
```
