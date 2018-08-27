FROM java:8
MAINTAINER Christopher Mepham (crmepham@hotmail.co.uk)
VOLUME /tmp
ADD frontend-service-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]
