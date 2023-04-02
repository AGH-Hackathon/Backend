FROM gradle:jdk17 as GRADLE_BUILD
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :lobby-creator-service:bootJar

FROM amazoncorretto:17
COPY --from=GRADLE_BUILD /home/gradle/src/lobby-creator-service/build/libs/lobby-creator-service-1.0.0.jar /app/
WORKDIR /app

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "lobby-creator-service-1.0.0.jar"]