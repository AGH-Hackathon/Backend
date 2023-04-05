FROM gradle:jdk17 as GRADLE_BUILD
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :game-management:bootJar

FROM amazoncorretto:17
COPY --from=GRADLE_BUILD /home/gradle/src/game-management/build/libs/game-management-1.0.0.jar /app/
WORKDIR /app

EXPOSE 8008

ENTRYPOINT ["java", "-jar", "game-management-1.0.0.jar"]