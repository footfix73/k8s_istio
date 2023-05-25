#Habilitar estos comentarios si queremos hacer el ejemplo de K8S
#FROM adoptopenjdk/openjdk11:alpine-slim
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

# Opcion de prueba para el tema de despliegue openshift + jenkins
# Build the application first using Maven
FROM maven:3.8-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn install

# Inject the JAR file into a new container to keep the file small
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/k8sistio-*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar app.jar"]