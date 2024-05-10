FROM eclipse-temurin:22-alpine

WORKDIR /app

COPY target/test-skazy-backend*.jar /app/test-skazy-backend.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/test-skazy-backend.jar"]