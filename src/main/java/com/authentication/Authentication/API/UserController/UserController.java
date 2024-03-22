package com.authentication.Authentication.API.UserController;

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

    @PostMapping()
    public String saveEmployee(@RequestBody UserDto body){
        return userService.registerUser(body);
    }
}
