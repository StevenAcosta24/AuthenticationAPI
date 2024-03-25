package com.authentication.Authentication.API.Service;

import com.authentication.Authentication.API.Database.DatabaseConnection;
import com.authentication.Authentication.API.Dto.UserDto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    DatabaseConnection db;

    public String register(UserDto user) {
        try {
            return this.db.register(user);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String login(UserDto user) {
        try {
            Optional<UserDto> userSearch = this.db.login(user);
            if (userSearch.isEmpty())
                return "User not found.";

            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            if (passwordEncoder.matches(user.getPassword(), userSearch.get().getPassword()))
                return String.valueOf(userSearch.get().getId());

            return "User not found.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
