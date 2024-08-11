FROM eclipse-temurin:21
LABEL authors="gauravsharma"
WORKDIR ./producer
CMD ["./gradlew", "clean", "build", "bootJar"]
COPY /producer/build/libs/*.jar app.jar

#EXPOSE PORT
ENTRYPOINT ["java", "-jar", "app.jar"]
