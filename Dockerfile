FROM maven:3.8.4-openjdk-17

ADD . /usr/src/teste-uol
WORKDIR /usr/src/teste-uol
EXPOSE 4567
ENTRYPOINT ["mvn", "clean", "verify", "exec:java"]