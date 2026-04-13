# =========================
# STAGE 1 - BUILD
# =========================
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests


# =========================
# STAGE 2 - RUNTIME
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia aplicação
COPY --from=build /app/target/Neocare-API-0.0.1-SNAPSHOT.jar app.jar

# Baixa o tracer
ADD https://dtdg.co/latest-java-tracer /dd-java-agent.jar

# IMPORTANTE: Java agent via ENV (como doc pede)
ENV JAVA_TOOL_OPTIONS="-javaagent:/dd-java-agent.jar"

# Copia o serverless-init
COPY --from=datadog/serverless-init:1 /datadog-init /app/datadog-init

# Variáveis Datadog
ENV DD_SERVICE=neocare-api
ENV DD_ENV=prod
ENV DD_VERSION=2.0
ENV DD_LOGS_ENABLED=true
ENV DD_TRACE_ENABLED=true
ENV DD_LOGS_INJECTION=true
ENV DD_SOURCE=java
ENV DD_SITE="datadoghq.com"
ENV DD_AZURE_SUBSCRIPTION_ID="ac16782f-3684-4e4f-8884-deb50b35831c"
ENV DD_AZURE_RESOURCE_GROUP="neocare"

EXPOSE 8080

ENTRYPOINT ["/app/datadog-init"]
CMD ["java", "-jar", "app.jar"]