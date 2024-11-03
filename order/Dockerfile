FROM openjdk:21-jdk-slim
COPY build/libs/order-0.0.1-SNAPSHOT.jar userApp.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "userApp.jar"]