FROM anapsix/alpine-java
ADD target/search-0.0.1-SNAPSHOT.jar search-ms.jar
ENTRYPOINT ["java", "-jar", "/search-ms.jar"]
EXPOSE 8123
