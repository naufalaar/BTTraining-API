#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
ARGS DB_HOST
ENV DB_HOST=${DB_HOST}
ARGS DB_SCHEMA
ENV DB_HOST=${DB_SCHEMA}
ARGS DB_USER
ENV DB_USER=${DB_USER}
ARGS DB_PASSWORD
ENV DB_PASSWORD=${DB_PASSWORD}
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/BTTraining-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
