package com.marvic.popstats;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoreBasedStatisticalAreaSpecs {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.basePath = "/population/cbsas";
    }

    @Nested
    class All_core_based_statistical_areas {
        @Test
        void are_returned_if_no_titles_or_codes_are_provided() {
            given().log().params()
                    .when().get()
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(926));
        }
    }

    @Nested
    class Requested_core_based_statistical_areas {
        @Test
        void are_returned_if_one_code_is_provided() {
            given().log().params().queryParam("code", "42660")
                    .when().get()
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(1));
        }

        @Test
        void are_returned_if_several_codes_are_provided() {
            given().log().params().queryParam("code", List.of("41180", "42660"))
                    .when().get()
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(2));
        }

        @Test
        void are_returned_if_one_title_is_provided() {
            given().log().params().queryParam("title", "St. Louis, MO-IL")
                    .when().get()
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(1));
        }

        @Test
        void are_returned_if_several_titles_are_provided() {
            given().log().params()
                    .queryParam("title", List.of("St. Louis, MO-IL", "Seattle-Tacoma-Bellevue, WA"))
                    .when().get()
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(2));
        }

        @Test
        void are_returned_if_several_codes_and_titles_are_provided() {
            given().log().params().queryParam("code",  List.of("41180", "42660"))
                    .queryParam("title", List.of("Abilene, TX", "Albuquerque, NM"))
                    .when().get()
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(4));
        }
    }

    @Nested
    class An_individual_core_based_statistical_area {
        @Test
        void is_returned_if_a_code_is_provided() {
            given().log().params().pathParam("code", "41180")
                    .when().get("/{code}")
                    .then().log().ifError()
                    .statusCode(HttpStatus.OK.value()).body("itemsIncluded", equalTo(1));
        }
    }
}
