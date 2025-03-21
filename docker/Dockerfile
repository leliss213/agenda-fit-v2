# Usa a imagem do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR para dentro do container
COPY target/agenda-fit-v2.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para rodar o Spring Boot
CMD ["java", "-jar", "app.jar"]
