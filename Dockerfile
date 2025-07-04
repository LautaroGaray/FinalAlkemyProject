
# IMAGEN MODELO DEL JDK YA EXISTENTE EN DOCKER HUB
FROM eclipse-temurin:17.0.15_6-jdk
#openjdk:17-jdk  //This image is officially deprecated
# amazoncorretto:17 es otra opcion disponible

# INFORMAR EL PUERTO DONDE SE EJECUTA EL CONTENEDOR (INFORMATIVO)
EXPOSE 8080

# DEFINIR DIRECTORIO RAIZ DE NUESTRO CONTENEDOR
WORKDIR /root

# COPIAR Y PEGAR ARCHIVOS POM Y MAVEN DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# DESCARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline

# COPIAR EL CODIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

# CONSTRUIR NUESTRA APLICACION
RUN ./mvnw clean install -DskipTests

# LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar","/root/target/clase11docker-0.0.1-SNAPSHOT.jar"]
