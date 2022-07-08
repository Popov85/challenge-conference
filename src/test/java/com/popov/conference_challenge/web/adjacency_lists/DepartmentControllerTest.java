package com.popov.conference_challenge.web.adjacency_lists;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DepartmentControllerTest {

    @Test
    void saveDepartment() {

    }

    @Test
    @Sql(scripts = {"/scripts/departments.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findSubTreeFromTop() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("GET", "/api/departments/sub-tree")
                .then()
                .log().body()
                .statusCode(200)
                .body("size()", is(3));
    }

    @Test
    @Sql(scripts = {"/scripts/departments.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/scripts/_clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findSubTreeById() {
        given()
                .auth().basic("admin", "admin")
                .when()
                .request("GET", "/api/departments/1/sub-tree")
                .then()
                .log().body()
                .statusCode(200);
    }
}