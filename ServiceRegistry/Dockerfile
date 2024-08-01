FROM openjdk:24-jdk

ARG JAR_FILE=target/ServiceRegistry.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar"]