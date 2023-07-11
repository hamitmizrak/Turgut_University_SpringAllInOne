
# for Java Spring Boot project Dockerize,  image

# JDK
FROM openjdk:17-oracle

# Variable
ARG JAR_FILE=Turgut_University_SpringAllInOne-0.0.1-SNAPSHOT.jar

# varaible change
ADD ${JAR_FILE} blog.jar
ENTRYPOINT ["java","-jar","/blog.jar"]