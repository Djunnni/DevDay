package com.example.payservice.v2.user.application;

import com.example.payservice.v2.user.application.port.UserPort;
import com.example.payservice.v2.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/users")
public class UserServiceV2 {

    private UserPort userPort;

    public UserServiceV2(UserPort userPort) {
        this.userPort = userPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<GetUserResponse> saveUser(@RequestBody AddUserRequest request) {
        User userInfo = userPort.save(new User(request.getUserId(), request.getDeposit(), request.getPrize()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GetUserResponse(userInfo.getId(), userInfo.getDeposit(), userInfo.getPrize()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable final Long userId) {
        User user =  userPort.getUser(userId);

        return ResponseEntity.ok(new GetUserResponse(user.getId(), user.getDeposit(), user.getPrize()));
    }

}
