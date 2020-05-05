FROM openjdk:12
VOLUME /tmp
EXPOSE 8761
ADD ./target/springboot-servicio-eureka-server-1.0.0.0.war eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]