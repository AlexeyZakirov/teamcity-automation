package com.example.teamcity.api.specifications;

import com.example.teamcity.api.models.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;

import static com.example.teamcity.api.config.Config.getProperty;

public class Specifications {

    private static Specifications spec;

    private Specifications() {}

    public static Specifications getSpec() {
        if (spec == null) {
            spec = new Specifications();
        }
        return spec;
    }

    private RequestSpecBuilder reqBuilder() {
        return new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());
    }

    public RequestSpecification unauthSpec() {
        return reqBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public RequestSpecification authSpec(User user) {
        return reqBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(String.format("http://%s:%s@%s", user.getUsername(), user.getPassword(), getProperty("host")))
                .build();
    }
}
