# Stage 1: Build do JAR
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia arquivos do Maven
COPY gerenciador-tarefas/pom.xml .
COPY gerenciador-tarefas/src ./src

# Instala Maven e builda o JAR
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

# Stage 2: Rodar a aplicação
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o JAR do stage anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe porta
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
