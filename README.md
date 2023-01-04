# Authorisation
Authorisation is a simple Springboot microservice implementing spring cloud security, authentication, authorization with jwt tokens.

## Installation

This project requires the following;
- [Java 11 or greater](https://nodejs.org/https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.6 or greater](https://maven.apache.org/download.cgi)
- [Mysql 8] https://dev.mysql.com/doc/refman/8.0/en/installing.html

```
$ git clone https://github.com/Ngai-E/Authorisation.git
$ cd Authorisation
$ mvn clean install
```
> for troubleshooting purposes if build fails when building the project,<br>
> try building and skipping tests with ```mvn clean install -DskipTests```

- create a mysql database called security_db
Authorisatoin requires a springcloud config service, and eureka discovery service to run,
so if you already feel confortable with these concepts,
feel free to add the following properties file [sample properties file] (https://github.com/Ngai-E/Authorisation/blob/master/docs/db/auth-service.properties) to your config service config file location.
Update the application properties file to then point to your config and eureka address.

> If you are not confortable with spring cloud then do the following;
> - replace the content of the application.properties file found here _/src/main/resources/_ with [sample properties file] (https://github.com/Ngai-E/Authorisation/blob/master/docs/db/auth-service.properties) to 
> - replace YOUR_USERNAME and YOUR_USERNAME with your appropriate mysql database username and password respectively.
> - comment out the following dependencies in the pom file
> - comment out the ```@EnableDiscoveryClient``` anotation in the _AuthApplication_ main file.

## Usage
Authorisation requires the following to have been set in the database in t_paramters

## Run project
```
$mvn spring-boot:run
```
This should launch the project with a swagger documentation accessible at http://${host}:${port}/swagger-ui.html
