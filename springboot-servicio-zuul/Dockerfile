FROM openjdk:12
VOLUME /tmp
EXPOSE 8090
ADD ./target/springboot-servicio-zuul-1.0.0.0.war zuul-server.jar
ENTRYPOINT ["java", "-jar", "/zuul-server.jar"]