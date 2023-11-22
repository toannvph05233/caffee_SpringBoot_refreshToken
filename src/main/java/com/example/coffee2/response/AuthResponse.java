package com.example.coffee2.response;

import com.example.coffee2.entity.UserEntity;

public class AuthResponse {
    public String accessToken;
    public String refreshToken;
    public UserEntity user;

    public AuthResponse(String accessToken, String refreshToken, UserEntity user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

}
