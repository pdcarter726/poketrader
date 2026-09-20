package edu.ncsu.csc440.poketrader.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Provides a token for the user.
 */
@Component
public class JwtTokenProvider {

	/** Pulls secret from application.properties */
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    /** Pulls expiration of user login from application.properties */
    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;

    /**
     * Generates the token
     * @param authentication authentication object
     * @return the generated token
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);


        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(key())
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(decodeSecret(jwtSecret));
    }

    private byte[] decodeSecret(String secret) {
        byte[] decoded = Decoders.BASE64.decode(secret);
        if (decoded.length > 0) {
            return decoded;
        }
        if (secret.matches("^[0-9a-fA-F]+$") && secret.length() % 2 == 0) {
            return hexStringToByteArray(secret);
        }
        throw new IllegalArgumentException("JWT secret must be Base64 or hex encoded");
    }

    private byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * Returns the username
     * @param token token to use for authentication
     * @return the username that is authenticated
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject(); //username

    }

    /**
     * Checks the token is valid.
     * @param token token to check
     * @return true if valid
     */
    public boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith(key())
                .build()
                .parse(token);
        return true;
    }
}
