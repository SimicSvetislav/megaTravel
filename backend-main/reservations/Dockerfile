FROM anapsix/alpine-java
ADD target/reservations-0.0.1-SNAPSHOT.jar reservations-ms.jar
ENTRYPOINT ["java", "-jar", "/reservations-ms.jar"]
EXPOSE 8122
