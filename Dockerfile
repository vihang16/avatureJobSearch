FROM openjdk:11-jdk-slim

# time zone
ENV TZ=Europe/Berlin

# run app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# if you want to start the app using an application.properties file located
#ENTRYPOINT ["java","-jar","/app.jar", "--spring.config.location=file:/application.properties"]

# if you've already overriden configuration settings via environment variables
ENTRYPOINT ["java","-jar","/app.jar"]