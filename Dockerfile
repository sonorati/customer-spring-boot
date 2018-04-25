FROM openjdk:8-jdk-alpine

ENV JAVA_HOME              /usr/lib/jvm/java-8-openjdk-amd64
ENV PATH                   $PATH:$JAVA_HOME/bin

ENV DATABASE database
ENV SERVICE_NAME tiger
ADD target/ordertiger-demo.jar ordertiger-demo.jar


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ordertiger-demo.jar"]
HEALTHCHECK --interval=10s CMD wget -qO- localhost:8080/actuator/health