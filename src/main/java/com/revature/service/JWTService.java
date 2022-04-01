package com.revature.service;

import com.revature.model.User;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTService {

    private static JWTService instance;

    private Key key;

    // Instance initialization block
    // This will run immediately after the constructor
//    {
//        byte[] secret = "my_secret_password".getBytes();
//        key = Keys.hmacShaKeyFor(secret); // Create a key using
//    }
    private JWTService() {
        byte[] secret = "there-is-no-secretHS_eqkivqX-0i8dXDf6IoVdGW-u4mzltQ17bVn-RSdkd52d1".getBytes();
        // key = Keys.secretKeyFor(SignatureAlgorithm.HS384); // Create a key using our secret password
        key = Keys.hmacShaKeyFor(secret);
    }

    // method
    public static JWTService getInstance() {
        if (JWTService.instance == null) {
            JWTService.instance = new JWTService();
        }

        return JWTService.instance;
    }

    // Signing a JWT with the key
    public String createJWT(User user) {
        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user_id", user.getId())
                .claim("user_role", user.getUser_role())
                .signWith(key)
                .compact();

        return jwt;
    }

    // Validating a JWT with the key
    public Jws<Claims> parseJwt(String jwt) {
        try {
            Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

            return token;
        } catch(JwtException e) {
            e.printStackTrace();
            throw new UnauthorizedResponse("JWT was invalid");
        }

    }

}
