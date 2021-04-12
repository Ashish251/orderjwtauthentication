package com.returnorder.jwtauthentication.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.returnorder.jwtauthentication.helper.JwtUtil;

import io.jsonwebtoken.Claims;

class JwtUtilTest {

	
	JwtUtil jwtUtil = new JwtUtil();
	
	@Mock
	Claims claims;

	private String extractUsername;

	private Date extractExpiration;

	private UserDetails userDetails = new User("Darsh", "Darsh123", (Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());

	private Boolean validateToken;
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testExtractUsername() {
		String token = jwtUtil.createToken(new HashMap<String, Object>(), "Darsh");
		extractUsername = jwtUtil.extractUsername(token);
		assertEquals("Darsh", extractUsername);
	}

	@Test
	void testExtractExpiration() {
		String token = jwtUtil.createToken(new HashMap<String, Object>(), "Darsh");
		extractExpiration = jwtUtil.extractExpiration(token);
		assertNotNull(extractExpiration);
	}

	
	@Test
	void testGenerateToken() {
		String generateToken = jwtUtil.generateToken(userDetails);
		assertNotNull(generateToken);
	}

	@Test
	void createToken() {
        String token = jwtUtil.createToken(new HashMap<String, Object>(), "Darsh");
        assertNotNull(token);
    }
	
	@Test
	void testValidateToken() {
		String token = jwtUtil.createToken(new HashMap<String, Object>(), "Darsh");
		validateToken = jwtUtil.validateToken(token, userDetails);
		assertEquals(true, validateToken);
	}

}
