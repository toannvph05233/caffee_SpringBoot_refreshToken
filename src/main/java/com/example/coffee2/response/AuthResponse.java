package com.example.coffee2.response;

import com.example.coffee2.entity.UserEntity;

public class AuthResponse {
    public String token;
    public UserEntity user;

    public AuthResponse(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

}
