package com.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours

    // Secret key (loaded from application properties or environment variables)
   
    
    private String secret = "r+qkZYn4TtMkK1Op8BRZjfdSceGObZB4/OwOmfIsUVftoB0rAsT+W7WpXtkXtxXgAFzQ5rFSPN2CwYvl46Ej6g==";


    /**
     * Retrieve username from the JWT token.
     *
     * @param token The JWT token.
     * @return Username (subject) from the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Retrieve expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return Expiration date of the token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Retrieve any claim from the JWT token.
     *
     * @param token The JWT token.
     * @param claimsResolver A function to extract a specific claim.
     * @param <T> The type of the claim.
     * @return The claim value.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieve all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return All claims from the token.
     */
    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes()); // Ensure the key is secure
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Check if the token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, otherwise false.
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generate a JWT token for a user.
     *
     * @param userDetails The user details.
     * @return Generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Generate the token with claims and subject.
     *
     * @param claims Custom claims for the token.
     * @param subject The subject (username).
     * @return The generated JWT token.
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        // Ensure the key is secure for HS512 (at least 512 bits)
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes()); 
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Validate a JWT token.
     *
     * @param token The JWT token.
     * @param userDetails The user details to validate against.
     * @return True if the token is valid, otherwise false.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
