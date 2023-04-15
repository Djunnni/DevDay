package com.example.payservice.v2.user.application;

import com.example.payservice.v2.user.application.port.UserPort;
import com.example.payservice.v2.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/users")
public class UserServiceV2 {

    private UserPort userPort;

    public UserServiceV2(UserPort userPort) {
        this.userPort = userPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> saveUser(@RequestBody AddUserRequest request) {
        final User user = new User(request.getUserId());
        userPort.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
