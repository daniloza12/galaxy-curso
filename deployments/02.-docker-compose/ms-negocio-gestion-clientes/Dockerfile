FROM openjdk:17-jdk-slim
#FROM openjdk
LABEL build_date="13-04-2024"
LABEL name="MS Gestión Clientes"
LABEL version="1.0"
LABEL description="Microservicio de gestión de clientes"

VOLUME /tmp
COPY build/libs/ms-negocio-gestion-clientes*.jar ms-negocio-gestion-clientes.jar
ENTRYPOINT ["java","-jar","/ms-negocio-gestion-clientes.jar"]