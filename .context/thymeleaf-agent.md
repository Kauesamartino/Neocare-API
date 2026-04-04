# Agente: Thymeleaf & Spring MVC

Responsável por todo o desenvolvimento da camada de visualização (frontend) com Thymeleaf.
Consultar **sempre que criar ou modificar templates HTML, controllers MVC ou formulários web**.

---

## Estrutura de Pacotes e Templates

### Controllers MVC
```
interfaces/web/                         ← Controllers MVC (retornam views, não @ResponseBody)
  AuthWebController.java                ← Login, logout
  DashboardController.java              ← Home por perfil
  UsuarioWebController.java             ← Gerenciamento de usuário
  MedicaoWebController.java             ← Fluxo de medições
  AlertaWebController.java              ← Visualização de alertas
```

### Templates Thymeleaf
```
resources/templates/
  layout/
    base.html                           ← Layout base com header/footer (th:fragment)
    navbar.html                         ← Navbar com links condicionais por role
    sidebar.html                        ← Sidebar (se aplicável)
  auth/
    login.html
    register.html (opcional)
  dashboard/
    user-dashboard.html                 ← Dashboard ROLE_USER
    admin-dashboard.html                ← Dashboard ROLE_ADMIN
  usuario/
    perfil.html
    listar.html                         ← ADMIN only
    form.html
  medicao/
    form-vital.html
    form-estresse.html
    historico.html
  alerta/
    listar.html
  error/
    403.html
    404.html
    500.html
```

---

## Regras de Controller MVC

### Estrutura padrão de um Controller MVC

```java
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final BuscarDadosUsuarioUseCase buscarDadosUsuariouseCase;

    public DashboardController(BuscarDadosUsuarioUseCase buscarDadosUsuarioUseCase) {
        this.buscarDadosUsuarioUseCase = buscarDadosUsuarioUseCase;
    }

    @GetMapping
    public String exibir(Model model, Authentication authentication) {
        var dados = buscarDadosUsuarioUseCase.executar(authentication.getName());
        model.addAttribute("usuario", dados);
        return "dashboard/user-dashboard";       // ← nome do template sem extensão
    }
}
```

**Regras obrigatórias:**
- [ ] `@Controller` (não `@RestController`) para controllers de view
- [ ] Retornar `String` com o nome do template
- [ ] Receber `Model` ou `RedirectAttributes` como parâmetro; nunca `ModelAndView` desnecessário
- [ ] Injeção via construtor (sem `@Autowired` em campo)
- [ ] Delegar ao Use Case; sem lógica de negócio inline

### Redirect após formulário (Post-Redirect-Get)
```java
@PostMapping("/medicoes/vital")
public String salvar(@Valid MedicaoVitalForm form,
                     BindingResult result,
                     Model model,
                     RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
        return "medicao/form-vital";             // ← exibir form com erros
    }
    try {
        registrarMedicaoUseCase.executar(form.toDomain());
        redirectAttributes.addFlashAttribute("sucesso", "Medição registrada com sucesso.");
        return "redirect:/medicoes";             // ← PRG pattern
    } catch (ValidacaoDominioException e) {
        model.addAttribute("erro", e.getMessage());
        return "medicao/form-vital";
    }
}
```

- [ ] Sempre usar **Post-Redirect-Get** após formulários com sucesso
- [ ] Usar `BindingResult result` imediatamente após `@Valid` no parâmetro
- [ ] Mensagens de sucesso via `RedirectAttributes.addFlashAttribute`
- [ ] Mensagens de erro de domínio via `model.addAttribute("erro", ...)`

---

## Regras de Templates Thymeleaf

### Layout base (fragmentos)
```html
<!-- layout/base.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity6">
<head>
    <meta charset="UTF-8">
    <title th:text="${titulo} ?: 'Neocare'">Neocare</title>
    <link rel="stylesheet" th:href="@{/css/style.css}">
</head>
<body>
    <header th:replace="~{layout/navbar :: navbar}"></header>
    <main th:fragment="conteudo">
        <!-- conteúdo injetado -->
    </main>
    <footer th:replace="~{layout/footer :: footer}"></footer>
</body>
</html>
```

### Uso do fragmento em páginas filhas
```html
<!-- dashboard/user-dashboard.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity6"
      th:replace="~{layout/base :: layout(~{::main})}">
<body>
<main>
    <h1>Bem-vindo, <span th:text="${usuario.nome}">Usuário</span></h1>
</main>
</body>
</html>
```

### Segurança nas views
```html
<!-- Mostrar somente para ADMIN -->
<nav th:if="${#authorization.expression('hasRole(''ADMIN'')')}">
    <a th:href="@{/admin/usuarios}">Gerenciar Usuários</a>
</nav>

<!-- Alternativa com namespace sec: -->
<a sec:authorize="hasRole('ADMIN')" th:href="@{/admin/usuarios}">Gerenciar Usuários</a>

<!-- Mostrar nome do usuário logado -->
<span sec:authentication="name">usuário</span>
```

### Formulários com validação
```html
<form th:action="@{/medicoes/vital}" th:object="${medicaoVitalForm}" method="post">
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">

    <div>
        <label for="pressaoSistolica">Pressão Sistólica</label>
        <input type="number" id="pressaoSistolica" th:field="*{pressaoSistolica}"
               th:classappend="${#fields.hasErrors('pressaoSistolica')} ? 'is-invalid' : ''">
        <span th:if="${#fields.hasErrors('pressaoSistolica')}"
              th:errors="*{pressaoSistolica}" class="error-msg"></span>
    </div>

    <!-- Global errors -->
    <div th:if="${erro}" class="alert alert-danger" th:text="${erro}"></div>
    <div th:if="${sucesso}" class="alert alert-success" th:text="${sucesso}"></div>

    <button type="submit">Registrar</button>
</form>
```

**Regras obrigatórias de template:**
- [ ] Sempre `th:action="@{/caminho}"` — nunca URL hardcoded em `action`
- [ ] Sempre `th:href="@{/caminho}"` — nunca URL hardcoded em `href`
- [ ] CSRF token em todo formulário POST (Spring Security injeta automaticamente com Thymeleaf Security)
- [ ] `th:object` e `th:field` para binding bidirecional
- [ ] Mostrar erros de campo com `th:errors` e `#fields.hasErrors`
- [ ] Fragmentos reutilizáveis em `layout/` — nunca duplicar navbar, footer ou estilos entre templates

---

## Form DTOs (Thymeleaf Binding)

Diferente dos Records usados na API REST, formulários Thymeleaf usam classes mutáveis:

```java
public class MedicaoVitalForm {

    @NotNull(message = "Pressão sistólica é obrigatória")
    @Min(value = 60, message = "Pressão sistólica mínima: 60 mmHg")
    @Max(value = 250, message = "Pressão sistólica máxima: 250 mmHg")
    private Integer pressaoSistolica;

    // getters e setters obrigatórios para binding do Thymeleaf

    public MedicaoVital toDomain() {
        // conversão para objeto de domínio
    }
}
```

- [ ] Anotações Bean Validation em todos os campos obrigatórios
- [ ] Getters e setters (Thymeleaf usa reflexão para binding)
- [ ] Método `toDomain()` para conversão ao domínio (evita mapeamento no controller)

---

## Configuração Spring Security para Thymeleaf (Form Login)

O projeto usa **JWT para a API REST** e **Form Login para as views Thymeleaf**. A SecurityConfig deve ter dois `SecurityFilterChain` beans:

```java
// FilterChain para rotas web (Thymeleaf) — form login
@Bean
@Order(1)
public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/", "/dashboard/**", "/medicoes/**", "/alertas/**",
                         "/admin/**", "/auth/login", "/auth/logout", "/css/**", "/js/**")
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/login", "/css/**", "/js/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/auth/login")
            .loginProcessingUrl("/auth/login")
            .defaultSuccessUrl("/dashboard", true)
            .failureUrl("/auth/login?error=true")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessUrl("/auth/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        )
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        );
    return http.build();
}

// FilterChain para API REST (/api/**) — JWT (já existente)
@Bean
@Order(2)
public SecurityFilterChain apiFilterChain(HttpSecurity http, ...) throws Exception {
    http
        .securityMatcher("/api/**")
        // ... configuração JWT existente
    return http.build();
}
```

---

## Checklist Pré-Entrega de View

- [ ] Todos os links testados manualmente (nenhum 404 ou 500)
- [ ] Formulários submetidos e validações disparadas corretamente
- [ ] Mensagens de erro e sucesso visíveis ao usuário
- [ ] Rotas protegidas inacessíveis sem autenticação (redirect para login)
- [ ] Rotas ADMIN inacessíveis para ROLE_USER (página 403)
- [ ] Navbar exibe opções corretas por role
- [ ] CSRF token presente em todos os formulários POST
- [ ] Nenhum link hardcoded (todos via `th:href="@{...}"`)
- [ ] Fragmentos de layout sem duplicação entre templates
- [ ] Página de erro 403 e 404 configuradas

---

## Dependências Maven Necessárias

```xml
<!-- Thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Thymeleaf + Spring Security (sec: namespace) -->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity6</artifactId>
</dependency>
```
