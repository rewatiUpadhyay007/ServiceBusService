FROM openjdk:8
ADD target/pdeAzureService.jar pdeAzureService.jar
EXPOSE 9003
ENTRYPOINT ["java","-jar","pdeAzureService.jar"]