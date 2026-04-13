# 🩺 Neocare API — Deploy no Azure + Datadog

> Deploy completo da Neocare API utilizando **Azure Container Apps**, **PostgreSQL** e observabilidade com **Datadog APM**.

---

## 📋 Índice

1. [Pré-requisitos](#-pré-requisitos)
2. [Clonar o repositório](#-1-clonar-o-repositório)
3. [Criar Resource Group](#-2-criar-resource-group)
4. [Criar Azure Container Registry](#-3-criar-azure-container-registry-acr)
5. [Build e Push da imagem Docker](#-4-build-e-push-da-imagem-docker)
6. [Criar ambiente do Container Apps](#-5-criar-ambiente-do-container-apps)
7. [Criar banco PostgreSQL](#-6-criar-banco-postgresql)
8. [Segurança](#-7-segurança-importante)
9. [Deploy da aplicação](#-8-deploy-da-aplicação)
10. [Obter URL da aplicação](#-9-obter-url-da-aplicação)
11. [Configurar Datadog APM](#-10-configurar-datadog-apm-java)
12. [Observabilidade](#-11-observabilidade)
13. [Limitações do ambiente](#-12-limitações-do-azure-container-apps)
14. [Melhorias futuras](#-melhorias-futuras)

---

## ✅ Pré-requisitos

Antes de começar, certifique-se de ter:

| Ferramenta | Descrição |
|------------|-----------|
| ☁️ Conta Azure | Acesso ao portal e permissões para criar recursos |
| 🖥️ Azure CLI | Ferramenta de linha de comando (`az`) instalada |
| 🐳 Docker | Para build e push da imagem da aplicação |
| 📊 Conta Datadog | Com uma **API Key** válida |

---

## 🧩 1. Clonar o repositório

```bash
git clone https://github.com/Kauesamartino/Neocare-API.git
cd Neocare-API
```

---

## ☁️ 2. Criar Resource Group

```bash
az group create \
  --name neocare \
  --location brazilsouth
```

---

## 📦 3. Criar Azure Container Registry (ACR)

```bash
az acr create \
  --resource-group neocare \
  --name neocareapi \
  --sku Basic
```

### 🔐 Login no ACR

```bash
az acr login --name neocareapi
```

---

## 🐳 4. Build e Push da imagem Docker

```bash
docker build -t neocareapi.azurecr.io/neocare-api:v1 .
docker push neocareapi.azurecr.io/neocare-api:v1
```

---

## 🌐 5. Criar ambiente do Container Apps

```bash
az containerapp env create \
  --name neocare-env \
  --resource-group neocare \
  --location canadacentral
```

> Este ambiente será utilizado pelo Azure Container Apps como base para os deploys.

---

## 🗄️ 6. Criar banco PostgreSQL

```bash
az postgres flexible-server create \
  --name neocare-database \
  --resource-group neocare \
  --location brazilsouth \
  --admin-user neocare_admin \
  --admin-password <SENHA_SEGURA>
```

---

## 🔐 7. Segurança (IMPORTANTE)

> ⚠️ **Nunca exponha credenciais diretamente no código ou nos scripts.**

Substitua os valores sensíveis pelos seus dados reais antes de executar:

```
DD_API_KEY=<SUA_API_KEY>
SPRING_DATASOURCE_PASSWORD=<SENHA_SEGURA>
```

### 💡 Recomendação

Utilize o **Azure Key Vault** para gerenciar secrets de forma segura em produção.

---

## 🚀 8. Deploy da aplicação

```bash
az containerapp create \
  --name neocare-api \
  --resource-group neocare \
  --environment neocare-env \
  --image neocareapi.azurecr.io/neocare-api:v1 \
  --target-port 8080 \
  --ingress external \
  --registry-server neocareapi.azurecr.io \
  --env-vars \
    DD_SERVICE=neocare-api \
    DD_ENV=dev \
    DD_VERSION=1.0 \
    DD_SITE=datadoghq.com \
    DD_API_KEY=<SUA_API_KEY> \
    SPRING_DATASOURCE_URL="jdbc:postgresql://neocare-database.postgres.database.azure.com:5432/neocare?sslmode=require" \
    SPRING_DATASOURCE_USERNAME="neocare_admin" \
    SPRING_DATASOURCE_PASSWORD=<SENHA_SEGURA>
```

---

## 🔍 9. Obter URL da aplicação

```bash
az containerapp show \
  --name neocare-api \
  --resource-group neocare \
  --query properties.configuration.ingress.fqdn
```

---

## 📊 10. Configurar Datadog APM (Java)

### No `Dockerfile`, adicionar o agente Java:

```dockerfile
ADD https://dtdg.co/latest-java-tracer /dd-java-agent.jar
```

### Na execução da aplicação, incluir o flag:

```
-javaagent:/dd-java-agent.jar
```

---

## 🧠 11. Observabilidade

As seguintes variáveis de ambiente são utilizadas para configurar o rastreamento no Datadog:

| Variável | Descrição |
|----------|-----------|
| `DD_SERVICE` | Nome do serviço monitorado |
| `DD_ENV` | Ambiente (`dev`, `staging`, `prod`) |
| `DD_VERSION` | Versão do deploy |

Essas variáveis permitem:

- 📂 Organização dos serviços no Datadog
- 🔍 Rastreamento de deploys por versão
- 📈 Análise de performance por ambiente

---

## ⚠️ 12. Limitações do Azure Container Apps

| Recurso | Status |
|---------|--------|
| Acesso ao `docker.sock` | ❌ Não disponível |
| Autodiscovery de containers | ❌ Não suportado |
| APM (tracing com `dd-java-agent`) | ✅ Funciona perfeitamente |
| Métricas de infraestrutura | ⚠️ Limitadas |

---

## 🧾 Resumo do fluxo

```
1. Clonar repositório
2. Criar Resource Group
3. Criar ACR
4. Build e push da imagem
5. Criar ambiente Container Apps
6. Criar banco PostgreSQL
7. Configurar variáveis de ambiente
8. Deploy da aplicação
9. Acessar via URL pública
10. Monitorar com Datadog
```

---

## ✅ Resultado esperado

Ao final do processo, você terá:

- 🌐 Aplicação disponível via **URL pública**
- 🗄️ Banco de dados **PostgreSQL conectado**
- 📊 **Traces visíveis** no painel do Datadog
- 🛠️ Ambiente pronto para **desenvolvimento e testes**

---

## 💡 Melhorias futuras

- [ ] Uso do **Azure Key Vault** para gerenciamento de secrets
- [ ] Pipeline de **CI/CD com GitHub Actions**
- [ ] Infraestrutura como código com **Terraform**
- [ ] Monitoramento avançado: **logs + métricas + alertas**

---

<p align="center">
  Feito com ☕ e muita <strong>resiliência</strong> 🚀
</p>
