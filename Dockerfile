FROM eclipse-temurin 

WORKDIR /app

COPY . .

# give permission to mvnw
RUN chmod +x mvnw

# build spring boot jar
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/InternalWorkingSpringBoot-0.0.1-SNAPSHOT.jar"]

# FROM eclipse-temurin

# WORKDIR /app

# COPY target/InternalWorkingSpringBoot-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080

# #java jar app.jar
# ENTRYPOINT ["java","-jar","app.jar"]
