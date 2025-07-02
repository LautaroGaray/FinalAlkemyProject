
# IMAGEN MODELO DEL JDK YA EXISTENTE EN DOCKER HUB
FROM eclipse-temurin:17.0.15_6-jdk
#openjdk:17-jdk  //This image is officially deprecated
# amazoncorretto:17 es otra opcion disponible


# Establece el directorio de trabajo
WORKDIR /app

#  Primero copia SOLO los archivos del wrapper de Maven
COPY mvnw* ./

#  Copia tanto mvnw como mvnw.cmd. Copia la configuración del wrapper de Maven
COPY .mvn .mvn

# Arregla los finales de línea y hace ejecutable # Convierte CRLF de Windows a LF de Linux y añade permisos de ejecución
RUN sed -i 's/\r$//' mvnw && \
    chmod +x mvnw

#  Verifica que mvnw funciona
RUN ./mvnw --version

#  Copia el archivo POM para resolver dependencias
COPY pom.xml .

# Descarga las dependencias para uso offline
RUN ./mvnw dependency:go-offline -B

#  Copia el código fuente
COPY src src

# Construye la aplicación #  Compila y empaqueta la aplicación, saltando tests
RUN ./mvnw clean install -DskipTests


# LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar","/app/target/FinalProjectAlkemy-0.0.1-SNAPSHOT.jar"]
