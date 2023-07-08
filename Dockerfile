FROM maven:3.8.5-jdk-11 AS build
COPY . /root/app/
WORKDIR /root/app
RUN mvn clean package

FROM openjdk:11-jre-slim
EXPOSE 9090
COPY --from=build /root/app/ /home/app/
WORKDIR /home/app
ENTRYPOINT ["java", "-jar", "-Xmx1512m", "./target/apl-back-fase1-1.0.0.jar"]

