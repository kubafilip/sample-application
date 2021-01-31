FROM openjdk:11
ADD build/libs/spring-app-1.0.jar .
EXPOSE 8000
CMD java -jar spring-app-1.0.jar