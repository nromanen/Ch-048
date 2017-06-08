FROM openjdk:8 

ENV MAIN_JAR 'allure-docker-example-1.1.0.jar'
ENV ASPECT_JAR 'deps/aspectjweaver-1.8.0.jar'

# Install maven
RUN apt-get update && apt-get upgrade -y && apt-get dist-upgrade -y
RUN apt-get install -fy unzip git
RUN apt-get install -y maven

RUN wget https://github.com/allure-framework/allure-core/releases/download/allure-core-1.4.23/allure-commandline.zip && \
    unzip allure-commandline.zip && bin/allure && rm allure-commandline.zip



WORKDIR /code

# Prepare by downloading dependencies
ADD pom.xml /code/pom.xml

# Adding source, compile and execut testing
ADD src /code/src
RUN mkdir /code/target
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "compiler:compile"]


EXPOSE 9999

CMD mvn -Dtest=SchedulerPageTest test && \
    allure generate /code/target/allure-results && \
    allure report open -p 9999
