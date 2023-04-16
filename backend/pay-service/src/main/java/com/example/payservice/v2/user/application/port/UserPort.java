package com.example.payservice.v2.user.application.port;

import com.example.payservice.v2.user.domain.User;

public interface UserPort {
    User save(User user);

    User getUser(long userId);
}
