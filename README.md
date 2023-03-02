# Smart Appliance API
Simple REST API that controlls a non-physical appliance.
# Getting started
Before running the API you need to create a local database. In this setup I decided to use a MySql database running in a docker container.
## Prerequisites
+ Docker

Go to the link below and follow the guide for installing the docker engine through [Docker Desktop](https://docs.docker.com/desktop/install/windows-install/) on Windows.

When finnished downloading start the docker desktop app.

+ MySql database

```docker run --name test -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=user -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=testing -p 3306:3306 -v /var/lib/mysql -d mysql/mysql-server:latest```

You will have to create a new db user and grant them privileges. 
Run docker container and login to your database using root
```
docker exec -it test bash -l
```
```
mysql -uroot -p
```
Then enter your root password.


```
mysql> CREATE USER 'username'@'%' IDENTIFIED BY 'password';
```
+ Postman

Download [Postman](https://www.postman.com/downloads/)


If you don't want to use Postman you can call the endpoints using for example curl

Example curl POST command:
```
curl -d '{"name" : "Name", "model": "Model123"}' -H 'Content-Type: application/json' http://localhost:8080/appliance/add/dishwasher
```
Example curl GET command:
```
curl http://localhost:8080/user/1/status
```

## Setting up

1. Clone the Repository
```
git clone https://github.com/limachar/smartApplianceAPI.git
```

2. Add configurations to the resources/application.properties file.
 ```
 # Db connection to local mysql db running in docker container
spring.datasource.url = jdbc:mysql://localhost:3306/testing
spring.datasource.username = username
spring.datasource.password = password

# Hibernate properties
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update

# No Spring banner at startup
spring.main.banner-mode=off
 ```
## Run

You can run the project in your IDE or by running 
```./gradlew bootRun```

## Usage
Available endpoints:
+ /appliance/add/dishwasher - POST
To start you need to add an appliance to the system, mocking the way an appliance would exist in the cloud before the user gets it. JSON data to send in the body is **name** and **model**, see example above under curl POST example.
+ /user/createUser - POST
JSON data is **name**
+ /user/{id}/addAppliance/{applianceId} - PUT
This is the only endpoint that uses {id}, which will be 1 for the first user you create. So to add the first appliance you created {id}=1 and {applianceId}=1.
+ /user/{applianceId}/status - GET
+ /user/{applianceId}/OnOff -PUT
Turns appliance on and off
+ /user/{applianceId}/{job} - PUT
Start a program om the appliance. Available jobs are: AUTO_45, AUTO_65, AUTO_70, ECO_55, QUICK_65.
+ /user/{applianceId}/job - GET
+ /user/{applianceId}/terminateJob - PUT


