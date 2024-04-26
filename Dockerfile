FROM openjdk:8

WORKDIR /app

COPY ./target/ProductRestAPI_WithoutDB-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "ProductRestAPI_WithoutDB-0.0.1-SNAPSHOT.jar"]