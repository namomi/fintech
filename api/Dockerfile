FROM openjdk:11
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/fintech.jar api.jar
ENTRYPOINT ["java", "-jar", "/deploy/api.jar"]