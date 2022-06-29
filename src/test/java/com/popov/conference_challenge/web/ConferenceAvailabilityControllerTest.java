package com.popov.conference_challenge.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ConferenceAvailabilityControllerTest {

    @Test
    @Sql(scripts = {"/scripts/conference.sql", "/scripts/participants.sql", "/scripts/conference_participant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void checkAvailabilityConferenceNotFound() {
        given()
                .auth().basic("user", "user")
                .when()
                .request("GET", "/api/conference-availability/2")
                .then()
                .statusCode(500);
    }

    @Test
    @Sql(scripts = {"/scripts/conference.sql", "/scripts/participants.sql", "/scripts/conference_participant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void checkAvailabilityPositive() {
        given()
                .auth().basic("user", "user")
                .when()
                .request("GET", "/api/conference-availability/1")
                .then()
                .statusCode(200)
                .body("seatsRemaining", is(9));
    }

    @Test
    @Sql(scripts = {"/scripts/conference.sql", "/scripts/participants.sql", "/scripts/conference_participant_overflow.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void checkAvailabilityNegative() {
        given()
                .auth().basic("user", "user")
                .when()
                .request("GET", "/api/conference-availability/1")
                .then()
                .statusCode(200)
                .body("seatsRemaining", is(0));
    }
}