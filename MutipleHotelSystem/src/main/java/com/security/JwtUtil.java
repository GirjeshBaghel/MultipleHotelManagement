package com.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.entity.Admin;
import com.service.CustomUserDetailsService;

import org.slf4j.Logger;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@SuppressWarnings("deprecation")
@Component
public class JwtUtil {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;


//	@Autowired
//    private TokenService tokenService; // Inject the TokenService

    //requirement :
    public static final long JWT_TOKEN_VALIDITY =5*60 *60;

    //    public static final long JWT_TOKEN_VALIDITY =  60;
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    public  Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(Admin admin) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(admin.getUserEmail());
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", admin.getUserEmail());
        claims.put("role", admin.getRole());
        claims.put("id", admin.getUserId());
       // claims.put("password",admin.getUserPassword());
        System.out.println("Token Created");
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        //    Boolean isTokenValid = tokenService.isTokenValid(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }

    private Set<String> blacklistedTokens = new HashSet<>(); // Track blacklisted tokens

    // Other methods...

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public Boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }


}
