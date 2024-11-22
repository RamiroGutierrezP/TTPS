package com.ttps.proyecto.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;


@Service
public class TokenService {
    private static Key key;

    public TokenService(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, int seconds) {
        Date exp = getExpiration(new Date(), seconds);

        return Jwts.builder()
                .subject(username)
                .expiration(exp)
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String token) {
        String prefix = "Bearer";
        try {
            if (token.startsWith(prefix)) {
                token = token.substring(prefix.length()).trim();
            }
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Expiration: " + claims.getExpiration());

            return true;
        } catch (ExpiredJwtException exp) {
            System.out.println("El Token es valido, pero expiro su tiempo de validez");
            return false;
        } catch (JwtException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    private Date getExpiration(Date now, int seconds) {
        return new Date(now.getTime() + seconds * 1000L);
    }

}
