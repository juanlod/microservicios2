FROM openjdk:12
VOLUME /tmp
ADD ./target/springboot-servicio-productos-1.0.0.0.war servicio-productos.jar
ENTRYPOINT ["java", "-jar", "/servicio-productos.jar"]