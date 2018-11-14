# Build
mvn clean package payara-micro:bundle

# Artifacts
- micro.jar - Library that can be used in another project
- micro.war - Application that can be deployed to an application server or to a docker image
- micro-microbundle.jar - Application runnable from CLI

# Run from CLI
java -jar target/micro-microbundle.jar 

# Call
http://192.168.0.103:8080/resources/users/1
Payara might run on a different url, it is printed out after it starts in the console
