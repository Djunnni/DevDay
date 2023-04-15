package com.example.payservice.v2.user.adapter;

import com.example.payservice.v2.user.application.port.UserPort;
import com.example.payservice.v2.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdapter implements UserPort {

    private UserRespository userRespository;

    @Autowired
    public UserAdapter(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public void save(User user) {
        userRespository.save(user);
    }

    @Override
    public User getUser(long userId) {
        Optional<User> user =  userRespository.findById(userId);
        if(!user.isPresent()) {
            throw new RuntimeException("유저가 존재하지 않습니다.");
        }

        return user.get();
    }

}
