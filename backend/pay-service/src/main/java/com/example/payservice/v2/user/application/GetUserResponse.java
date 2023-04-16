package com.example.payservice.v2.user.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GetUserResponse {
    @NotNull
    private Long id;
    @NotNull
    private Integer deposit;
    @NotNull
    private Integer prize;

}
