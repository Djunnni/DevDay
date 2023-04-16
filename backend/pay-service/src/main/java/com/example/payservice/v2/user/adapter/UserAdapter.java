package com.example.payservice.v2.user.adapter;

import com.example.payservice.v2.user.application.port.UserPort;
import com.example.payservice.v2.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UserAdapter implements UserPort {

    private UserRespository userRespository;

    @Autowired
    public UserAdapter(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public User save(User user) {
        Optional<User> existUser = userRespository.findById(user.getId());
        if(existUser.isPresent()) {
            log.info("이미 존재하는 유저입니다. 기존 유저 정보를 리턴합니다. -> userId : {}, deposit: {}, prize: {}",
                    user.getId(),
                    user.getDeposit(),
                    user.getPrize()
            );
            return existUser.get();
        }
        User newUser = userRespository.save(user);
        return newUser;
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
