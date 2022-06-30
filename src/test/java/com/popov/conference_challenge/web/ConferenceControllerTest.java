package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.dto.ConferenceDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ConferenceControllerTest {
    @Test
    void saveConferenceNotAuthenticated() {
        given()
                .with()
                .body(new ConferenceDto(null,
                        "Java conference",
                        LocalDate.of(2022, 9, 1),
                        LocalDate.of(2022, 9, 5),
                        12,
                        false))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/conferences")
                .then()
                .statusCode(401);
    }

    @Test
    void saveConferenceLackOfCredentials() {
        given()
                .auth().basic("user", "user")
                .with()
                .body(new ConferenceDto(null,
                        "Java conference",
                        LocalDate.of(2022, 9, 1),
                        LocalDate.of(2022, 9, 5),
                        12,
                        false))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/conferences")
                .then()
                .statusCode(403);
    }

    @Test
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveConferenceNormal() {
        given()
                .auth().basic("admin", "admin")
                .with()
                .body(new ConferenceDto(null,
                        "Java conference",
                        LocalDate.of(2022, 9, 1),
                        LocalDate.of(2022, 9, 5),
                        12,
                        false))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/conferences")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("Java conference"))
                .body("startDate", is("2022-09-01"))
                .body("endDate", is("2022-09-05"))
                .body("seats", is(12))
                .body("cancelled", is(false));
    }

    @Test
    @Sql(scripts = {"/scripts/conference.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void cancelConference() {
        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .when()
                .request("PATCH", "/api/conferences/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("Java conference"))
                .body("startDate", is("2022-09-01"))
                .body("endDate", is("2022-09-05"))
                .body("seats", is(12))
                .body("cancelled", is(true));
    }
}