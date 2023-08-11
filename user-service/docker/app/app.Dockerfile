FROM eclipse-temurin:17-jdk-jammy

COPY user-service/.mvn/ .mvn
COPY user-service/mvnw user-service/pom.xml ./
RUN ./mvnw dependency:resolve

COPY user-service/src ./src

CMD ["./mvnw", "spring-boot:run"]