package com.returnorder.jwtauthentication.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//methods - for generating token
//validate
//isExp
//util class for jwt

@Component
public class JwtUtil {
	
	private Logger log = LoggerFactory.getLogger(JwtUtil.class);
	
	private static final long serialVersionUID = -2550185165626007488L;
	
	private static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	private String SECRET_KEY = "java";

	public String extractUsername(String token) {
		log.info("JwtUtil :: extractUsername");
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		log.info("JwtUtil :: extractExpiration");
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.info("JwtUtil :: extractClaim");
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		log.info("JwtUtil :: extractAllClaims");
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		log.info("JwtUtil :: isTokenExpired");
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		log.info("JwtUtil :: generateToken");
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	public String createToken(Map<String, Object> claims, String subject) {
		log.info("JwtUtil :: createToken");
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
	
		log.info("JwtUtil :: validateToken");
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
