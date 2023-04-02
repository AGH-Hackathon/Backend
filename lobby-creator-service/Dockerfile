FROM gradle:jdk17 as GRADLE_BUILD
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew :lobby-creator-service:bootJar

FROM amazoncorretto:17
COPY --from=GRADLE_BUILD /home/gradle/src/lobby-creator-service/build/libs/lobby-creator-service-1.0.0.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "lobby-creator-service-1.0.0.jar"]