package com.teluguskillhub.springsecurity.security;

import com.teluguskillhub.springsecurity.Exception.TaskUserAssociationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        Date currentDate = new Date();
        int milliSecondsInOneHour = 3600000;
        Date expiryDate  = new Date(currentDate.getTime() + milliSecondsInOneHour);
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, "JWTSecretKey")
                .compact();
        return token;
    }
    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey("JWTSecretKey")
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey("JWTSecretKey")
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception exception){
            throw new TaskUserAssociationException("Token issue: "+exception.getMessage());
        }
    }
}
