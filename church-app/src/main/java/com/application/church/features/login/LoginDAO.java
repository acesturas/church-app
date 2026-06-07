package com.application.church.features.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.application.church.global.utils.JwtUtil;

public class LoginDAO {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginResponse login(Connection conn, LoginRequest loginRequest) {

        LoginResponse loginResponse = new LoginResponse();

        String sql = """
                SELECT first_name,
                       password
                  FROM system_users
                 WHERE username = ?
                """;

        try (PreparedStatement ps1 = conn.prepareStatement(sql)) {

            ps1.setString(1,  loginRequest.getUsername());

            try (ResultSet rs1 =  ps1.executeQuery()) {

                if (!rs1.next()) {

                    loginResponse.setSuccess(false);
                    return loginResponse;
                }

                String dbPassword = rs1.getString("password");

                boolean valid = encoder.matches( loginRequest.getPassword(), dbPassword);

                if (!valid) {

                    loginResponse.setSuccess(false);
                    return loginResponse;
                }

                String token = JwtUtil.generateToken(  loginRequest.getUsername());

                loginResponse.setSuccess(true);
                loginResponse.setFirstName(rs1.getString("first_name"));
                loginResponse.setToken(token);

                return loginResponse;
            }

        } catch (SQLException e) {

            e.printStackTrace();

            loginResponse.setSuccess(false);
            return loginResponse;
        }
    }
}