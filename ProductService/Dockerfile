FROM openjdk:24-jdk

ARG JAR_FILE=target/ProductService.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]