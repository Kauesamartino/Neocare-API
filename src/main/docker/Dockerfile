# ESTÁGIO 1: Build da Aplicação
# Usamos uma imagem que já vem com o Maven (ferramenta de build) e o JDK.
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker.
# Se as dependências não mudarem, o Docker não vai baixá-las de novo.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código-fonte da sua aplicação
COPY src ./src


# ESTÁGIO 2: Execução da Aplicação
# Usamos uma imagem leve, apenas com o Java para rodar.
FROM openjdk:17-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia APENAS o arquivo .jar compilado do estágio 'build' para a imagem final.
COPY --from=build /app/target/Neocare-API-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a sua aplicação usa
EXPOSE 8080

# Comando para iniciar a sua aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]