package com.returnorder.jwtauthentication.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.returnorder.jwtauthentication.model.JwtResponse;
import com.returnorder.jwtauthentication.model.UserModel;

class JwtResponseTest {

	UserModel user = new UserModel("UID","Gaurav","Gaurav123");
	JwtResponse jwtResponse = new JwtResponse("abcd123ef4ghi56",user);
	
	@Test
	void testGetToken() {
		String token = jwtResponse.getToken();
		assertEquals("abcd123ef4ghi56",token);
	}

	@Test
	void testGetUserModel() {
		UserModel user = jwtResponse.getUserModel();
		assertEquals("Gaurav", user.getUsername());
	}

	@Test
	void testSetToken() {
		jwtResponse.setToken("ijklm");
		assertEquals("ijklm",jwtResponse.getToken());
	}

	@Test
	void testSetUserModel() {
		UserModel newUser = new UserModel("UID1","Kumar","Kumar123");
		jwtResponse.setUserModel(newUser);
		assertEquals("Kumar",jwtResponse.getUserModel().getUsername());
	}

	@Test
	void testToString() {
		String string = "JwtResponse [token=" + "abcd123ef4ghi56" + ", userModel=" +"UserModel [userId=" + "UID" + ", username=" + "Gaurav" + ", password=" + "Gaurav123" + "]" + "]";
		assertEquals(string,jwtResponse.toString());
	}

	@Test
	void testJwtResponse() {
		JwtResponse jwt = new JwtResponse();
		assertNotNull(jwt);
	}

	@Test
	void testJwtResponseStringUserModel() {
		JwtResponse jwt = new JwtResponse("123",new UserModel());
		assertNotNull(jwt);
	}


}
