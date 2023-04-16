package com.example.payservice.v2.user.domain;

import com.example.payservice.common.util.V2;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@V2
public class User {
    @Id
    private Long id;
    private Integer deposit;
    private Integer prize;

    public User(Long id) {
        this(id, 0, 0);
    }

    public User(Long id, Integer deposit, Integer prize) {
        Assert.notNull(id, "ID는 필수입니다.");
        Assert.notNull(deposit, "예치금은 필수입니다.");
        Assert.notNull(prize, "상금은 필수입니다.");
        this.id = id;
        this.deposit = deposit;
        this.prize = prize;
    }
}
