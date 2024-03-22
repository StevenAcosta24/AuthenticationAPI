package com.authentication.Authentication.API.Service;

import com.authentication.Authentication.API.Database.DatabaseConnection;
import com.authentication.Authentication.API.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    DatabaseConnection db;

    public String registerUser(UserDto user){
        try {
            return this.db.registerUser(user);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
