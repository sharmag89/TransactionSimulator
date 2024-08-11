FROM eclipse-temurin:21
LABEL authors="gauravsharma"
WORKDIR /consumer-app
CMD ["./gradlew", "clean", "build", "bootJar"]
COPY /consumer/build/libs/*.jar app.jar

#EXPOSE PORT
ENTRYPOINT ["java", "-jar", "app.jar"]
