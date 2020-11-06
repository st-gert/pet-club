FROM adoptopenjdk/maven-openjdk11 as builder
COPY ./pom.xml /opt/app/pom.xml
RUN mvn -f /opt/app dependency:go-offline
COPY ./src /opt/app/src
RUN mvn -f /opt/app package

FROM adoptopenjdk/openjdk11
COPY --from=builder /opt/app/target/*.jar /opt/spring-boot.jar
ENTRYPOINT ["java", "-jar", "/opt/spring-boot.jar"]

