package com.example.payservice.v2.user.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddUserRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Integer deposit;

    @NotNull
    private Integer prize;

}
