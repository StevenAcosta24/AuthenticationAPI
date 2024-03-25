package com.authentication.Authentication.API.Controller;

import com.authentication.Authentication.API.Dto.UserDto;
import com.authentication.Authentication.API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth/v1/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto body) {
        return userService.register(body);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto body) {
        return userService.login(body);
    }
}
