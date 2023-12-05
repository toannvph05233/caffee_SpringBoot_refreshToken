package com.example.coffee2.service.author;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtService {
    @Value("${jwt.expiration}")
    private long expiration;
//    @Value("${jwt.secret}")
//    private long secret;

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    String secretString = Encoders.BASE64.encode(key.getEncoded());

    // hàm tạo ra token
    public String generateAccessToken(String username) {
        // lấy đối tượng đang đăng nhập.
        return Jwts.builder()
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secretString)
                .compact();
    }



    // lấy username từ token
    public String getUsernameFromToken(String token) {
        String userName = Jwts.parser()
                .setSigningKey(secretString)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userName;
    }



}
