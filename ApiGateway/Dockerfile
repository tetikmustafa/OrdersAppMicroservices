FROM openjdk:24-jdk

ARG JAR_FILE=target/ApiGateway.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]