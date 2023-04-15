package com.example.payservice.v2.user;

import com.example.payservice.v2.user.application.AddUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class UserSteps {
    public static ExtractableResponse<Response> 유저생성요청(AddUserRequest request) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request).when().post("/v2/users").then()
                .log().all().extract();
    }

    public static AddUserRequest 유저생성요청_생성() {
        final Long userId = 1L;
        return new AddUserRequest(userId);
    }

}
