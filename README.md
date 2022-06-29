### Conference-challenge (REST API)

Functional requirements:
Conference management:
 - Create new conference 
 - Cancel conference 
 - Check conference room availability (based on registered participants and conference room max
seats)

Conference participant management:
- Add participant to conference
- Remove participant from conference

#### Optional requirements:
 - Implement user authentication and protect API End-Points according user role
 - Result should be stored on GitHub or BitBucket

#### Stack of technologies:

- Java 11;
- Spring Boot 2.7.1;
- Spring Data;
- Spring Security;
- RDBMS (H2);
- FlyWay
- Rest-Assured
- Maven.

#### Test
```
mvn clean verify
```

#### Run
```
./mvnw spring-boot:run
```