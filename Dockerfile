FROM bellsoft/liberica-openjdk-alpine:latest
VOLUME /temp
EXPOSE 8080
ARG JAR_FILE=/target/nice-ia-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
CMD ["java", "-jar", "/app.jar"]