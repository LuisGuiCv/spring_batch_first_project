FROM openjdk
EXPOSE 8080
EXPOSE 3306
ADD target/spring-batch-docker.jar spring-batch-docker.jar
ENTRYPOINT ["java","-jar","/spring-batch-docker.jar"]