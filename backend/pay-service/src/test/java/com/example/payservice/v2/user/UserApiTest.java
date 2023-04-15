package com.example.payservice.v2.user;

import com.example.payservice.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.example.payservice.v2.user.UserSteps.유저생성요청;
import static com.example.payservice.v2.user.UserSteps.유저생성요청_생성;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
public class UserApiTest extends ApiTest {

    @Test
    void 유저생성() {
        var response = 유저생성요청(유저생성요청_생성());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


}
