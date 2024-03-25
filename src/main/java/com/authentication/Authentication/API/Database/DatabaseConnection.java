package com.authentication.Authentication.API.Database;

import com.authentication.Authentication.API.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class DatabaseConnection {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DatabaseConnection() {
    }

    public String register(UserDto user) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SaveUser");

        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("username", user.getUsername());
        inParamMap.put("email", user.getEmail());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        inParamMap.put("password", passwordEncoder.encode(user.getPassword()));
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> res = simpleJdbcCall.execute(in);
        int id = -1;
        for (Object o : res.entrySet()) {
            @SuppressWarnings("rawtypes")
            Map.Entry obj = (Map.Entry) o;
            id = (int) obj.getValue();
        }
        if (id > 0) {
            return "User registered successfully.";
        }
        return "Error.";
    }

    @SuppressWarnings("unchecked")
    public Optional<UserDto> login(UserDto user) throws SQLException {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("GetUser");
            Map<String, Object> inParamMap = new HashMap<String, Object>();
            inParamMap.put("email", user.getEmail());
            SqlParameterSource in = new MapSqlParameterSource(inParamMap);
            ArrayList<Object> res = (ArrayList<Object>) simpleJdbcCall.execute(in).get("#result-set-1");
            if (res.isEmpty())
                return Optional.empty();

            Map<String, Object> resObj = (Map<String, Object>) res.get(0);

            UserDto userFinded = new UserDto(
                    (int) resObj.get("id"),
                    (String) resObj.get("username"),
                    (String) resObj.get("email"),
                    (String) resObj.get("password"));
            return Optional.of(userFinded);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
