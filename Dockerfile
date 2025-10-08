FROM openjdk:25

COPY build/libs/marketplace-backend-0.0.1-SNAPSHOT.jar marketplace-backend.jar

ENTRYPOINT ["java", "-jar", "/marketplace-backend.jar"]