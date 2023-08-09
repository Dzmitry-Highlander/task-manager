FROM eclipse-temurin:17-jdk-jammy

COPY task-service/.mvn/ .mvn
COPY task-service/mvnw task-service/pom.xml ./
RUN ./mvnw dependency:resolve

COPY task-service/src ./src

CMD ["./mvnw", "spring-boot:run"]