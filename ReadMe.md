**requirement to run this app:**
- java 8 
- maven 3

** to run tests**

> mvn clean test

**to run application**

>mvn spring-boot:run

**to to run application in development environment, I set spring active profile as dev in properties file. In dev profile:**

1. the in-mem DB will be populated by stores.json file

2. jwt token check will be bypassed, so you do not need to authenticate first to get jwt token 

**to see api docs:**

check http://localhost/swagger-ui.html after running application

**to call APIs in non-dev environment**

1. first call /authenticate endpoint with this body
{ "username":"admin","password":"password"}
2. set the returned jwt token in authentication header of other APIs request
 
**Additional notes:**

- I've used a ready code (HaversineAlgorithm) to find distance of two location
- I've used swagger-ui to generate swagger doc for users (fro example front end developers)
- I've used spring security to setup security. For now the valid username/password are hardcoded,
they should be move to DB later
- I've used a ready code (HaversineAlgorithm) to find distance of two location
- later we can add a Docker file, or add jib plugin and generate docker image without writing dockerfile
- for now I included Integration tests to test phase, but usually we have a separate build phase/pipeline for them
 