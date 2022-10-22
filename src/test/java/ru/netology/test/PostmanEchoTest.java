package ru.netology.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


public class PostmanEchoTest {

    @Test
    void schouldPostmanEcho() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("some data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("headers.content-type", equalTo("text/plain; charset=UTF-8"))
                .body ("headers.content-length",equalTo("9"))
                .body("data", equalTo("some data"))

                .body(matchesJsonSchemaInClasspath("postman.schema.json"))
        ;
    }
}
