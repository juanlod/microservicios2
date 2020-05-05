FROM openjdk:12
VOLUME /tmp
EXPOSE 8888
ADD ./target/springboot-servicio-cloud-config-server-1.0.0.0.war cloud-config-server.jar
ENTRYPOINT ["java", "-jar", "/cloud-config-server.jar"]