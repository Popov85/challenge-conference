package com.popov.conference_challenge.web;

import com.popov.conference_challenge.service.dto.ParticipantDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ParticipantControllerTest {

    @Test
    void saveConferenceLackOfCredentials() {
        given()
                .auth().basic("user", "user")
                .with()
                .body(new ParticipantDto(null, "Me", "testtest", true))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/participants")
                .then()
                .statusCode(403);
    }

    @Test
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void saveParticipantNormal() {
        given()
                .auth().basic("admin", "admin")
                .with()
                .body(new ParticipantDto(null, "Me", "testtest", true))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/participants")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("username", is("Me"))
                .body("password", is("testtest"))
                .body("active", is(true));
    }

    @Test
    @Sql(scripts = {"/scripts/participants.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deactivateParticipant() {
        given()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .when()
                .request("PATCH", "/api/participants/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("username", is("Me"))
                .body("password", is("testtest"))
                .body("active", is(false));
    }
}