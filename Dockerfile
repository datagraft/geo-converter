FROM maven:3.8.6-openjdk-8-slim

WORKDIR /usr/src/app
COPY pom.xml /usr/src/app/
# Install maven dependencies
RUN mvn dependency:go-offline
COPY src /usr/src/app/src
# Build the .jar
RUN mvn package

# Extract the jar from the container
# docker build . -t geo-converter
# docker run -it --rm -v $(pwd):/mnt geo-converter cp /usr/src/app/target/geo-converter-0.0.1.jar /mnt
# docker image rm geo-converter