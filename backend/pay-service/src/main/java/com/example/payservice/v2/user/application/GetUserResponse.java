package com.example.payservice.v2.user.application;

import org.springframework.util.Assert;

public class GetUserResponse {
    private Long id;
    private Integer deposit;
    private Integer prize;

    public GetUserResponse(Long id, Integer deposit, Integer prize) {
        Assert.notNull(id, "ID는 필수입니다.");
        Assert.notNull(deposit, "예치금은 필수입니다.");
        Assert.notNull(prize, "상금은 필수입니다.");
        this.id = id;
        this.deposit = deposit;
        this.prize = prize;
    }
}
