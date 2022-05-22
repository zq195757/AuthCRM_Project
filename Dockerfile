FROM openjdk:11-jre

COPY *.jar /authproject.jar

CMD ["--server.port=7777"]

EXPOSE 7777

ENTRYPOINT ["java", "-jar", "/authproject.jar"]