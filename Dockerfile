FROM java:8 

# Install maven
RUN apt-get update
RUN apt-get install -y maven

WORKDIR /code

# Prepare by downloading dependencies
ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]

# Adding source, compile and execut testing
ADD src /code/src
EXPOSE 9999
CMD mvn -Dtest=SchedulerPageTest#testWeekSize test site jetty:run
