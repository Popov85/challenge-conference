package com.popov.conference_challenge.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ConferenceParticipantControllerTest {

    @Test
    @Sql(scripts = {"/scripts/conference.sql", "/scripts/participants.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addParticipant() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("POST", "/api/conferences/1/participants/1/add")
                .then()
                .statusCode(201);
    }

    @Test
    @Sql(scripts = {"/scripts/conference.sql", "/scripts/participants.sql", "/scripts/conference_participant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void removeParticipant() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("DELETE", "/api/conferences/1/participants/3/remove")
                .then()
                .statusCode(204);
    }

    @Test
    @Sql(scripts = {"/scripts/conference.sql", "/scripts/participants.sql", "/scripts/conference_participant.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllParticipants() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("GET", "/api/conferences/1/participants")
                .then()
                .statusCode(200)
                .body("totalPages", is(1))
                .body("totalElements", is(3))
                .body("content.size()", is(3));
    }
}