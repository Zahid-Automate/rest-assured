package com.restfulbooker;

import static org.hamcrest.Matchers.lessThan;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

/**
 * Created By Mohammed on 18-02-2022
 */
public class BaseSetup {

    @BeforeClass
    public void setup () {

        RequestSpecification requestSpecification = new RequestSpecBuilder ().setBaseUri (
                "https://restful-booker.herokuapp.com")
            .addHeader ("Content-Type", "application/json")
            .addHeader ("Accept", "application/json")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .build ();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder ().expectResponseTime (lessThan (20000L))
            .build ();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }
}