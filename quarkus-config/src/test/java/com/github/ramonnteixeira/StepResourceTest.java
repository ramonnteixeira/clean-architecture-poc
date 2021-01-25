package com.github.ramonnteixeira;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class StepResourceTest {

    @Test
    public void listSteps() {
        given()
          .when().get("/api/steps")
          .then()
             .statusCode(200);
    }

}