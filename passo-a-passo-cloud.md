# 🩺 Neocare API — Deploy no Azure (Opcional: Datadog)

> Deploy completo da Neocare API utilizando **Azure Container Apps**, **PostgreSQL Flexible Server** e opcionalmente **Datadog APM**.

---

## 📋 Índice

1. Pré-requisitos
2. Clonar repositório
3. Criar Resource Group
4. Criar Azure Container Registry
5. Build e Push da imagem
6. Criar ambiente Container Apps
7. Criar banco PostgreSQL
8. Liberar acesso ao banco (firewall)
9. Criar database
10. Deploy da aplicação
11. Obter URL
12. (Opcional) Datadog APM
13. Observações importantes

---

## ✅ Pré-requisitos

* Conta Azure
* Azure CLI (`az`)
* Docker com `buildx`
* (Opcional) Conta Datadog

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
  --location canadacentral
```

---

## 📦 3. Criar Azure Container Registry (ACR)

```bash
az acr create \
  --resource-group neocare \
  --name neocareapi \
  --sku Basic
```

### 🔐 Habilitar admin e login

```bash
az acr update -n neocareapi --admin-enabled true
az acr login --name neocareapi
```

---

## 🐳 4. Build e Push da imagem (Mac M1/M2 compatível)

```bash
docker buildx build \
  --platform linux/amd64 \
  -t neocareapi.azurecr.io/neocare-api:latest \
  --push \
  -f src/main/docker/Dockerfile .
```

---

## 🌐 5. Criar ambiente do Container Apps

```bash
az containerapp env create \
  --name neocare-env \
  --resource-group neocare \
  --location canadacentral
```

---

## 🗄️ 6. Criar banco PostgreSQL

```bash
az postgres flexible-server create \
  --name neocare-database \
  --resource-group neocare \
  --location canadacentral \
  --admin-user neocare_admin \
  --admin-password <SENHA_SEGURA>
```

---

## 🔓 7. Liberar acesso ao banco (Firewall aberto)

```bash
az postgres flexible-server firewall-rule create \
  --resource-group neocare \
  --name neocare-database \
  --rule-name allow-all \
  --start-ip-address 0.0.0.0 \
  --end-ip-address 255.255.255.255
```

> ⚠️ **Atenção:** Isso libera acesso público total. Use apenas para testes.

---

## 🧱 8. Criar database `neocare`

Conecte no banco e execute:

```sql
CREATE DATABASE neocare;
```

---

## 🚀 9. Deploy da aplicação

```bash
az containerapp create \
  --name neocare-api \
  --resource-group neocare \
  --environment neocare-env \
  --image neocareapi.azurecr.io/neocare-api:latest \
  --registry-server neocareapi.azurecr.io \
  --registry-username <ACR_USERNAME> \
  --registry-password <ACR_PASSWORD> \
  --target-port 8080 \
  --ingress external \
  --cpu 0.5 \
  --memory 1Gi \
  --env-vars \
    SPRING_DATASOURCE_URL="jdbc:postgresql://neocare-database.postgres.database.azure.com:5432/neocare?sslmode=require" \
    SPRING_DATASOURCE_USERNAME="neocare_admin" \
    SPRING_DATASOURCE_PASSWORD="<SENHA_SEGURA>"
```

---

## 🔍 10. Obter URL da aplicação

```bash
az containerapp show \
  --name neocare-api \
  --resource-group neocare \
  --query properties.configuration.ingress.fqdn
```

---

## 📊 11. (Opcional) Datadog APM

### Variáveis adicionais no deploy:

```bash
DD_SERVICE=neocare-api
DD_ENV=dev
DD_VERSION=1.0
DD_SITE=datadoghq.com
DD_API_KEY=<SUA_API_KEY>
```

### No Dockerfile:

```dockerfile
ADD https://dtdg.co/latest-java-tracer /dd-java-agent.jar
```

### Na execução Java:

```
-javaagent:/dd-java-agent.jar
```

---

## 🧠 Observações importantes

* ❗ Evite expor:

  * Senhas
  * API Keys
* ✅ Use **Azure Key Vault** em produção
* ⚠️ Firewall aberto é apenas para ambiente de teste
* 🧱 Container Apps não suporta docker.sock (limitação do ambiente)

---

## 🧾 Fluxo resumido

```
1. Criar RG
2. Criar ACR
3. Build + Push imagem
4. Criar ambiente
5. Criar PostgreSQL
6. Liberar firewall
7. Criar database
8. Deploy Container App
9. Acessar URL
```

---

## ✅ Resultado esperado

* 🌐 API pública funcionando
* 🗄️ PostgreSQL conectado
* 📊 (Opcional) Traces no Datadog

---

<p align="center">
  Deploy feito com ☕ + sofrimento em cloud 🚀
</p>
