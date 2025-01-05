package com.budgetupdate.budgetupdate.Utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            logger.debug("Generated signing key successfully.");
            return key;
        } catch (Exception e) {
            logger.error("Error generating signing key: {}", e.getMessage(), e);
            throw e;
        }
    }

    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // Ensure userId is used as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserId(String token) {
        try {
            String userId = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            logger.info("Extracted user ID {} from token: {}", userId, token);
            return userId;
        } catch (Exception e) {
            logger.error("Error extracting user ID from token: {}", e.getMessage(), e);
            throw e;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userIdFromToken = extractUserId(token); // Extract userId from token
        String userIdFromDetails = userDetails.getUsername(); // Get username (userId) from CustomUserDetails

        logger.info("Validating token:");
        logger.info("User ID from token: {}", userIdFromToken);
        logger.info("User ID from details: {}", userIdFromDetails);

        boolean isExpired = isTokenExpired(token);
        logger.info("Is token expired: {}", isExpired);

        boolean isValid = userIdFromToken.equals(userIdFromDetails) && !isExpired;
        logger.info("Token validation result: {}", isValid);

        return isValid;
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            boolean isExpired = expiration.before(new Date());
            logger.info("Token expiration date: {}. Is expired: {}", expiration, isExpired);
            return isExpired;
        } catch (Exception e) {
            logger.error("Error checking token expiration: {}", e.getMessage(), e);
            throw e;
        }
    }
}



