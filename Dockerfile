FROM maven:3.9.6-eclipse-temurin-22 as builder

WORKDIR /app

COPY . .

RUN mvn package

FROM eclipse-temurin:22-alpine

WORKDIR /app

COPY --from=builder /app/target/test-skazy-backend*.jar test-skazy-backend.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","test-skazy-backend.jar"]