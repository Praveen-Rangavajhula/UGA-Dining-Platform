package com.db.project.ugadining.security.auth;

import com.db.project.ugadining.exception.NotFoundException;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger( JwtService.class );
    private static final String SECRET_KEY;

    static {

        logger.info("Initializing the secret key");
        String secretKey = Dotenv.load().get("SECRET_KEY");

        if (secretKey == null || secretKey.trim().isEmpty()) {
            logger.error("Secret key not found or empty");
            throw new NotFoundException("Secret key is missing or empty. Please set the environment property 'SECRET_KEY'");
        }

        SECRET_KEY = secretKey;
        logger.info("Secret key initialized successfully");
    }

    public String extractUsername(String jwtToken) {
        logger.info("Extracting username from jwt token");
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        logger.info("Generating a new token");
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        logger.info("Generating a new token with extra claims");
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        logger.info("Validating the jwt token");
        final String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        logger.info("Checking if token is expired");
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        logger.info("Extracting expiration date from jwt token");
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        logger.info("Extracting claims from jwt token");
        final Claims claims = extractAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaimsFromToken(String jwtToken) {
        logger.info("Extracting all claims from jwt token");
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {
        logger.info("Obtaining the Sign In key");
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
