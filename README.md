## About the MarsRover Coding Challenge

* The microservice runs with **Java 1.8** and it was developed with spring boot project.
* To send plateau dimensions, rover position, and rover instructions 3 POST API calls are used.
* For object-relational mapping **Spring Data JPA** is used.
* **H2 in memory db** is used to persist the game data. 
* **Swagger** is used for **REST API** documentation  
* **JUnit and Mockito** framework is used in writing and running your unit tests.
* **Test Coverage : 80.5 %**
* **SonarQube analyze** was done with SonarLint Eclipse plugin.
* Spring DevTools was used during development.

### GitHub  
  The application can be downloaded to local repository by the following command after configuring SSH settings : 
  
  ```
  git clone git@github.com:OzlemDoganGitPortfolio/marsrover.git
  ```
### To execute the game
To run the application and play the game, issue the following command from the project home directory:
  
```
$ java -jar marsrover-0.0.1-SNAPSHOT.jar  
```
```
$ ./mvnw spring-boot:run 
```
* Then, access the following url, from two different browser session windows/tabs:

```
http://localhost:8080
```
* If you want to check the database: (**username: sa**, **password:password**)

```
http://localhost:8080/h2-console/
```
* API documentation

```
http://localhost:8080/swagger-ui.html
```
### Database Configurations - Embedded H2 database
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
```


