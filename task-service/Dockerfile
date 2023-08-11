FROM eclipse-temurin:17-jdk-jammy
MAINTAINER ITAcademy

ADD ./target/task-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/task-service.jar"]

EXPOSE 8080 8089