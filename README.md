# Real-Estate-Registry

A simple real estate registry for buildings.

Installation Instructions:

git clone https://github.com/iamEJ/Real-Estate-Registry.git

cd Real-Estate-Registry
mvn install
mvn spring-boot:run

Program runs on http://localhost:8080

Main API:
http://localhost:8080/api/v1/owners
http://localhost:8080/api/v1/buildings

Documentation:
http://localhost:8080/swagger-ui.html

H2 database:
http://localhost:8080/console

---------------------------------------------------------------
If build fails delete "org." before "junit.vintage" in pom.xml 
