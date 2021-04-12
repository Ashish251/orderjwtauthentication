package com.returnorder.jwtauthentication.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.returnorder.jwtauthentication.model.UserModel;

class UserModelTest {

	UserModel userModel = new UserModel();
	
	@Test
	void testUserModel() {
		assertNotNull(userModel);
	}

	@Test
	void testUserModelStringStringString() {
		String expected = "UserModel [userId=null, username=null, password=null]";
		assertEquals(expected, userModel.toString());
	}

	@Test
	void testGetUserId() {
		userModel.setUserId("UID05R");
		assertEquals("UID05R", userModel.getUserId());
	}

	@Test
	void testSetUserId() {
		userModel.setUserId("UID05R");
		assertEquals("UID05R", userModel.getUserId());
	}

	@Test
	void testGetUsername() {
		userModel.setUsername("Ritansh");
		assertEquals("Ritansh", userModel.getUsername());
	}

	@Test
	void testSetUsername() {
		userModel.setUsername("Ritansh");
		assertEquals("Ritansh", userModel.getUsername());
	}

	@Test
	void testGetPassword() {
		userModel.setPassword("Ritansh123");
		assertEquals("Ritansh123", userModel.getPassword());
	}

	@Test
	void testSetPassword() {
		userModel.setPassword("Ritansh123");
		assertEquals("Ritansh123", userModel.getPassword());
	}

	@Test
	void testToString() {
		UserModel user = new UserModel("UID05R","Ritansh", "Ritansh123");
		assertEquals("UID05R", user.getUserId());
		assertEquals("Ritansh", user.getUsername());
		assertEquals("Ritansh123", user.getPassword());
	}

}
