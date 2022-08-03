FROM openjdk:17-ea-jdk-oracle
RUN mkdir -p /myapp
COPY target/spring-boot-web.jar myapp/spring-boot-web.jar
ENTRYPOINT ["java","-jar","myapp/spring-boot-web.jar"]