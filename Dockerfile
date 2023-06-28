FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
copy pom.xml .
RUN mvn -f pom.xml clean package

FROM openjdk:17-alpine
COPY --from=build "target/ventas-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]