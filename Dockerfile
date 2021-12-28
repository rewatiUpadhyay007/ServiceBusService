FROM openjdk:8
ADD target/ServiceBusService.jar ServiceBusService.jar
EXPOSE 9003
ENTRYPOINT ["java","-jar","ServiceBusService.jar"]