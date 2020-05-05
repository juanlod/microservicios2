FROM openjdk:12
VOLUME /tmp
EXPOSE 8002
ADD ./target/springboot-servicio-item-1.0.0.0.war servicio-items.jar
ENTRYPOINT ["java", "-jar", "/servicio-items.jar"]