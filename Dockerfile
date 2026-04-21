# Image Maven + JDK 17 — compile et lance directement sans JAR
FROM maven:3.9-eclipse-temurin-17

LABEL maintainer="com.raina" \
      app="appmain" \
      version="0.0.1-SNAPSHOT"

WORKDIR /app

# Copie du POM et téléchargement des dépendances (cache Docker)
COPY pom.xml .
RUN mvn dependency:resolve -B -q

# Copie du code source
COPY src ./src

# Port exposé par Spring Boot
EXPOSE 8080

# Lancement direct sans créer de JAR
ENTRYPOINT ["mvn", "spring-boot:run"]