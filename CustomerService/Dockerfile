FROM openjdk:24-jdk

ARG JAR_FILE=target/CustomerService.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app.jar"]