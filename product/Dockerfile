FROM openjdk:21-jdk-slim
COPY build/libs/product-0.0.1-SNAPSHOT.jar userApp.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "userApp.jar"]