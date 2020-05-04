FROM openjdk:8-jdk-alpine
# Refer to Maven build -> finalName
ARG JAR_FILE=target/find-by-ip.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-Dip=181.44.186.52","-jar","app.jar"]
