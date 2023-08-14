FROM eclipse-temurin:17-jdk-jammy
MAINTAINER ITAcademy

ADD ./target/user-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/user-service.jar"]

EXPOSE 8080 8089