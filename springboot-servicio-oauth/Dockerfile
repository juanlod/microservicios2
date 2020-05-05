FROM openjdk:12
VOLUME /tmp
EXPOSE 9100
ADD ./target/springboot-servicio-oauth-1.0.0.0.war servicio-oauth.jar
ENTRYPOINT ["java", "-jar", "/servicio-oauth.jar"]