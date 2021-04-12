package com.returnorder.jwtauthentication.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.returnorder.jwtauthentication.model.JwtRequest;

class JwtRequestTest {

	JwtRequest jwtRequest = new JwtRequest();
	
	@Test
	void testJwtRequest() {
		assertNotNull(jwtRequest);
	}

	@Test
	void testJwtRequestStringString() {
		JwtRequest jr = new JwtRequest("Darsh", "Darsh123");
		assertEquals("Darsh", jr.getUsername());
		assertEquals("Darsh123", jr.getPassword());
	}

	@Test
	void testGetUsername() {
		jwtRequest.setUsername("Darsh");
		assertEquals("Darsh", jwtRequest.getUsername());
	}

	@Test
	void testSetUsername() {
		jwtRequest.setUsername("Darsh");
		assertEquals("Darsh", jwtRequest.getUsername());
	}

	@Test
	void testGetPassword() {
		jwtRequest.setPassword("Darsh123");
		assertEquals("Darsh123", jwtRequest.getPassword());
	}

	@Test
	void testSetPassword() {
		jwtRequest.setPassword("Darsh123");
		assertEquals("Darsh123", jwtRequest.getPassword());
	}

	@Test
	void testToString() {
		JwtRequest jr = new JwtRequest("Darsh", "Darsh123");
		String expected = "JwtRequest [username=Darsh, password=1813604712]";
		assertEquals(expected, jr.toString());
	}

}
