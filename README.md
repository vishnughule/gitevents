# Gitevents app

This project is built with help of Angular 6 and Spring Boot.

Front end pages are developed using Angular 6 
Backend apis are developed using Spring Boot

Project structure 

	gitevents (Consist of two modules)
	|
	|
	|---gitevents-ui (Front end Module)
	|
	|---gitevents-api (Backend Module)

## Build

To run this project you need to have maven version of atleast 3.1.0.

To build this project run  mvn clean install on root folder of the project gitevents.

It is good to have Node and npm installed already, Even if Node and npm not installed it will try to install them as part of maven build process.

Once project is successfully Built , Navigate to gitevents/gitevnets-api/target and run below command

java -jar gitevents-api-0.0.1-SNAPSHOT.jar

try localhost:8080 and main page should appear


## Running unit tests

Run mvn test on gitevnts-api project 


## Further help

