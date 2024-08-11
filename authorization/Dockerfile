# Package stage
FROM eclipse-temurin:21
LABEL authors="gauravsharma"
WORKDIR /authorization-app
CMD ["./gradlew", "clean", "build", "bootJar"]
COPY ./authorization/build/libs/*.jar app.jar

#EXPOSE PORT
ENTRYPOINT ["java", "-jar", "app.jar"]