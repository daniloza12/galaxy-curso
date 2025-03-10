FROM openjdk:17-jdk-slim
#Environment vars
#ENV USER_NAME=
#ENV USER_PASSWORD=
#ENV JAVA_OPTS="-Dspring.cloud.config.server.git.username=$USER_NAME -Dspring.cloud.config.server.git.password=$USER_PASSWORD"
LABEL build_date="31-08-2024"
LABEL name="MS Gestion Productos"
LABEL version="1.0"
LABEL description="Microservicio de gestion de productos"
VOLUME /tmp
COPY build/libs/ms-negocio-gestion-productos-eks-only*.jar ms-negocio-gestion-productos.jar
ENTRYPOINT ["java","-jar","/ms-negocio-gestion-productos.jar"]