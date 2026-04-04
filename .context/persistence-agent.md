# Agente: Persistence & Data

Responsável pelo fluxo de persistência do projeto Neocare API.
Consultar **ao criar entidades JPA, migrations Flyway, queries ou repositórios**.

---

## Flyway — Regras de Migration

### Nomenclatura

```
V{numero}__{descricao_com_underscores}.sql
```

Exemplos:
- `V9__create-table-consulta.sql`
- `V10__add-column-status-to-alerta.sql`
- `V11__insert-roles-default.sql`

- [ ] Número sequencial sem lacunas (verificar última migration antes de criar)
- [ ] Descrição em kebab-case, descritiva o suficiente para entender sem abrir o arquivo
- [ ] Migrations de schema (`CREATE`, `ALTER`, `DROP`) separadas de migrations de dados (`INSERT`, `UPDATE`)
- [ ] **Nunca modificar** uma migration já executada — criar nova migration para correções
- [ ] `spring.jpa.hibernate.ddl-auto=none` — Flyway é o único dono do schema

### Estrutura de uma Migration de Schema

```sql
-- V9__create-table-consulta.sql

CREATE TABLE IF NOT EXISTS consulta (
    id          BIGSERIAL       PRIMARY KEY,
    usuario_cpf VARCHAR(11)     NOT NULL,
    dispositivo_id BIGINT       NOT NULL,
    data_hora   TIMESTAMP       NOT NULL DEFAULT NOW(),
    observacao  TEXT,
    ativo       BOOLEAN         NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_consulta_usuario    FOREIGN KEY (usuario_cpf) REFERENCES usuario(cpf),
    CONSTRAINT fk_consulta_dispositivo FOREIGN KEY (dispositivo_id) REFERENCES dispositivo(id)
);

CREATE INDEX IF NOT EXISTS idx_consulta_usuario_cpf ON consulta(usuario_cpf);
CREATE INDEX IF NOT EXISTS idx_consulta_data_hora   ON consulta(data_hora);
```

- [ ] `IF NOT EXISTS` em todo `CREATE TABLE` e `CREATE INDEX`
- [ ] `CONSTRAINT` nomeadas explicitamente para FKs
- [ ] Índices criados para colunas de busca frequente (FK, status, data)
- [ ] Tipos de coluna adequados ao dado (não `VARCHAR(255)` para tudo)

---

## Entidades JPA — Regras

### Separação obrigatória: Entity ≠ Domain Model

```
infrastructure/entity/ConsultaEntity.java   ← JPA, anotações, mapeamento
domain/model/Consulta.java                  ← Domínio puro, regras de negócio
```

**Nunca misturar anotações JPA com lógica de domínio.**

### Estrutura padrão de Entity JPA

```java
@Entity
@Table(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_cpf", nullable = false, length = 11)
    private String usuarioCpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispositivo_id", nullable = false)
    private DispositivoEntity dispositivo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @Column(nullable = false)
    private Boolean ativo;

    // Construtor padrão obrigatório para JPA
    protected ConsultaEntity() {}

    // Construtor completo para uso nos mappers
    public ConsultaEntity(String usuarioCpf, DispositivoEntity dispositivo,
                          LocalDateTime dataHora, String observacao) {
        this.usuarioCpf = usuarioCpf;
        this.dispositivo = dispositivo;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.ativo = true;
    }

    // Getters — sem setters públicos desnecessários
}
```

- [ ] `fetch = FetchType.LAZY` em todos os relacionamentos `@ManyToOne` e `@OneToMany`
- [ ] Construtor protegido sem argumentos para JPA
- [ ] Sem lógica de negócio na Entity JPA
- [ ] `@Column` com `nullable`, `length` e `name` explícitos para colunas críticas
- [ ] `@GeneratedValue(strategy = GenerationType.IDENTITY)` — nunca `AUTO` com PostgreSQL

### Anti-Padrões de Entidade

| Anti-Padrão | Problema | Penalidade |
|---|---|---|
| `fetch = FetchType.EAGER` em coleções | Causa N+1, degradação de performance | -5 pts |
| Lógica de negócio dentro da Entity JPA | Viola SRP + Clean Arch | -10 pts |
| Entity JPA usada como parâmetro/retorno de Use Case | Viola regra de dependência | -10 pts |
| `@GeneratedValue(strategy = AUTO)` com PostgreSQL | Incompatibilidade de sequência | -5 pts |
| Entity sem construtor protegido | JPA não consegue instanciar via proxy | erro em runtime |

---

## Repositories — Regras

### Interface de Domínio (domain/repository/)

```java
public interface ConsultaRepository {
    Consulta salvar(Consulta consulta);
    Optional<Consulta> buscarPorId(Long id);
    List<Consulta> listarPorUsuario(String cpf);
    void desativar(Long id);
}
```

- [ ] Nomes dos métodos em português do domínio (não `findBy...`, `save`, `deleteById`)
- [ ] Parâmetros e retornos são objetos de domínio ou primitivos — nunca Entity JPA
- [ ] `Optional<T>` para buscas que podem não encontrar resultado

### Implementação JPA (infrastructure/repository/)

```java
@Repository
public class ConsultaRepositoryImpl implements ConsultaRepository {

    private final ConsultaJpaRepository jpaRepository;
    private final ConsultaMapper mapper;

    public ConsultaRepositoryImpl(ConsultaJpaRepository jpaRepository, ConsultaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Consulta salvar(Consulta consulta) {
        var entity = mapper.toEntity(consulta);
        var salva = jpaRepository.save(entity);
        return mapper.toDomain(salva);
    }

    @Override
    public Optional<Consulta> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
```

- [ ] Implementa a interface de `domain/repository/`
- [ ] Usa o Mapper para converter Entity ↔ Domain antes de retornar
- [ ] Nunca retorna `ConsultaEntity` — sempre converte para `Consulta` (domínio)
- [ ] `ConsultaJpaRepository extends JpaRepository<ConsultaEntity, Long>` separado da implementação

---

## Queries Customizadas

- [ ] Queries JPQL com `@Query` preferidas sobre `findBy...` para lógica além do básico
- [ ] Usar `Pageable` para listas que podem crescer (evitar `findAll()` sem limites)
- [ ] Projeções (interfaces ou records) para queries que retornam subconjuntos de colunas
- [ ] Nunca `SELECT *` em queries críticas — selecionar apenas as colunas necessárias

```java
// Evitar N+1: buscar com JOIN FETCH quando necessário
@Query("SELECT c FROM ConsultaEntity c JOIN FETCH c.dispositivo WHERE c.usuarioCpf = :cpf")
List<ConsultaEntity> findByUsuarioCpfWithDispositivo(@Param("cpf") String cpf);
```

---

## Checklist Pré-Entrega de Persistência

- [ ] Migration criada com número sequencial correto e nome descritivo?
- [ ] Migration executou em banco limpo sem erro?
- [ ] Entity JPA separada do Domain Model?
- [ ] `FetchType.LAZY` em todos os relacionamentos?
- [ ] Interface de repositório em `domain/repository/` com nomes de domínio?
- [ ] Implementação JPA usa Mapper para converter Entity ↔ Domain?
- [ ] Nenhuma Entity JPA retornada por Use Case ou Controller?
- [ ] Índices criados para colunas de busca frequente?
