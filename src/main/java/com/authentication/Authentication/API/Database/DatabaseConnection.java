package com.authentication.Authentication.API.Database;

import com.authentication.Authentication.API.Dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseConnection {
    public DatabaseConnection(){}
    public String registerUser(UserDto user){
        if(user.getUsername().equalsIgnoreCase("test")
            && user.getPassword().equalsIgnoreCase("1234")){
            return "1";
        }
        return null;
    }
}
