FROM java:8-jdk
COPY  target/hardParadise-0.0.1-SNAPSHOT.jar /usr/src/app/hardParadise-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/app/
EXPOSE 8443
CMD ["java", "-jar", "hardParadise-0.0.1-SNAPSHOT.jar"]
