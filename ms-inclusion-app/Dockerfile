FROM eclipse-temurin:21-jre
COPY ms-inclusion-app-0.0.1.jar /home/ms-inclusion-app.jar
EXPOSE 9191
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/home/ms-inclusion-app.jar"] 
