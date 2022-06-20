FROM maven:3.8.4-openjdk-17 as maven

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17.0.2_8-jre

WORKDIR /courier-tracking

COPY --from=maven target/courier-tracking-1.0.jar ./

CMD ["java", "-jar", "./courier-tracking-1.0.jar"]