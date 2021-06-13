FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=stock-management-1.0.0.jar
COPY ${JAR_FILE} stock-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "stock-management.jar"]