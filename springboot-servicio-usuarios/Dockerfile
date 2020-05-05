FROM openjdk:12
VOLUME /tmp
ADD ./target/springboot-servicio-usuarios-1.0.0.0.war servicio-usuarios.jar
ENTRYPOINT ["java", "-jar", "/servicio-usuarios.jar"]