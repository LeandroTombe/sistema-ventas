FROM openjdk:17

VOLUME /tmp

EXPOSE 4000

ADD ./target/ventas-0.0.1-SNAPSHOT.jar app.jar

COPY sistema_venta.sql /docker-entrypoint-initdb.d/

ENTRYPOINT ["java", "-jar","/app.jar"]
