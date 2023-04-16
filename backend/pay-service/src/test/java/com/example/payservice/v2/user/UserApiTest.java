package com.example.payservice.v2.user;

import com.example.payservice.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.example.payservice.v2.user.UserSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
public class UserApiTest extends ApiTest {

    @Test
    void 유저생성() {
        var response = 유저생성요청(유저생성요청_생성());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 이미_존재하는_유저생성() {
        유저생성요청(유저생성요청_생성(1L, 2000, 3000));
        var response = 유저생성요청(유저생성요청_생성());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.jsonPath().getInt("deposit")).isEqualTo(2000);
    }

    @Test
    void 유저조회() {
        유저생성요청(유저생성요청_생성());
        final long userId = 1L;
        var response = 유저조회요청(userId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
