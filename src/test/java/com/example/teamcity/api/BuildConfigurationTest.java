package com.example.teamcity.api;

import com.example.teamcity.api.models.User;
import com.example.teamcity.api.specifications.Specifications;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest{
    @Test
    public void buildConfigurationTest(){
        var token = RestAssured
                .given()
                .spec(
                        Specifications.getSpec().authSpec(new User("admin", "admin"))
                )
                .get("/authenticationTest.html?csrf")
                .then().statusCode(200)
                .extract().asString();
        System.out.println(token);
    }
}
