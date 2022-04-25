package com.example.demo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.security.user.AppUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtProvider {

    private static final String SECRET_KEY = "ShoppingCart";
    private static final Long EXPIRATION_TIME = 3600000L;
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public static String createToken(AppUserDetails user, String requestUrl){
        List<String> authorities = new ArrayList<>();
        user.getAuthorities().forEach(authority ->
                authorities.add(authority.getAuthority()));

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer(requestUrl)
                .withClaim("authorities", authorities)
                .sign(ALGORITHM);
    }

    public static DecodedJWT validateToken(String token){
        JWTVerifier verifier = JWT.require(ALGORITHM).build();

        return verifier.verify(token);
    }


}
