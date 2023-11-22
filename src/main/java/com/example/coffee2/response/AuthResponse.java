package com.example.coffee2.response;

import com.example.coffee2.entity.UserEntity;

public class AuthResponse {

    public String token;
    public String accessToken;
    public String refreshToken;
    public UserEntity user;

//    public AuthResponse(String token, UserEntity user) {
//        this.token = token;
//        this.user = user;
//    }

    public AuthResponse(String accessToken, String refreshToken, UserEntity user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

}
