FROM openjdk:17-alpine
ADD ./target/user_survey_api-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]