FROM eclipse-temurin:17-jdk-jammy

COPY audit-service/.mvn/ .mvn
COPY audit-service/mvnw audit-service/pom.xml ./
RUN ./mvnw dependency:resolve

COPY audit-service/src ./src

CMD ["./mvnw", "spring-boot:run"]